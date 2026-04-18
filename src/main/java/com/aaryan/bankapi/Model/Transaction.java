package com.aaryan.bankapi.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount ;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType ;

    private String description;

    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();

}
