package com.api.todolist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "admin_user", nullable = false)
    private Boolean admin = false;

    @OneToMany(targetEntity=Task.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    public User(String userName, String password, Boolean admin) {
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }
}
