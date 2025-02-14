package com.retaileroffers.rewards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.time.LocalDate;
@Data
public class TransactionDto {
    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @Min(value = 1, message = "Amount must be positive")
    private double amount;

    @NotNull(message = "Transaction date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in YYYY-MM-DD format")
    private LocalDate transactionDate;
}
