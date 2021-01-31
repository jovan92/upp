package com.example.app.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.User;

@Service
public class TimeOutService implements JavaDelegate {

	Logger logger = LogManager.getLogger(TimeOutService.class);
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	MailServiceImpl mailServiceImpl;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Stared method TimeOutService");
		System.out.println("Dosao ovde jebem mu mater u PM1M");
		String email = execution.getVariable("email").toString();
		System.out.println(email);
		
		User user = userServiceImpl.delete(email);
		String text = "Vas nalog je obrisan zato sto niste ga aktivirali u datom roku! Jebite se pederi!!!!";
		String subject = "Luzeru";
		
		mailServiceImpl.sendMail(subject, text, email);
		logger.info("End method TimeOutService");
	}
}
