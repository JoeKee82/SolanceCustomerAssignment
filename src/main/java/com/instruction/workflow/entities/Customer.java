package com.instruction.workflow.entities;

import com.instruction.workflow.enums.Currency;
import com.instruction.workflow.enums.CustomerAccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)// Force h2 to use String rather than ordinal
    private CustomerAccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String userId;
    private String iban;
    private double balance;

}
