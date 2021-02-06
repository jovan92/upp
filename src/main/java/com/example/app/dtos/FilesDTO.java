package com.example.app.dtos;

import com.example.app.models.Files;

public class FilesDTO {

	private Long id;
	private String name;
	private byte[] file;
	private UserDTO userDTO;
	public FilesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilesDTO(Long id, String name, byte[] file, UserDTO userDTO) {
		super();
		this.id = id;
		this.name = name;
		this.file = file;
		this.userDTO = userDTO;
	}
	public FilesDTO(Files filesResponse) {
		// TODO Auto-generated constructor stub
		this.id = filesResponse.getId();
		this.name = filesResponse.getName();
		this.file = filesResponse.getFile();
		this.userDTO = new UserDTO(filesResponse.getUser());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	
}
