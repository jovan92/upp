package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.repositories.UserRepository;

@Service
public class UsernameType {
	
	@Autowired
	private UserRepository userRepository;

    public boolean checkUniqueUsername(String username) {
        return userRepository.getSystemUserByUsername(username) == null;
    }
}
