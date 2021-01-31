package com.example.app.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.app.dtos.LoginDTO;
import com.example.app.dtos.ResponderHendlerDTO;
import com.example.app.dtos.UserDTO;
import com.example.app.models.User;
import com.example.app.repositories.UserRepository;
import com.example.app.security.TokenUtils;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired(required=true)
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtils tokenUtils;

	@SuppressWarnings("unused")
	public ResponderHendlerDTO login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsername(loginDTO.getUsernameOrEmail());

		if (user == null) {
			User user1 = userRepository.findByEmail(loginDTO.getUsernameOrEmail());
			if (user1 == null) {
				return new ResponderHendlerDTO(null, "userNotFound", 404);
			} else {
				user = user1;
			}
		}

		if (!user.getIsAcive()) {
			return new ResponderHendlerDTO(null, "userNotActive", 500);
		}

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
					loginDTO.getPassword());

			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
			
			String roles = user.getRoles().get(0).getName();
			UserDTO userDTO = new UserDTO(user);
			
			return new ResponderHendlerDTO(200, "login", tokenUtils.generateToken(details), userDTO, roles);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponderHendlerDTO(e, "userNotFound", 404);
		}
	}

}
