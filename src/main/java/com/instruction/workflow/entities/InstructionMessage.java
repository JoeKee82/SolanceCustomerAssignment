package com.instruction.workflow.entities;

import com.instruction.workflow.enums.Country;
import com.instruction.workflow.enums.Currency;
import com.instruction.workflow.enums.OperationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Not being stored currently but could be if needed
@Data
@Entity
@NoArgsConstructor
public class InstructionMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnId;

    @Enumerated(EnumType.STRING)
    private Currency solanceFrom; // This could also be flat file, or retrieved from an external source

    @Enumerated(EnumType.STRING)
    private Currency solanceTo;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private Country originatingCountry;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime dateTimeEntered;

    private String userId;
    private double amountBuy;
    private double amountSell;
    private double balance;
    private String beneficiaryId;
    private String depositorId;
    private String beneficiaryIban;
    private String depositorIban;
    private double rate;
    private String paymentRef;
    private String purposeRef;
    private String timePlaced;

    @PrePersist
    void addTimestamp() {
        dateTimeEntered = LocalDateTime.now();
}

}
