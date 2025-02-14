package com.retaileroffers.rewards.service;

import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.CustomerResponseDto;
import com.retaileroffers.rewards.exception.CustomerNotFoundException;
import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id)throws CustomerNotFoundException;
    CustomerResponseDto saveCustomer(CustomerDto customer);
}
