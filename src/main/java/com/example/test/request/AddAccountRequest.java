package com.example.test.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddAccountRequest {
    private UUID userId;
    private Double initialBalance;
}
