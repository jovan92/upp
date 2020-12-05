package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Ovde se radi kompletna biznis logika
 * Primjer je jedna obicna lista koja se pogodi prilikom pokretanja
 * @author X
 *
 */

@Service
public class TestService {

	public List<String> test = new ArrayList<String>();

	public List<String> getTestMethod() {
		test.add("Test da li app rad");
		test.add("Jovan Popovic, Novica Nikolic, Dejan Jovanovic");
		
		return test;
	}

	public List<String> setTestMethod(String newString) {
		test.add(newString);
		return test;
	}

	public List<String> updateTestMethod(String newText, int index) {
		test.add(index, newText);
		return test;
	}

	public List<String> deleteTestMethod(int index) {
		test.remove(index);
		return test;
	}
	
}
