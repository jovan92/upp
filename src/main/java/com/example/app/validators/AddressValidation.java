package com.example.app.validators;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;

public class AddressValidation implements FormFieldValidator {

	@Override
	public boolean validate(Object o, FormFieldValidatorContext formFieldValidatorContext) {

		String input = (String) o;

		try {
			String[] inputs = input.split(",");

			if (inputs.length != 3) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
