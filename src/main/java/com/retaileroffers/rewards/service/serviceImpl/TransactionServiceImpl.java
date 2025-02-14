package com.retaileroffers.rewards.service.serviceImpl;

import com.retaileroffers.rewards.dto.TransactionDto;
import com.retaileroffers.rewards.entity.Transaction;
import com.retaileroffers.rewards.exception.TransactionNotFoundException;
import com.retaileroffers.rewards.mapper.TransactionMapper;
import com.retaileroffers.rewards.repository.TransactionRepository;
import com.retaileroffers.rewards.service.CustomerService;
import com.retaileroffers.rewards.service.RewardService;
import com.retaileroffers.rewards.service.TransactionService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private RewardService rewardService;
    private CustomerService customerService;

    @Override
    public List<TransactionDto> getTransactionsByCustomerId(Long customerId) throws TransactionNotFoundException {
        customerService.getCustomerById(customerId);
        List<Transaction> transactionList=transactionRepository.getByCustomerId(customerId);
        if(transactionList.isEmpty()){
            throw new TransactionNotFoundException("Transaction not found for customer "+customerId);
        }
        return transactionList.stream()
                .map(n->TransactionMapper.mapToDto(n,new TransactionDto()))
                .toList();
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactionList=transactionRepository.findAll();
        return transactionList.stream()
                .map(n->TransactionMapper.mapToDto(n,new TransactionDto()))
                .toList();
    }

    @Override
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        customerService.getCustomerById(transactionDto.getCustomerId());
        int points= rewardService.calculateRewards(transactionDto.getAmount());
        Transaction transaction=TransactionMapper.mapFromDto(transactionDto,new Transaction(),points);
        transactionRepository.save(transaction);
        return TransactionMapper.mapToDto(transaction,new TransactionDto());
    }
}
