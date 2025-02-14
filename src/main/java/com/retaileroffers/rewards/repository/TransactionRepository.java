package com.retaileroffers.rewards.repository;

import com.retaileroffers.rewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> getByCustomerId(Long cusromerId);
}
