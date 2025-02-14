package com.retaileroffers.rewards.mapper;

import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.CustomerResponseDto;
import com.retaileroffers.rewards.entity.Customer;

public class CustomerMapper {
    static public CustomerDto mapToDto(Customer customer, CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

    static public Customer mapFromDto(CustomerDto customerDto,Customer customer){
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setName(customerDto.getName());
        return customer;
    }

    static public CustomerResponseDto mapToDto(Customer customer,CustomerResponseDto customerResponseDto){
        customerResponseDto.setCustomerId(customer.getId());
        customerResponseDto.setName(customer.getName());
        return customerResponseDto;

    }
}
