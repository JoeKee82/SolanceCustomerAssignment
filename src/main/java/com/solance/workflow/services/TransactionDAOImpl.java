package com.solance.workflow.services;

import com.solance.workflow.entities.Transaction;
import com.solance.workflow.interfaces.TransactionDAO;
import com.solance.workflow.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionDAOImpl implements TransactionDAO {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void delete(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Optional<Transaction> get(String id) {
       return transactionRepository.findById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getall() {
        return transactionRepository.findAll();
    }

}
