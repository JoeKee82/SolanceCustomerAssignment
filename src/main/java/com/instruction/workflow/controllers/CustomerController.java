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
    public Customer getCustomer(@PathVariable("userId") String userId) {
        Customer customer = customerService.getCustomer(userId);
        return customerService.getCustomer(userId);
    }

    @PatchMapping("/customer/{userId}")
    public String openCustomerAccount(@PathVariable("userId") String userId) {
        return customerService.openCustomerAccount(userId);
    }

    @PatchMapping("/customer/batch")
    public String openCustomerAccounts(@RequestBody List<String> userIds) {
        userIds.stream().forEach(userId -> customerService.openCustomerAccount(userId));
        return "Customer accounts open";
    }

    @PutMapping("/customer")
    public String registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

    @PutMapping("/customer/batch")
    public String registerCustomers(@RequestBody List<Customer> customers) {
        customers.stream().forEach(customer -> customerService.registerCustomer(customer));
      return "Customers registered";
    }

    @DeleteMapping("/customer/{userId}")
    public void removeCustomer(@PathVariable("userId") String userId) {
        customerService.removeCustomer(userId);
    }

}