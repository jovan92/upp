package com.example.app.dtos;

public class LoginResponseDTO {

	private UserDTO userDTO;
	private String token;
	
	

	public LoginResponseDTO(String token, UserDTO userDTO) {
		super();
		this.userDTO = userDTO;
		this.token = token;
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

}
