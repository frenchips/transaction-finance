package com.example.test.service;

import com.example.test.request.AddUserRequest;
import com.example.test.request.UpdateUserRequest;
import com.example.test.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<Response> saveUser(AddUserRequest request);
    ResponseEntity<Response> findAllUser();
    ResponseEntity<Response> getUserId(UUID id);
    ResponseEntity<Response> updateUser(UUID id, UpdateUserRequest request);
}
