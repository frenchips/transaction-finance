package com.example.test.service;

import com.example.test.request.AddTransactionRequest;
import com.example.test.response.Response;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<Response> addTransaction(AddTransactionRequest request);
}
