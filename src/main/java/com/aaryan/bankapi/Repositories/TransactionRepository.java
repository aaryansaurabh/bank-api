package com.aaryan.bankapi.Repositories;

import com.aaryan.bankapi.Model.Account;
import com.aaryan.bankapi.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository <Transaction,Long>{
    List<Transaction> findByFromAccountOrToAccount(Account fromAccount , Account toAccount);
}
