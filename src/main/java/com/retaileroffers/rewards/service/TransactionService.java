package com.retaileroffers.rewards.service;

import com.retaileroffers.rewards.dto.TransactionDto;
import com.retaileroffers.rewards.exception.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {

    public List<TransactionDto> getTransactionsByCustomerId(Long customerId)throws TransactionNotFoundException;

    public List<TransactionDto> getAllTransactions();

    public TransactionDto saveTransaction(TransactionDto transaction);
}
