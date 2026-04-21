package com.aaryan.bankapi.services;

import com.aaryan.bankapi.Model.AccountType;
import com.aaryan.bankapi.Model.User;
import com.aaryan.bankapi.Repositories.UserRepository;
import com.aaryan.bankapi.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final AccountService accountService;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder ;
    @Override
    public void register(User user, AccountType accountType) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        accountService.createAccount(user,accountType);
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("email is not found"));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("invalid");
        }
        return jwtUtil.generateToken(email);
    }
}
