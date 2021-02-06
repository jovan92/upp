package com.example.app.camunda.services;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.CheckRequest;
import com.example.app.models.Files;
import com.example.app.models.Roles;
import com.example.app.models.User;
import com.example.app.repositories.FilesRepository;
import com.example.app.services.CheckRequestImplService;
import com.example.app.services.RolesService;
import com.example.app.services.UserServiceImpl;

@Service
public class SaveFilesService implements JavaDelegate {

	Logger logger = LogManager.getLogger(SaveFilesService.class);
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	FilesRepository filesRepository;
	
	@Autowired
	CheckRequestImplService checkRequestImplService;
	
	@Autowired
	RolesService rolesService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		HashMap<String, Object> review = (HashMap<String, Object>) execution.getVariable("review");
		String username = review.get("username").toString();
		
		User user = userServiceImpl.findByUsername(username);
		Roles roles = rolesService.findByName("COMMISSION");
		
		List<User> commision = userServiceImpl.findByRoles(roles);
		
		List<Files> files = filesRepository.findByUser(user);
		
		CheckRequest cr = new CheckRequest(user, commision, files);
		CheckRequest crRes = checkRequestImplService.save(cr);
		
		execution.setVariable("user", user);
		execution.setVariable("crRes", crRes);
		execution.setVariable("requestRevision",commision);
	}
}
