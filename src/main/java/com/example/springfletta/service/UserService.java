package com.example.springfletta.service;

import org.springframework.stereotype.Service;

import com.example.springfletta.repository.UserRepository;

//@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    

}
