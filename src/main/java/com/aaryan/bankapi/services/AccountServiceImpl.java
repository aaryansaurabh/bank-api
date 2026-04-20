package com.aaryan.bankapi.services;

import com.aaryan.bankapi.Model.Account;
import com.aaryan.bankapi.Model.AccountType;
import com.aaryan.bankapi.Model.Transaction;
import com.aaryan.bankapi.Model.User;
import com.aaryan.bankapi.Repositories.AccountRepository;
import com.aaryan.bankapi.Repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{

    private  final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;
    @Override
    public void createAccount(User user, AccountType accountType) {
        Account account = new Account();
        account.setUser(user);
        account.setAccountType(accountType);
        account.setBalance(0.0);
        account.setAccountNo("SB" + System.currentTimeMillis());
        accountRepository.save(account);
    }

    @Override
    public Double getBalance(String accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    @Override
    public void deposit(String accountNo, Double amount) {
        Account found = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        found.setBalance(found.getBalance()+amount);
        accountRepository.save(found);
    }

    @Override
    public void withdraw(String accountNo, Double amount) {
        Account accountFound = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (accountFound.getBalance() >= amount) {
            accountFound.setBalance(accountFound.getBalance() - amount);
            accountRepository.save(accountFound);
        }else {
            System.out.println(" Balance present in account is less than withdraw");
        }
    }

    @Override
    public void transfer(String fromAccount, String toAccount, Double amount) throws RuntimeException {
        Account sender = accountRepository.findByAccountNo(fromAccount)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Account receiver = accountRepository.findByAccountNo(toAccount)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if(sender.getBalance()>= amount){
            sender.setBalance(sender.getBalance()-amount);
            receiver.setBalance(receiver.getBalance()+amount);
            accountRepository.save(sender);
            accountRepository.save(receiver);
        }else{
            throw new RuntimeException("Insufficient balance");
        }
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNo) {
        Account found = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        return transactionRepository
                .findByFromAccountOrToAccount(found, found);
    }
}
