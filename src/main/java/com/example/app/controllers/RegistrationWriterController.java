package com.example.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.services.HelperService;

@Controller
@RequestMapping("/api/registrationWriter")
public class RegistrationWriterController {

	private String procesName = "Process_Create_Writer";
	
	Logger logger = LogManager.getLogger(RegistrationWriterController.class);
	
	private HelperService helperService;
	
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
}
