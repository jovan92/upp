package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.Roles;
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
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	public User delete(String email) {
		User user = userRepository.findByEmail(email);
		
		userRepository.delete(user);
		return user;
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
	
	public List<User> findByRoles(Roles roles) {
		return userRepository.findByRoles(roles);
	}
}
