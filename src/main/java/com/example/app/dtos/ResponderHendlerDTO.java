package com.example.app.dtos;

import java.util.List;

public class ResponderHendlerDTO {

	private Exception e;
	private String type;
	private int status;
	private FormFieldsDto formFieldsDto;
	private LoginResponseDTO loginResponseDTO;
	private List<FilesDTO> filesDTOs;

	public ResponderHendlerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponderHendlerDTO(Exception e, String type, int status) {
		super();
		this.e = e;
		this.type = type;
		this.status = status;
	}

	public ResponderHendlerDTO(int status, FormFieldsDto formFieldsDto) {
		super();
		this.status = status;
		if (formFieldsDto != null)
			this.formFieldsDto = formFieldsDto;
	}

	public ResponderHendlerDTO(int status, FormFieldsDto formFieldsDto, String type) {
		super();
		this.status = status;
		if (formFieldsDto != null)
			this.formFieldsDto = formFieldsDto;
		this.type = type;
	}

	public ResponderHendlerDTO(int status, String type) {
		super();
		this.status = status;
		this.type = type;
	}

	/**
	 * Create login response
	 * 
	 * @param status
	 * @param type
	 * @param jwt
	 * @param userDTO
	 */
	public ResponderHendlerDTO(int status, String type, String jwt, UserDTO userDTO) {
		super();
		this.status = status;
		this.type = type;
		this.loginResponseDTO = new LoginResponseDTO(jwt, userDTO);
	}

	public ResponderHendlerDTO(int status, String type, String jwt, UserDTO userDTO, String roles) {
		// TODO Auto-generated constructor stub
		super();
		this.status = status;
		this.type = type;
		this.loginResponseDTO = new LoginResponseDTO(jwt, userDTO, roles);
	}

	public ResponderHendlerDTO(int status, String type, List<FilesDTO> filesDTOs) {
		// TODO Auto-generated constructor stub
		this.status = status;
		this.type = type;
		this.filesDTOs = filesDTOs;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public FormFieldsDto getFormFieldsDto() {
		return formFieldsDto;
	}

	public void setFormFieldsDto(FormFieldsDto formFieldsDto) {
		this.formFieldsDto = formFieldsDto;
	}

	public LoginResponseDTO getLoginResponseDTO() {
		return loginResponseDTO;
	}

	public void setLoginResponseDTO(LoginResponseDTO loginResponseDTO) {
		this.loginResponseDTO = loginResponseDTO;
	}

	public List<FilesDTO> getFilesDTOs() {
		return filesDTOs;
	}

	public void setFilesDTOs(List<FilesDTO> filesDTOs) {
		this.filesDTOs = filesDTOs;
	}

}
