package com.example.test.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
    DEBIT("DEBIT"), CREDIT("CREDIT");

    private String val;

    TransactionType(String val) {
        this.val = val;
    }

    @JsonValue
    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val;
    }
}
