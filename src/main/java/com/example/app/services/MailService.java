package com.example.app.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService implements JavaDelegate {

	Logger logger = LogManager.getLogger(MailService.class);

	@Autowired
	private MailServiceImpl mailServiceImpl;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Stared MailService, create mail and call MailServicImpl method.");

		String token = execution.getVariable("verificationToken").toString();
		String processId = execution.getProcessInstanceId();
		String link = String.format("http://localhost:4200/verify/%s/%s", token, processId);
		String text = String.format("<h3>Please click this  <a href=\"%s\"> link </a></h3>", link);

		String subject = "Verification email";

		logger.info("Mail is send.");
		mailServiceImpl.sendMail(subject, text, execution.getVariable("email").toString());
	}
}
