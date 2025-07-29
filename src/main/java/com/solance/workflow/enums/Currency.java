package com.solance.workflow.enums;

public enum Currency {

    EUR("Euro"),
    USD("US Dollar"),
    JPY("Japanese Yen"),
    FR("Swiss Franc");

    Currency(String currencyCode) {
    }

    public static Boolean findByName(String currency) {
        for (Currency curr : values()) {
            if (curr.name().equalsIgnoreCase(currency)) {
                return true;
            }
        }
        return false;
    }

}
