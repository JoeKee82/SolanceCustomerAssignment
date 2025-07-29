package com.solance.workflow.controllers;

import com.solance.workflow.enums.Currency;
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
    public String getCustomer(@PathVariable("userId") long userId) {
        Optional<Customer> customer = customerDAOImpl.get(userId);
        return customer.map(Customer::toString).orElse("Customer not found");
    }

    @DeleteMapping("/customer/{userId}")
    public String deleteCustomer(@PathVariable("userId") long userId) {
        log.info("Deleting customer with userId {}", userId);
        customerDAOImpl.delete(userId);
        return "Customer Deleted";
    }

    @PatchMapping("/customer/{userId}")
    public String openCustomerAccount(@PathVariable("userId") long userId) {
        Optional<Customer> saved = customerDAOImpl.get(userId);

       if(saved.isPresent()) {
           Customer customerToOpenAccount = saved.get();
           customerToOpenAccount.setAccountStatus(CustomerAccountStatus.ACTIVE);
           customerDAOImpl.update(customerToOpenAccount);
           log.info("Opened customer account with ID {}", userId);
           return customerToOpenAccount.getAccountStatus().name();
       } else {
           throw new CustomerNotFoundException("Customer with id " + userId + " not found!");
       }
    }

    @PutMapping("/customer")
    public long registerCustomer(@RequestBody Customer customer) {
        if (Currency.findByName(customer.getCurrency().name())) {
            Customer registeredCustomer = customerDAOImpl.save(customer);
            log.info("Registered customer with ID {}", registeredCustomer.getUserId());
            return registeredCustomer.getUserId();
        }

        return 0; // Indicates failed registration
    }

}