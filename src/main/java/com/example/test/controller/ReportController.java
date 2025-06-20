package com.example.test.controller;

import com.example.test.request.FetchReportRequest;
import com.example.test.response.Response;
import com.example.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/fetchReport")
    public ResponseEntity<Response> fetchReport(@RequestBody FetchReportRequest request){
        return transactionService.report(request);
    }
}
