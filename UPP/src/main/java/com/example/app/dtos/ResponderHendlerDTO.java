package com.example.app.dtos;

public class ResponderHendlerDTO {

	private Exception e;
	private String type;
	private int status;
	private FormFieldsDto formFieldsDto;

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
		if (formFieldsDto != null) this.formFieldsDto = formFieldsDto;
	}
	
	public ResponderHendlerDTO(int status, FormFieldsDto formFieldsDto, String type) {
		super();
		this.status = status;
		if (formFieldsDto != null) this.formFieldsDto = formFieldsDto;
		this.type = type;
	}
	
	public ResponderHendlerDTO(int status, String type) {
		super();
		this.status = status;
		this.type = type;
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

}
