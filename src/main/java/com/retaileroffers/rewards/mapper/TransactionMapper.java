package com.retaileroffers.rewards.mapper;

import com.retaileroffers.rewards.dto.TransactionDto;
import com.retaileroffers.rewards.entity.Transaction;

public class TransactionMapper {
    static public TransactionDto mapToDto(Transaction transaction,TransactionDto transactionDto){
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        transactionDto.setCustomerId(transaction.getCustomerId());
        return transactionDto;
    }

    public static Transaction mapFromDto(TransactionDto transactionDto, Transaction transaction, int points) {
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setCustomerId(transactionDto.getCustomerId());
        transaction.setPoints(points);
        return transaction;
    }
}
