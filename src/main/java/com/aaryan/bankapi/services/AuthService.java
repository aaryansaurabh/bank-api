package com.aaryan.bankapi.services;


import com.aaryan.bankapi.Model.AccountType;
import com.aaryan.bankapi.Model.User;

public interface AuthService {
    void register (User user, AccountType accountType);

    String login(String email , String password);
}
