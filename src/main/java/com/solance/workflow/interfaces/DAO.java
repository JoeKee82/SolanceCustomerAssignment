package com.solance.workflow.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    void delete(String id);

    Optional<T> get(String id);

    List<T> getall();

    T save(T t);

    T update(T t);

}
