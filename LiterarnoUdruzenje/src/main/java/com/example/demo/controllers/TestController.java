package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.TestService;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {

	@Autowired
	TestService testService;
	
	/**
	 * Povlacimo sve podatke
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getMethod() {
		
		List<String> test = testService.getTestMethod();
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
	/**
	 * Dodajemo novi podatak
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<List<String>> postMethod() {
		
		String newString = "Novi string u listi";
		List<String> test = testService.setTestMethod(newString);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
	/**
	 * Mjenjamo sve podatke
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.PUT)
	public ResponseEntity<List<String>> putMethod() {
		
		String newText = "Ovo su clanovi tima: Jovan Popovic, Novica Nikolic, Dejan Jovanovic";
		int index = 1;
		List<String> test = testService.updateTestMethod(newText, index);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
	/**
	 * Brisemo neki podatak
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.DELETE)
	public ResponseEntity<List<String>> deleteMethod() {
		
		int index = 0;
		List<String> test = testService.deleteTestMethod(index);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
}
