package com.example.test.controller;

import com.example.test.request.AddTransactionRequest;
import com.example.test.request.SearchRequest;
import com.example.test.response.Response;
import com.example.test.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Response> transaction(@RequestBody AddTransactionRequest request){
        return transactionService.addTransaction(request);
    }

    @PostMapping("/searchTransaction")
    public ResponseEntity<Response> search(@RequestBody @Valid SearchRequest request){
        return transactionService.searchData(PageRequest.of(request.getPageNumber(), request.getPageSize()));
    }
}
