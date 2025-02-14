package com.retaileroffers.rewards.controller;

import com.retaileroffers.rewards.dto.TransactionDto;
import com.retaileroffers.rewards.exception.TransactionNotFoundException;
import com.retaileroffers.rewards.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction API", description = "Endpoints for managing transactions")
@RequiredArgsConstructor
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{customerId}")
    @Operation(summary = "Get transactions by customer ID")
    public ResponseEntity<List<TransactionDto>> getTransactions(
            @PathVariable Long customerId) throws TransactionNotFoundException {

        List<TransactionDto> transactions = transactionService.getTransactionsByCustomerId(customerId);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for customer ID: " + customerId);
        }
        return ResponseEntity.ok(transactions);
    }

    // Fetch all transactions
    @GetMapping
    @Operation(summary = "Get all transactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // Save a new transaction
    @PostMapping
    @Operation(summary = "Save a new transaction")
    public ResponseEntity<TransactionDto> addTransaction(@Valid @RequestBody TransactionDto transaction) {
        return ResponseEntity.ok(transactionService.saveTransaction(transaction));
    }
}