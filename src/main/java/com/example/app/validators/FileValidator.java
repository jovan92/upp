package com.example.app.validators;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;

public class FileValidator implements FormFieldValidator {

	@Override
	public boolean validate(Object o, FormFieldValidatorContext formFieldValidatorContext) {

		return true;
	}
}
