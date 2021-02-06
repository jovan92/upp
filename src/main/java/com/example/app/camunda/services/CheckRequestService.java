package com.example.app.camunda.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.Files;
import com.example.app.models.User;
import com.example.app.repositories.FilesRepository;
import com.example.app.services.MailServiceImpl;

@Service
public class CheckRequestService implements JavaDelegate {

	@Autowired
	MailServiceImpl mailServiceImpl;
	
	@Autowired
	FilesRepository filesRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) execution.getVariable("user");
		List<User> commision = (List<User>) execution.getVariable("requestRevision");
		
		List<Files> files = filesRepository.findByUser(user);
		
		String subject = "Zahtjev za verifikaciju knjiga";
		String text = "Knjige koje je objavio korisnik " + user.getFirstName() + " " + user.getLastName() + "\n";
		text += "Molimo vas pogledajte ove knjige i ocjenite ih: \n";
		
		for (Files f: files) {
			text += "Link -> http://localhost:4200/books/v/" + f.getId() + "\n"; 
		}
		
		for (User u: commision) {
			mailServiceImpl.sendMail(subject, text, u.getEmail());
		}
	}

}
