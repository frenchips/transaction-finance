package com.example.test.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FetchReportRequest {
    private String type;
    private String startDate;
    private String endDate;
}
