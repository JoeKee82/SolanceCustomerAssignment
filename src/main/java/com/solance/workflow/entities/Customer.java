package com.solance.workflow.entities;

import com.solance.workflow.enums.Currency;
import com.solance.workflow.enums.CustomerAccountStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Enumerated(EnumType.STRING)// Force h2 to use String rather than ordinal
    private CustomerAccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private Currency currency; // This could also be flat file, or retrieved from an external source

    private String iban;
    private double balance;

}
