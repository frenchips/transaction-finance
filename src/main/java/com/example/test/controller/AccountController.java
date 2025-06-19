package com.example.test.controller;

import com.example.test.request.AddAccountRequest;
import com.example.test.response.Response;
import com.example.test.response.SaveAccountResponse;
import com.example.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/saveAccount")
    public ResponseEntity<Response> saveAccount(@RequestBody AddAccountRequest request){
        return accountService.saveAccount(request);
    }

    @GetMapping("/fetchAccount")
    public ResponseEntity<Response> fetchAccount(){
        return accountService.findAccount();
    }
}
