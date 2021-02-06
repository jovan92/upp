package com.example.app.camunda.services;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckFormsService implements JavaDelegate {

	Logger logger = LogManager.getLogger(CheckFormsService.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Check input forms");
		
		HashMap<String, Object> userForms = (HashMap<String, Object>) execution.getVariable("registration");
		
		String firstName = "";
		
		try {
			firstName = userForms.get("firstName").toString();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
