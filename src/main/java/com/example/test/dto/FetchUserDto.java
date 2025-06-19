package com.example.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class FetchUserDto {
    private UUID id;
    private String name;
    private String email;
    private Timestamp createAt;
}
