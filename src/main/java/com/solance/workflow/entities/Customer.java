package com.solance.workflow.entities;

import com.solance.workflow.enums.Currency;
import com.solance.workflow.enums.CustomerAccountStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Customer {

    public Customer() {
    }

    public Customer(long id, CustomerAccountStatus accountStatus, String userId, String iban, double balance) {
        this.id = id;
        this.accountStatus = accountStatus;
        this.userId = userId;
        this.iban = iban;
        this.balance = balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)// Force h2 to use String rather than ordinal
    private CustomerAccountStatus accountStatus;

    private String userId; //  todo: make unique
    private String iban;
    private double balance;

}
