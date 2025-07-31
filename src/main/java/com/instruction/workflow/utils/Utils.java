package com.instruction.workflow.utils;

import com.instruction.workflow.entities.Customer;
import com.instruction.workflow.entities.InstructionMessage;
import com.instruction.workflow.entities.Transaction;

// Time  permitting would add mapper for these objects
public class Utils {

    public static Customer createCustomerObject(InstructionMessage instructionMessage) {
        // ASSUMPTION: Benefactor account already registered and active
        return Customer.builder()
                .userId(instructionMessage.getUserId())
                .currency(instructionMessage.getCurrency())
                .iban(instructionMessage.getDepositorIban())
                .balance(instructionMessage.getBalance())
                .build();
    }

    public static Transaction getTransactionObject(InstructionMessage instructionMessage) {
        return Transaction.builder()
                .amountBuy(instructionMessage.getAmountBuy())
                .amountSell(instructionMessage.getAmountSell())
                .rate(instructionMessage.getRate())
                .paymentRef(instructionMessage.getPaymentRef())
                .purposeRef(instructionMessage.getPurposeRef())
                .beneficiaryId(instructionMessage.getBeneficiaryId())
                .depositorIban(instructionMessage.getDepositorIban())
                .userId(instructionMessage.getUserId())
                .operationType(instructionMessage.getOperationType())
                .beneficiaryIban(instructionMessage.getBeneficiaryIban())
                .solanceFrom(instructionMessage.getSolanceFrom())
                .solanceTo(instructionMessage.getSolanceTo())
                .timePlaced(instructionMessage.getTimePlaced())
                .originatingCountry(instructionMessage.getOriginatingCountry())
                .build();
    }
}
