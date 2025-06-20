package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchTransactionAllDto {
    private String name;
    private Double saldo;
    private String type;
    private Double amount;
}
