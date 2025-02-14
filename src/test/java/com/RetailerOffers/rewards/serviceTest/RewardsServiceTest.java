package com.RetailerOffers.rewards.serviceTest;

import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.RewardsDto;
import com.retaileroffers.rewards.entity.Transaction;
import com.retaileroffers.rewards.exception.CustomerNotFoundException;
import com.retaileroffers.rewards.exception.TransactionNotFoundException;
import com.retaileroffers.rewards.repository.TransactionRepository;
import com.retaileroffers.rewards.service.CustomerService;
import com.retaileroffers.rewards.service.serviceImpl.RewardsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RewardsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    private CustomerDto customerDto;
    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setName("John Doe");

        transaction1 = new Transaction();
        transaction1.setTransactionDate(LocalDate.of(2024, 2, 10));
        transaction1.setPoints(50);

        transaction2 = new Transaction();
        transaction2.setTransactionDate(LocalDate.of(2024, 2, 15));
        transaction2.setPoints(100);
    }

    @Test
    void testGetRewards_Success() {
        // Mock dependencies
        when(customerService.getCustomerById(1L)).thenReturn(customerDto);
        when(transactionRepository.getByCustomerId(1L)).thenReturn(Arrays.asList(transaction1, transaction2));

        // Call method
        RewardsDto result = rewardsService.getRewards(1L);

        // Assertions
        assertNotNull(result);
        assertEquals(150, result.getTotalRewards());
        assertEquals("John Doe", result.getCustomerName());
        assertFalse(result.getRewardPerMonth().isEmpty());

        // Verify interactions
        verify(transactionRepository, times(1)).getByCustomerId(1L);
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    void testGetRewards_CustomerNotFound() {
        // Mock customer not found scenario
        when(customerService.getCustomerById(1L)).thenThrow(new CustomerNotFoundException("Customer not found"));

        // Verify exception
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> rewardsService.getRewards(1L));
        assertEquals("Customer not found", exception.getMessage());

        verify(customerService, times(1)).getCustomerById(1L);
        verify(transactionRepository, never()).getByCustomerId(anyLong());
    }

    @Test
    void testGetRewards_NoTransactions() {
        // Mock customer found but no transactions
        when(customerService.getCustomerById(1L)).thenReturn(customerDto);
        when(transactionRepository.getByCustomerId(1L)).thenReturn(Collections.emptyList());

        // Verify exception
        Exception exception = assertThrows(TransactionNotFoundException.class, () -> rewardsService.getRewards(1L));
        assertEquals("No transaction found for customer John Doe", exception.getMessage());

        verify(customerService, times(1)).getCustomerById(1L);
        verify(transactionRepository, times(1)).getByCustomerId(1L);
    }

    @Test
    void testCalculateRewards() {
        // Test different scenarios
        assertEquals(90, rewardsService.calculateRewards(120)); // 70 over 50 → 90 points
        assertEquals(50, rewardsService.calculateRewards(100)); // 50 over 50 → 50 points
        assertEquals(10, rewardsService.calculateRewards(60));  // 10 over 50 → 10 points
        assertEquals(0, rewardsService.calculateRewards(40));   // No rewards under 50
    }
}

