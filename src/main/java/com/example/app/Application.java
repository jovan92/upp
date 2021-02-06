package com.example.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.app.models.Roles;
import com.example.app.repositories.RoleRepository;
import com.example.app.repositories.UserRepository;

@Configuration
@EnableWebMvc
@SpringBootApplication
public class Application {

	@Autowired
	private IdentityService identityService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@PostConstruct
	private void InitGroups() {
		// TO DO Ovo prebaciti u neku posebnu klasu kasnije
		List<Group> groups = identityService.createGroupQuery().groupIdIn("READER", "BETAREADER").list();

		if (groups.isEmpty()) {

			Group reader = identityService.newGroup("READER");
			identityService.saveGroup(reader);

			Group betaReader = identityService.newGroup("BETAREADER");
			identityService.saveGroup(betaReader);
		}

		Roles newRole1 = new Roles("READER");
		roleRepository.save(newRole1);
		Roles newRole2 = new Roles("BETAREADER");
		roleRepository.save(newRole2);
		Roles newRole3 = new Roles("WRITER");
		roleRepository.save(newRole3);
		Roles newRole4 = new Roles("COMMISSION");
		roleRepository.save(newRole4);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		List<Roles> roles = new ArrayList<Roles>();
		roles.add(newRole4);

		com.example.app.models.User user1 = new com.example.app.models.User("Dejan", "Jovanovic", "dejan",
				encoder.encode("123").toString(), false, true, roles, "novica.nikolioc@gmail.com", "as2d1as4d5a6s4da6");
		userRepository.save(user1);

		com.example.app.models.User user2 = new com.example.app.models.User("Jovan", "Popovic", "jovan",
				encoder.encode("123").toString(), false, true, roles, "nole994ns2@gmail.com", "as2d1as4d5a6s4da6");
		userRepository.save(user2);
	}
}