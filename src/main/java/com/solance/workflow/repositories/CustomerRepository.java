package com.solance.workflow.repositories;

import com.solance.workflow.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByUserId(String userId);

    void deleteByUserId(String userId);

}

