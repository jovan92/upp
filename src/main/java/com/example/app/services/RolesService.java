package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.Roles;
import com.example.app.repositories.RoleRepository;

@Service
public class RolesService {

	@Autowired
	RoleRepository roleRepository;

	public Roles findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	
}
