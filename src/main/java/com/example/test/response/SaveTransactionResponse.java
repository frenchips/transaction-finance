package com.example.test.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class SaveTransactionResponse {
    private UUID id;
    private UUID accountId;
    private String type;
    private Double amount;
    private Timestamp createAt;
}
