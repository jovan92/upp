package com.example.app.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.dtos.FormSubmissionDto;
import com.example.app.dtos.ResponderHendlerDTO;
import com.example.app.dtos.TokenDTO;
import com.example.app.services.HelperService;
import com.example.app.services.UserWriterRegistrationService;

@Controller
@RequestMapping("/api/registrationWriter")
public class RegistrationWriterController {

	private String procesName = "Process_Create_Writer";
	
	Logger logger = LogManager.getLogger(RegistrationWriterController.class);
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
	private UserWriterRegistrationService userWriterRegistrationService;
	
	/**
	 * Koristimo iz HelperService class istu metodu za sve forme. 
	 * Samo treba proslediti odgovarajuci ID procesa.
	 * @return All input type
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getForms() {
		logger.info("Start creating registration writer forms.");

		Object response = helperService.getForms(procesName);
		logger.info("Finished create registration writer forms.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param formSubmissionDto
	 * @param procesId
	 * @return
	 */
	@RequestMapping(value = "/{procesId}", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody List<FormSubmissionDto> formSubmissionDto,
			@PathVariable String procesId) {
		logger.info("Start save new user in db");
		
		Object response = helperService.save(procesName, procesId, formSubmissionDto);
		logger.info("Finished save new user in db");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveFiles/{procesId}", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@PathVariable String procesId) {
		logger.info("Start save files in folder.");
		
		// TODO Logika skidanja bajt koda koji nosi informaciju o fajlovima
		// TODO Logika parsiranja bajta i slanja dalje
		Object response = userWriterRegistrationService.saveFiles(); // prosljedjujemo taj bajt kod 
		logger.info("Finished save new user in db");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
