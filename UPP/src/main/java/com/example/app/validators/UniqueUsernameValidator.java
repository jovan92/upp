package com.example.app.validators;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.app.models.User;
import com.example.app.repositories.UserRepository;
import com.example.app.services.ServiceUtils;
import com.example.app.services.UserService;
import com.example.app.services.UsernameType;

public class UniqueUsernameValidator implements FormFieldValidator {

	@Autowired
    private UserRepository userRepository;
	
	@Override
    public boolean validate(Object o, FormFieldValidatorContext formFieldValidatorContext) {
		
		return true;
    }
}
