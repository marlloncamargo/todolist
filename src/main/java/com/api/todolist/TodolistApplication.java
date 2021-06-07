package com.api.todolist;

import com.api.todolist.entity.User;
import com.api.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class TodolistApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Encode password
		String encodedPassword = passwordEncoder.encode("123456");

		// Create users
		List<User> users = List.of(
				new User("admin", encodedPassword, Boolean.TRUE),
				new User("user", encodedPassword, Boolean.FALSE));

		// Save users into h2 database
		repository.saveAll(users);
	}
}
