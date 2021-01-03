package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.User;
import com.example.app.repositories.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	public void save(User newUser) {
		// TODO Auto-generated method stub
		userRepository.save(newUser);
	}

	public User findByToken(String token) {
		// TODO Auto-generated method stub
		User user = userRepository.findByHashCode(token);
		user.setIsAcive(true);
		userRepository.save(user);
		return user;
	}

	public void activateProfile(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	public boolean checkUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user == null;
	}

}
