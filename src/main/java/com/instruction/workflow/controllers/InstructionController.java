package com.instruction.workflow.controllers;

import com.instruction.workflow.entities.Customer;
import com.instruction.workflow.entities.InstructionMessage;
import com.instruction.workflow.entities.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.instruction.workflow.utils.Utils.createCustomerObject;
import static com.instruction.workflow.utils.Utils.getTransactionObject;

@Slf4j
@RestController
public class InstructionController {

    @Autowired
    CustomerController customerController;

    @Autowired
    TransactionController transactionController;

    @PutMapping("/instruction")
    public Transaction instructionWorkflow(@RequestBody InstructionMessage instructionMessage) {
        Customer customer = createCustomerObject(instructionMessage);
        customerController.registerCustomer(customer);
        customerController.openCustomerAccount(customer.getUserId());

        Transaction txn = getTransactionObject(instructionMessage);
        transactionController.updateCustomerAccount(txn);
        transactionController.transferToBenificiaryAccount(txn);

        log.info("[INST] Instruction workflow completed:: " + instructionMessage);
        return txn;
    }

}
