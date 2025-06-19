package com.example.test.service;

import com.example.test.request.AddAccountRequest;
import com.example.test.response.Response;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<Response> saveAccount(AddAccountRequest request);
    ResponseEntity<Response> findAccount();
}
