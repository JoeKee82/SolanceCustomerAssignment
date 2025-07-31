package com.instruction.workflow.dao;

import com.instruction.workflow.entities.Transaction;

import java.util.List;

public interface TransactionDAO extends DAO<Transaction> {

    List<Transaction> getAllByUserId(String id);

}
