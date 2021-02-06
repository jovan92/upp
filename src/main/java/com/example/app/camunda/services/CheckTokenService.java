package com.example.app.camunda.services;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.User;
import com.example.app.services.UserServiceImpl;

@Service
public class CheckTokenService implements JavaDelegate {

	Logger logger = LogManager.getLogger(CheckTokenService.class);
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Start task for check token");
		
		HashMap<String, Object> activationUser = (HashMap<String, Object>) execution.getVariable("activationUser");
		String token = activationUser.get("token").toString();
		
		User user = userServiceImpl.findByToken(token);
		
		if (user != null) {
			execution.setVariable("isValidToken", true);
			logger.info("User {" + user.toString() + "} is activated.");
		} else {
			execution.setVariable("isValidToken", false);
			//TODO Kreirati SocketIO istancu
			logger.info("Token {" + token + "} is incorect.");
			throw new Exception("Token is not corect");
		}
		
		//TODOO Next task
		
		
	}
}
