package com.solance.workflow.entities;

import com.solance.workflow.enums.Currency;
import com.solance.workflow.enums.OperationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnId;

    @Enumerated(EnumType.STRING)
    private Currency solanceFrom; // This could also be flat file, or retrieved from an external source

    @Enumerated(EnumType.STRING)
    private Currency solanceTo;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime timePlaced;

    private double amount;
    private String beneficiaryId; // same as Customer userId
    private String userId;
    private String beneficiaryIban;
    private String depositorIban;
    private String paymentRef;
    private String purposeRef;
    private double rate;

    @PrePersist
    void addTimestamp() {
        timePlaced = LocalDateTime.now();
    }
}