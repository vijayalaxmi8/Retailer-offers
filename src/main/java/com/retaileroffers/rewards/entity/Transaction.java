package com.retaileroffers.rewards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private double amount;
    private LocalDate transactionDate;
    private int points;

    public Transaction(Long customerId, double amount, LocalDate transactionDate,int points) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.points=points;
    }
}
