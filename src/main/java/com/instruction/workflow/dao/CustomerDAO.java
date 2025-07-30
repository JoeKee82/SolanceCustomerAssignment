package com.instruction.workflow.dao;

import com.instruction.workflow.entities.Customer;

import java.util.Optional;

public interface CustomerDAO extends DAO<Customer> {

    Optional<Customer> findByUserId(String userId);

    // TODO: REMOVE
//    void deleteByUserId(String userId);

}
