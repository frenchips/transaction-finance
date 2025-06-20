package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchReportDto {
    private String type;
    private Double totalTranscation;
    private Double totalAmount;
}
