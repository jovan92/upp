package com.example.app.controllers;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.dtos.FormSubmissionDto;
import com.example.app.dtos.GenresDTO;
import com.example.app.dtos.TokenDTO;
import com.example.app.services.GenresService;
import com.example.app.services.HelperService;

@Controller
@RequestMapping("/api/registration")
public class RegistrationController {

	Logger logger = LogManager.getLogger(RegistrationController.class);
	
	@Autowired
	private HelperService registrationService;
	
	@Autowired
	private GenresService genresService;
	
	/**
	 * Citanje iz xml sva polja forme
	 * @return All input type
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getForms() {
		logger.info("Stared method getForms()");
		Object response = registrationService.getForms("Process_Create_User");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public ResponseEntity<Object> getGenres() {
		logger.info("Stared method getGenres()");
		Object response = genresService.getGenres("Process_Create_User");
		System.out.println(response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validation", method = RequestMethod.POST)
	public ResponseEntity<Object> validation(@RequestBody Object object) {
		logger.info("Stared method validation()");
		System.out.println(object);
		// Object response = registrationService.save("Process_Create_User");
		return new ResponseEntity<>(object, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/newGenre", method = RequestMethod.POST)
	public ResponseEntity<Object> newGenre(@RequestBody GenresDTO newGenres) {
		logger.info("Stared method newGenre()");
		int n = 10000 + new Random().nextInt(90000);
		newGenres.setVin(newGenres.getName() + "_" + n);
		//newGenres.setVin(null);
		Object response = genresService.addNewGenres(newGenres);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public ResponseEntity<Object> verify(@RequestBody TokenDTO tokenDTO) {
		logger.info("Stared method verify()");

		Object response = registrationService.verify(tokenDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{procesId}", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody List<FormSubmissionDto> formSubmissionDto, @PathVariable String procesId) {
		logger.info("Stared method save()");
		Object response = registrationService.save("Process_Create_User", procesId, formSubmissionDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
