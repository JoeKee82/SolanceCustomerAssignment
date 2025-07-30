package com.instruction.workflow.controllers;

import com.instruction.workflow.entities.Customer;
import com.instruction.workflow.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customer/{userId}")
    public String getCustomer(@PathVariable("userId") String userId) {
        return customerService.getCustomer(userId);
    }

    @PatchMapping("/customer/{userId}")
    public String openCustomerAccount(@PathVariable("userId") String userId) {
        return customerService.openCustomerAccount(userId);
    }

    @PutMapping("/customer")
    public String registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

}