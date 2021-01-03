package com.example.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.dtos.LoginDTO;
import com.example.app.services.LoginService;

@Controller
@RequestMapping("/api/login")
public class LoginController {

	Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
		logger.info("Start login user.");

		Object response = loginService.login(loginDTO);
		logger.info("Finished login user = [].");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
