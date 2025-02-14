package com.RetailerOffers.rewards.serviceTest;

import com.retaileroffers.rewards.dto.TransactionDto;
import com.retaileroffers.rewards.entity.Transaction;
import com.retaileroffers.rewards.exception.TransactionNotFoundException;
import com.retaileroffers.rewards.repository.TransactionRepository;
import com.retaileroffers.rewards.service.CustomerService;
import com.retaileroffers.rewards.service.RewardService;

import com.retaileroffers.rewards.service.serviceImpl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private RewardService rewardService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction transaction;
    private TransactionDto transactionDto;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setCustomerId(100L);
        transaction.setAmount(200.0);
        transaction.setTransactionDate(LocalDate.of(2024, 2, 14));

        transactionDto = new TransactionDto();
        transactionDto.setCustomerId(100L);
        transactionDto.setAmount(200.0);
        transactionDto.setTransactionDate(LocalDate.parse("2024-02-14"));
    }

    @Test
    void testGetTransactionsByCustomerId_Success() {
        when(customerService.getCustomerById(100L)).thenReturn(null); // Ensure customer exists
        when(transactionRepository.getByCustomerId(100L)).thenReturn(List.of(transaction));

        List<TransactionDto> result = transactionService.getTransactionsByCustomerId(100L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(200.0, result.get(0).getAmount());
        verify(transactionRepository, times(1)).getByCustomerId(100L);
    }

    @Test
    void testGetTransactionsByCustomerId_TransactionNotFound() {
        when(customerService.getCustomerById(100L)).thenReturn(null);
        when(transactionRepository.getByCustomerId(100L)).thenReturn(List.of());

        Exception exception = assertThrows(TransactionNotFoundException.class, () ->
                transactionService.getTransactionsByCustomerId(100L));

        assertEquals("Transaction not found for customer 100", exception.getMessage());
        verify(transactionRepository, times(1)).getByCustomerId(100L);
    }

    @Test
    void testGetAllTransactions_Success() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));

        List<TransactionDto> result = transactionService.getAllTransactions();

        assertEquals(1, result.size());
        assertEquals(200.0, result.get(0).getAmount());
        verify(transactionRepository, times(1)).findAll();
    }
}
