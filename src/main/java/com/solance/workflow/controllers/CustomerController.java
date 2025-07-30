package com.solance.workflow.controllers;

import com.solance.workflow.enums.CustomerAccountStatus;
import com.solance.workflow.exceptions.CustomerNotFoundException;
import com.solance.workflow.interfaces.CustomerDAO;
import com.solance.workflow.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    CustomerDAO customerDAOImpl;

    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return customerDAOImpl.getall();
    }

    @GetMapping("/customer/{userId}")
    public String getCustomer(@PathVariable("userId") String userId) {
        Optional<Customer> customer = customerDAOImpl.findByUserId(userId);
        return customer.map(Customer::toString).orElse("Customer not found");
    }

    @PatchMapping("/customer/{userId}")
    public String openCustomerAccount(@PathVariable("userId") String userId) {
        Optional<Customer> customerAccount = customerDAOImpl.findByUserId(userId);

       if(customerAccount.isPresent()) {
           Customer customerAccountToUpdate = customerAccount.get();
           customerAccountToUpdate.setAccountStatus(CustomerAccountStatus.ACTIVE);
           customerDAOImpl.update(customerAccountToUpdate);
           log.info("[CUST] Opened customer account with ID {}", userId);
           return customerAccountToUpdate.getAccountStatus().name();
       } else {
           log.error("[CUST] Customer with id {} not found!", userId);
           throw new CustomerNotFoundException("[CUST] Customer with id " + userId + " not found!");
       }
    }

    @PutMapping("/customer")
    public String registerCustomer(@RequestBody Customer customer) {
        Customer registeredCustomer = customerDAOImpl.save(customer);
        log.info("[CUST] Registered customer with ID {}", registeredCustomer.getUserId());
        return registeredCustomer.getUserId();
    }

}