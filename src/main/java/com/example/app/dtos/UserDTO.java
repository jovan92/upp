package com.example.app.dtos;

import com.example.app.models.User;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private Boolean isBeta;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String firstName, String lastName, String username, String email, Boolean isBeta) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.isBeta = isBeta;
	}
	
	public UserDTO(User u) {
		super();
		this.id = u.getId();
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.username = u.getUsername();
		this.email = u.getEmail();
		this.isBeta = u.getIsBeta();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsBeta() {
		return isBeta;
	}

	public void setIsBeta(Boolean isBeta) {
		this.isBeta = isBeta;
	}

}
