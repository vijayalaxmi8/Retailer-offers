package com.retaileroffers.rewards.service.serviceImpl;

import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.CustomerResponseDto;
import com.retaileroffers.rewards.entity.Customer;
import com.retaileroffers.rewards.exception.CustomerNotFoundException;
import com.retaileroffers.rewards.mapper.CustomerMapper;
import com.retaileroffers.rewards.repository.CustomerRepository;
import com.retaileroffers.rewards.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers= customerRepository.findAll();
        return customers.stream().map(n->CustomerMapper.mapToDto(n,new CustomerDto())).toList();
    }

    @Override
    public CustomerDto getCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found id "+id));
         return CustomerMapper.mapToDto(customer,new CustomerDto());
    }

    @Override
    public CustomerResponseDto saveCustomer(CustomerDto customerDto) {
        return CustomerMapper
                .mapToDto(customerRepository.save(CustomerMapper.mapFromDto(customerDto,new Customer())),new CustomerResponseDto());

    }
}
