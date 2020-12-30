package com.example.app.dtos;

import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDto {

	public String taskId;
	public List<FormField> formFields;
	public String processInstanceId;

	public FormFieldsDto(String taskId, List<FormField> formFields, String processInstanceId) {
		super();
		this.taskId = taskId;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
	}

	public FormFieldsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormFieldsDto(String id, String id2, List<FormField> properties) {
		super();
		this.taskId = id;
		this.formFields = properties;
		this.processInstanceId = id2;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<FormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

}
