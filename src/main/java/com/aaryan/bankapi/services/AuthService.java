package com.aaryan.bankapi.services;


import com.aaryan.bankapi.Model.User;

public interface AuthService {
    void register (User user);

    String login(String email , String password);
}
