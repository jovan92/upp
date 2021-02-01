package com.example.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dtos.FormFieldsDto;
import com.example.app.dtos.FormSubmissionDto;
import com.example.app.dtos.ResponderHendlerDTO;
import com.example.app.dtos.TokenDTO;

@Service
public class HelperService {

	Logger logger = LogManager.getLogger(HelperService.class);

	@Autowired
	IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	FormService formService;
	
	DelegateExecution execution;

	public Object getForms(String idProces) {
		ResponderHendlerDTO respons;
		try {
			ProcessInstance pi = runtimeService.startProcessInstanceByKey(idProces);

			Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
			TaskFormData tfd = formService.getTaskFormData(task.getId());

			List<FormField> properties = tfd.getFormFields();

			FormFieldsDto formFieldsDto = new FormFieldsDto(task.getId(), pi.getId(), properties);

			respons = new ResponderHendlerDTO(200, formFieldsDto);
			logger.info("Number of the form field = [" + properties.size() + "]");
			return respons;
		} catch (Exception e) {

			respons = new ResponderHendlerDTO(e, "hendler", 500);
			return respons;
		}

	}

	public Object save(String procesId, String taskId, List<FormSubmissionDto> formSubmissionDto) {

		ResponderHendlerDTO respons;

		HashMap<String, Object> map = this.mapListToDTO(formSubmissionDto);
		
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			String processInstanceId = task.getProcessInstanceId();
			runtimeService.setVariable(processInstanceId, "registration", map);
			formService.submitTaskForm(taskId, map);
			respons = new ResponderHendlerDTO(200, "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lose: " + e.toString());
			respons = new ResponderHendlerDTO(200, "errorUsername");
		}
		
		return respons;
	}

	public Object verify(TokenDTO tokenDTO) {
		Task nextTask;
		TaskFormData tfd = null;
		if (taskService.createTaskQuery().processInstanceId(tokenDTO.getProcessId()).list().size() != 0) {
			logger.info("Start create new User Task.");

			nextTask = taskService.createTaskQuery().processInstanceId(tokenDTO.getProcessId()).list().get(0);
			tfd = formService.getTaskFormData(nextTask.getId());

			List<FormField> properties = tfd.getFormFields();
			this.updateIsActivUser(nextTask.getId(), properties, tokenDTO);
			logger.info("Finished create new User Task.");
		}

		return new ResponderHendlerDTO(null, "success", 200);
	}

	private Boolean updateIsActivUser(String taskId, List<FormField> properties, TokenDTO tokenDTO) {
		FormSubmissionDto fsd = new FormSubmissionDto();
		fsd.setFieldId("token");
		fsd.setFieldValue(tokenDTO.getToken());

		List<FormSubmissionDto> fssd = new ArrayList<FormSubmissionDto>();
		fssd.add(fsd);

		HashMap<String, Object> map = this.mapListToDTO(fssd);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "activationUser", map);
		formService.submitTaskForm(taskId, map);
		return true;
	}

	private HashMap<String, Object> mapListToDTO(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}

	public Object getFileForms(String procesName, String taksId) {
		// TODO Auto-generated method stub

		// TODO Napraviti try 
		Task task = taskService.createTaskQuery().processInstanceId(taksId).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		FormFieldsDto formFieldsDto = new FormFieldsDto(task.getId(), procesName, properties);

		ResponderHendlerDTO respons = new ResponderHendlerDTO(200, formFieldsDto);
		return respons;
	}
}
