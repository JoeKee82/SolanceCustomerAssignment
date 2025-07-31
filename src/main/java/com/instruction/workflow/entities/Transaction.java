package com.instruction.workflow.entities;

import com.instruction.workflow.enums.Country;
import com.instruction.workflow.enums.Currency;
import com.instruction.workflow.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnId;

    @Enumerated(EnumType.STRING)
    private Currency solanceFrom;

    @Enumerated(EnumType.STRING)
    private Currency solanceTo;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime dateTimeEntered;

    @Enumerated(EnumType.STRING)
    private Country originatingCountry;

    private double amountBuy;
    private double amountSell;
    private String beneficiaryId; // same as Customer userId
    private String userId;
    private String beneficiaryIban;
    private String depositorIban;
    private String paymentRef;
    private String purposeRef;
    private double rate;
    private String timePlaced;

    @PrePersist
    void addTimestamp() {
        dateTimeEntered = LocalDateTime.now();
    }
}