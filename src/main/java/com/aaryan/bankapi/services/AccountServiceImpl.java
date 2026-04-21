package com.aaryan.bankapi.services;

import com.aaryan.bankapi.Model.*;
import com.aaryan.bankapi.Repositories.AccountRepository;
import com.aaryan.bankapi.Repositories.TransactionRepository;
import com.aaryan.bankapi.dto.TransactionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{

    private  final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private  final Emailservice emailservice;
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
            Transaction transaction = new Transaction();
            transaction.setFromAccount(sender);
            transaction.setToAccount(receiver);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setStatus("SUCCESS");
            transaction.setTransactionId("TXN" + System.currentTimeMillis());
            transactionRepository.save(transaction);
            emailservice.sendEmail(
                    sender.getUser().getEmail(),
                    "Transfer Alert ",
                    "Hi " + sender.getUser().getName() +" " + amount + " has been credited to Beneficiary"
            );
            emailservice.sendEmail(
                    receiver.getUser().getEmail(),
                    "Credit Alert",
                    "Hi " + receiver.getUser().getName() +" " + amount +
                            "has been credited by" +sender.getUser().getName()

            );
        }else{
            throw new RuntimeException("Insufficient balance");
        }
    }

    @Override
    public List<TransactionResponseDto> getTransactionHistory(String accountNo) {
        Account found = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        return transactionRepository
                .findByFromAccountOrToAccount(found, found)
                .stream()
                .map(transaction -> {
                    TransactionResponseDto dto = new TransactionResponseDto();
                    dto.setTransactionId(transaction.getTransactionId());
                    dto.setFromAccountNo(transaction.getFromAccount().getAccountNo());
                    dto.setToAccountNo(transaction.getToAccount().getAccountNo());
                    dto.setAmount(transaction.getAmount());
                    dto.setTransactionType(transaction.getTransactionType());
                    dto.setStatus(transaction.getStatus());
                    dto.setCreatedAt(transaction.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
