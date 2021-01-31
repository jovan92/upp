package com.example.app.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SocketIO implements JavaDelegate {
	Logger logger = LogManager.getLogger(SocketIO.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Stared SocketIO");
	}
}
