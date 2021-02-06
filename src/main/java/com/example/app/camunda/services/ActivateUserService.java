package com.example.app.camunda.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ActivateUserService implements JavaDelegate {

	Logger logger = LogManager.getLogger(ActivateUserService.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Start ActivationUserService()");
		
		
	}

}
