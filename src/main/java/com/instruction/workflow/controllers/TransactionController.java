package com.instruction.workflow.controllers;

import com.instruction.workflow.entities.Transaction;
import com.instruction.workflow.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transaction")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @PutMapping("/transaction")
    public String updateCustomerAccount(@RequestBody Transaction txn) {
        return transactionService.updateCustomerAccount(txn);
    }

    @PatchMapping("/transaction")
    public String transferToBenificiaryAccount(@RequestBody Transaction txn) {
       return transactionService.transferToBenificiaryAccount(txn);
    }

}
