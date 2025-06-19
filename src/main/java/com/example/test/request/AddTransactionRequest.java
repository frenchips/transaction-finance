package com.example.test.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddTransactionRequest {
    private UUID accountId;
    private String type;
    private Double amount;
}
