package com.example.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Table(name = "tb_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "create_at")
    private Timestamp createAt;

    @OneToMany(mappedBy = "userId")
    Set<Account> setAccount;
}
