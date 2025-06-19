package com.example.test.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class SaveAccountResponse {
    private UUID accountId;
    private UUID userId;
    private Double balance;
    private Timestamp createAt;
}
