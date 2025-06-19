package com.example.test.controller;

import com.example.test.request.AddUserRequest;
import com.example.test.request.UpdateUserRequest;
import com.example.test.response.Response;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<Response> saveUser(@RequestBody AddUserRequest request){
        return userService.saveUser(request);
    }

    @GetMapping("/fetchAllUser")
    public ResponseEntity<Response> fetchAll(){
        return userService.findAllUser();
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Response> getUserById(@RequestParam UUID id){
        return userService.getUserId(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Response> update(@RequestParam UUID id, @RequestBody UpdateUserRequest request){
        return userService.updateUser(id, request);
    }
}
