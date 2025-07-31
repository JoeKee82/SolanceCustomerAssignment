package com.instruction.workflow.services;

import com.instruction.workflow.dao.CustomerDAO;
import com.instruction.workflow.entities.Customer;
import com.instruction.workflow.enums.CustomerAccountStatus;
import com.instruction.workflow.exceptions.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAOImpl;

    public List<Customer> getCustomers() {
        List<Customer> customers = customerDAOImpl.getall();
        customers.stream().forEach(customer -> {
            customer.setBalance(
                    Math.round(customer.getBalance())
            );
        });
        return customers;
    }

    public Customer getCustomer(String userId) {
        Optional<Customer> customer = customerDAOImpl.findByUserId(userId);
        if(customer.isPresent()) {
            Customer customerToUpdate = customer.get();
            customerToUpdate.setBalance(
                    Math.round(customerToUpdate.getBalance())
            );
            return customerToUpdate;
        }

        return null;
    }

    public String openCustomerAccount(String userId) {
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

    public String registerCustomer(Customer customer) {
        Customer registeredCustomer = customerDAOImpl.save(customer);
        log.info("[CUST] Registered customer with ID {}", registeredCustomer.getUserId());
        return registeredCustomer.getUserId();
    }

    public void removeCustomer(String userId) {
        log.info("[CUST] Removing customer with ID {}", userId);
        customerDAOImpl.deleteByUserId(userId);
    }
}
