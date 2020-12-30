package com.example.app.validators;

import java.util.HashMap;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;

public class GenresValidator implements FormFieldValidator {

	@Override
    public boolean validate(Object o, FormFieldValidatorContext formFieldValidatorContext) {
		System.out.println("GenresValidator " + o);
        return true;
    }
}
