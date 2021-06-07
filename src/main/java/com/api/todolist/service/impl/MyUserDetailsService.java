package com.api.todolist.service.impl;

import com.api.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.api.todolist.entity.User user = repository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found for this username: " + username));
        return new User(user.getUsername(), "123456",
                new ArrayList<>());
    }
}