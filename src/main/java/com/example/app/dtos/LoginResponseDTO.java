package com.example.app.dtos;

public class LoginResponseDTO {

	private UserDTO userDTO;
	private String token;
	private String roles;

	public LoginResponseDTO(String token, UserDTO userDTO) {
		super();
		this.userDTO = userDTO;
		this.token = token;
	}

	public LoginResponseDTO(String token, UserDTO userDTO, String roles) {
		// TODO Auto-generated constructor stub
		super();
		this.userDTO = userDTO;
		this.token = token;
		this.roles = roles;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
