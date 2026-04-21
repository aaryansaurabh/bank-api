package com.aaryan.bankapi.controller;

import com.aaryan.bankapi.Model.AccountType;
import com.aaryan.bankapi.Model.User;
import com.aaryan.bankapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user ,
                                             @RequestParam AccountType accountType){
        authService.register(user,accountType);
        return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        return authService.login(email, password);
    }


}
