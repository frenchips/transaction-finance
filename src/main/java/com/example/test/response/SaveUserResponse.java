package com.example.test.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class SaveUserResponse {
    private UUID id;
    private String name;
    private String email;
    private Timestamp createAt;
}
