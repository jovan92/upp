package com.example.app.validators;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailValidation implements FormFieldValidator {

	@Override
	public boolean validate(Object o, FormFieldValidatorContext formFieldValidatorContext) {
		String stringMail = (String) o;

		if (stringMail.toString().matches("^(.+)@(.+)$")) {
			return true;
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("isValidError", "mail");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, map.toString(), null);
		}
	}
}
