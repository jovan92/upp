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

	private String procesName = "Process_Create_User";

	Logger logger = LogManager.getLogger(RegistrationController.class);

	@Autowired
	private HelperService registrationService;

	@Autowired
	private GenresService genresService;

	/**
	 * 
	 * @return All input type
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getForms() {
		logger.info("Start creating registration forms.");

		Object response = registrationService.getForms(procesName);
		logger.info("Finished create registration forms.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Find all genres
	 * 
	 * @return
	 */
	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public ResponseEntity<Object> getGenres() {
		logger.info("Start find all genres.");

		Object response = genresService.getGenres("Process_Create_User");
		logger.info("Finished find all genres.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	@RequestMapping(value = "/validation", method = RequestMethod.POST)
	public ResponseEntity<Object> validation(@RequestBody Object object) {
		logger.info("Stared method validation()");
		System.out.println(object);
		// Object response = registrationService.save("Process_Create_User");
		return new ResponseEntity<>(object, HttpStatus.OK);
	}

	/**
	 * Add new genre
	 * 
	 * @param newGenres
	 * @return
	 */
	@RequestMapping(value = "/newGenre", method = RequestMethod.POST)
	public ResponseEntity<Object> newGenre(@RequestBody GenresDTO newGenres) {
		logger.info("Start create new genre.");

		int n = 10000 + new Random().nextInt(90000);
		newGenres.setVin(newGenres.getName() + "_" + n);
		Object response = genresService.addNewGenres(newGenres);
		logger.info("Finished create new genre.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Verification user profile
	 * 
	 * @param tokenDTO
	 * @return
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public ResponseEntity<Object> verify(@RequestBody TokenDTO tokenDTO) {
		logger.info("Stare verify user profile.");

		Object response = registrationService.verify(tokenDTO);
		logger.info("Finished verify user profile.");
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

		Object response = registrationService.save(procesName, procesId, formSubmissionDto);
		logger.info("Finished save new user in db");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
