package com.example.app.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SaveFilesService implements JavaDelegate {

	Logger logger = LogManager.getLogger(SaveFilesService.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		// TODO poslovna logika
		
		
		
		
		execution.setVariable("type", "sendRequest");
		
		// TODO call mail method
	}
}
