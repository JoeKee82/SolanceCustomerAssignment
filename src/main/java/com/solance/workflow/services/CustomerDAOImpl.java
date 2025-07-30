package com.solance.workflow.services;

import com.solance.workflow.entities.Customer;
import com.solance.workflow.interfaces.CustomerDAO;
import com.solance.workflow.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> get(String id) {
       return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByUserId(String id) {
        return customerRepository.findByUserId(id);
    }

    @Override
    public List<Customer> getall() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

}
