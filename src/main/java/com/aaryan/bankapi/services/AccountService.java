package com.aaryan.bankapi.services;

import com.aaryan.bankapi.Model.AccountType;
import com.aaryan.bankapi.Model.Transaction;
import com.aaryan.bankapi.Model.User;
import com.aaryan.bankapi.dto.TransactionResponseDto;

import java.util.List;

public interface AccountService {
    void createAccount(User user , AccountType accountType);

    Double getBalance(String accountNo);

    void deposit(String accountNo, Double amount);

    void withdraw(String accountNo , Double amount);

    void transfer(String fromAccount , String toAccount , Double amount);

    List<TransactionResponseDto> getTransactionHistory(String accountNo);
}
