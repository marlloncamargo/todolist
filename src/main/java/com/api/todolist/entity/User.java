package com.api.todolist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "admin_user", nullable = false)
    private Boolean admin = false;

    public User(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
}
