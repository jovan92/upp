package com.example.app.services;

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

	public Object login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		User user = userRepository.findByUsername(loginDTO.getUsernameOrEmail());
		if (user == null) {
			User user1 = userRepository.findByEmail(loginDTO.getUsernameOrEmail());
			if (user1 == null) {
				return false;
			} else {
				user = user1;
			}
		}

		if (!user.getIsAcive()) {
			return false;
		}

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
					loginDTO.getPassword());

			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());

			map.put("jwt", tokenUtils.generateToken(details));
			map.put("user", user);

			return map;
		} catch (Exception e) {
			// TODO: handle exception
			map.put("error", "User is not login");
			map.put("user", null);
			return map;
		}

	}

}
