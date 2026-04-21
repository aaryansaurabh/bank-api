package com.aaryan.bankapi.controller;

import com.aaryan.bankapi.Model.Transaction;
import com.aaryan.bankapi.dto.TransactionResponseDto;
import com.aaryan.bankapi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam String accountNo){
        Double balance = accountService.getBalance(accountNo);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String accountNo , @RequestBody Double amount){
        accountService.deposit(accountNo, amount);
        return new ResponseEntity<>("Deposit successful", HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String accountNo , @RequestBody Double amount){
        accountService.withdraw(accountNo,amount);
        return new ResponseEntity<>("withdraw successful", HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer (
                @RequestParam String fromAccount,
                @RequestParam String toAccount,
                @RequestBody Double amount){
        accountService.transfer(fromAccount, toAccount, amount);
        return new ResponseEntity<>("transfer completed ", HttpStatus.OK);

    }

    @GetMapping("/transaction")
    public ResponseEntity<?> transaction(@RequestParam String accountNo ){
        List<TransactionResponseDto> transactions = accountService.getTransactionHistory(accountNo);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }


}
