package com.example.app.services;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl {

	Logger logger = LogManager.getLogger(MailServiceImpl.class);

	@Autowired
	private final JavaMailSender senderMail;

	public MailServiceImpl(JavaMailSender sender) {
		this.senderMail = sender;
	}

	@Async("emailExecutor")
	public void sendMail(String subject, String text, String string) {
		// TODO Auto-generated method stub
		MimeMessagePreparator message = newMessage -> {
			newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(string));
			newMessage.setFrom("${spring.mail.username}");
			newMessage.setSubject(subject);
			newMessage.setText(text);
		};
		senderMail.send(message);

	}

}
