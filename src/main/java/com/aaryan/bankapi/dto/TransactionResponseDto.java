package com.aaryan.bankapi.dto;

import com.aaryan.bankapi.Model.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransactionResponseDto {

    private String transactionId;
    private String fromAccountNo;
    private String toAccountNo;
    private Double amount;
    private TransactionType transactionType;
    private String status;
    private LocalDateTime createdAt;
}
