package com.retaileroffers.rewards.controller;

import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.CustomerResponseDto;
import com.retaileroffers.rewards.entity.Customer;
import com.retaileroffers.rewards.exception.CustomerNotFoundException;
import com.retaileroffers.rewards.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Validated
@Tag(name = "Customer API", description = "Endpoints for managing customers") // Tag for Swagger grouping
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers.")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieve a single customer by their ID.")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping
    @Operation(summary = "Add a new customer", description = "Create and save a new customer record.")
    public ResponseEntity<CustomerResponseDto> addCustomer(@Valid @RequestBody CustomerDto customer) {
        return ResponseEntity.ok().body(customerService.saveCustomer(customer));
    }
}
