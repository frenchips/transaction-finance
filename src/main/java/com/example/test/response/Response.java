package com.example.test.response;

import lombok.Data;

@Data
public class Response<T>{
    private String message;
    private T data;
}
