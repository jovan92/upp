package com.example.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.models.Roles;
import com.example.app.repositories.RoleRepository;

@Service
public class UserService implements JavaDelegate {

	Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	IdentityService identityService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserServiceImpl userServicImpl;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Stared UserService");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		HashMap<String, Object> userForms = (HashMap<String, Object>) execution.getVariable("registration");
		Boolean isBeta = Boolean.parseBoolean(userForms.get("isBeta").toString());
		logger.info("Beta user is: " + isBeta);

		String getRole = isBeta ? "BETAREADER" : "READER";
		List<Roles> role = roleRepository.findByName(getRole);

		String token = UUID.randomUUID().toString();

		com.example.app.models.User newUser = new com.example.app.models.User(userForms.get("firstName").toString(),
				userForms.get("lastName").toString(), userForms.get("username").toString(),
				encoder.encode(userForms.get("password").toString()), isBeta, role, userForms.get("email").toString(), token);

		logger.info(newUser);

		try {
			userServicImpl.save(newUser);
			execution.setVariable("isValid", true);
		} catch (Exception e) {
			execution.setVariable("isValid", false);
			//TODO Kreirati SocketIO istancu
			throw new Exception("Something went wrong");
		}

		User user = identityService.newUser(userForms.get("username").toString());
		user.setPassword(userForms.get("password").toString());
		user.setFirstName(userForms.get("firstName").toString());
		user.setLastName(userForms.get("lastName").toString());
		user.setEmail(userForms.get("email").toString());

		execution.setVariable("email", newUser.getEmail());
		execution.setVariable("verificationToken", token);

		logger.info("UserService is finished, set mail {" + newUser.getEmail().toString()
				+ "} and set verificationToken {" + token.toString() + "}");
	}

}
