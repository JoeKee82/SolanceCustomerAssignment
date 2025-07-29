package com.solance.workflow.interfaces;

import com.solance.workflow.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(long id); // throws SQLException ?

    void delete(long id);

    Customer save(T t);

    Customer update(T t);

    List<T> getall();
}
