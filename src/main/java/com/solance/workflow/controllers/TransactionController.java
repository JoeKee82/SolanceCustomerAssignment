package com.solance.workflow.controllers;

import com.solance.workflow.entities.Customer;
import com.solance.workflow.entities.Transaction;
import com.solance.workflow.enums.CustomerAccountStatus;
import com.solance.workflow.enums.OperationType;
import com.solance.workflow.exceptions.AccountStatusException;
import com.solance.workflow.exceptions.CustomerNotFoundException;
import com.solance.workflow.exceptions.InsufficientFundsException;
import com.solance.workflow.interfaces.CustomerDAO;
import com.solance.workflow.interfaces.TransactionDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    CustomerDAO customerDAOImpl;

    @Autowired
    TransactionDAO transactionDAOImpl;

    @GetMapping("/transaction")
    public List<Transaction> getTransactions() {
        return transactionDAOImpl.getall();
    }

    @PutMapping("/transaction")
    public String updateCustomerAccount(@RequestBody Transaction txn) {
        Optional<Customer> customerAccount = customerDAOImpl.findByUserId(txn.getUserId());
        if(customerAccount.isPresent()) {
            Customer customerAccountToUpdate = customerAccount.get();
            if(customerAccountToUpdate.getAccountStatus().equals(CustomerAccountStatus.ACTIVE)) {
                double previousBalance = customerAccountToUpdate.getBalance();

                switch (txn.getOperationType()) {
                    case OperationType.DEPOSIT -> {
                        customerAccountToUpdate.setBalance(previousBalance + txn.getAmount());
                        customerDAOImpl.update(customerAccountToUpdate);
                        transactionDAOImpl.save(txn);
                    }
                    case OperationType.WITHDRAWAL -> {
                        customerAccountToUpdate.setBalance(previousBalance - txn.getAmount());
                        customerDAOImpl.update(customerAccountToUpdate);
                        transactionDAOImpl.save(txn);
                    }
                }

                log.info("[TXN] Updated customer account {} balance now {} {}",
                        txn.getUserId(), customerAccountToUpdate.getBalance(), customerAccountToUpdate.getCurrency());
                return String.valueOf(customerAccountToUpdate.getBalance());
            } else {
                throw new AccountStatusException("Customer account not active!");
            }
        } else {
            throw new CustomerNotFoundException("Customer with id " + txn.getUserId() + " not found!");
        }
    }

    @PatchMapping("/transaction")
    public String transferToBenificiaryAccount(@RequestBody Transaction txn) {
        Optional<Customer> customerAccountFrom = customerDAOImpl.findByUserId(txn.getUserId());
        Optional<Customer> customerAccountTo = customerDAOImpl.findByUserId(txn.getBeneficiaryId());

        if (customerAccountFrom.isPresent() && customerAccountTo.isPresent()) {
            Customer tempCustomerAccFrom = customerAccountFrom.get();
            Customer tempCustomerAccTo = customerAccountTo.get();

            // ASSUMPTION: Can only transfer into active accounts
            if(tempCustomerAccFrom.getAccountStatus().equals(CustomerAccountStatus.ACTIVE)
            && tempCustomerAccTo.getAccountStatus().equals(CustomerAccountStatus.ACTIVE)) {

                if (tempCustomerAccFrom.getBalance() >= txn.getAmount()) {
                    updateAccounts(txn.getAmount(), tempCustomerAccTo, tempCustomerAccFrom);
                } else {
                    throw new InsufficientFundsException("[TXN] Customer account with id " + txn.getUserId() +
                            " does not have funds for this transaction. Balance is " + tempCustomerAccFrom.getBalance());
                }

                transactionDAOImpl.save(txn);
                return String.valueOf(tempCustomerAccTo.getBalance());
            } else {
                throw new AccountStatusException("Customer account not active!");
            }
        } else {
            throw new CustomerNotFoundException("Customer not found!");
        }
    }

    private void updateAccounts(double fundAmount, Customer tempCustomerAccTo, Customer tempCustomerAccFrom) {
        double previousBalanceTo = tempCustomerAccTo.getBalance();
        tempCustomerAccTo.setBalance(previousBalanceTo + fundAmount);
        customerDAOImpl.update(tempCustomerAccTo);

        double previousBalanceFrom = tempCustomerAccFrom.getBalance();
        tempCustomerAccFrom.setBalance(previousBalanceFrom - fundAmount);
        customerDAOImpl.update(tempCustomerAccFrom);

        log.info("[TRANS] Transferred {} {} customer account {} to customer account {}",
                fundAmount, tempCustomerAccTo.getCurrency(), tempCustomerAccFrom.getUserId(), tempCustomerAccTo.getUserId());
    }
}
