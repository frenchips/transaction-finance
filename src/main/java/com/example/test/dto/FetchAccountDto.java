package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class FetchAccountDto {
    private UUID id;
    private UUID userId;
    private Double balance;
    private Timestamp createAt;
}
