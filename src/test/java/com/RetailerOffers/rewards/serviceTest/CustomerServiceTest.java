package com.RetailerOffers.rewards.serviceTest;
import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.CustomerResponseDto;
import com.retaileroffers.rewards.entity.Customer;
import com.retaileroffers.rewards.exception.CustomerNotFoundException;
import com.retaileroffers.rewards.mapper.CustomerMapper;
import com.retaileroffers.rewards.repository.CustomerRepository;

import com.retaileroffers.rewards.service.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");

        customerDto = new CustomerDto();
        customerDto.setName("John Doe");
        customerDto.setEmail("john.doe@example.com");
        customerDto.setPhoneNumber("1234567890");
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDto> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById_CustomerExists() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDto result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCustomerById_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
        assertEquals("Customer not found id 1", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerResponseDto result = customerService.saveCustomer(customerDto);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
