package com.example.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
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
	private RepositoryService repositoryService;

	@Autowired
	TaskService taskService;

	@Autowired
	FormService formService;

	/**
	 * Metoda koja je nativna i omogucava da svi procesi kroz nju prodju tako sto
	 * proslijedimo id procesa i on na osnovu njega nadje proces i iscita podatke
	 * 
	 * @param typeProces
	 * @return ResponderHendlerDTO
	 */
	public Object getForms(String idProces) {
		ResponderHendlerDTO respons;
		try {
			ProcessInstance pi = runtimeService.startProcessInstanceByKey(idProces);

			Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);

			TaskFormData tfd = formService.getTaskFormData(task.getId());

			List<FormField> properties = tfd.getFormFields();

			FormFieldsDto formFieldsDto = new FormFieldsDto(task.getId(), pi.getId(), properties);

			respons = new ResponderHendlerDTO(200, formFieldsDto);
			logger.info("End method getForms()");
			return respons;
		} catch (Exception e) {

			respons = new ResponderHendlerDTO(e, "hendler", 500);
			return respons;
		}

	}

	public Object save(String procesId, String taskId, List<FormSubmissionDto> formSubmissionDto) {

		ResponderHendlerDTO respons;

		HashMap<String, Object> map = this.mapListToDTO(formSubmissionDto);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "registration", map);
		formService.submitTaskForm(taskId, map);
		logger.info("End method save()");

		respons = new ResponderHendlerDTO(200, "success");
		return respons;
	}

	public Object verify(TokenDTO tokenDTO) {

		// runtimeService.setVariable(tokenDTO.getProcessId(), "VerifyUserToken", tokenDTO.getToken());

		MessageCorrelationResult results = runtimeService.createMessageCorrelation("UserVerification")
				.processInstanceId(tokenDTO.getProcessId()).correlateWithResult();

		logger.info("Create Message Correlation: {" + results.getResultType().toString() + "}");

		Task nextTask;
		TaskFormData tfd = null;
		Boolean response = false;
		if (taskService.createTaskQuery().processInstanceId(tokenDTO.getProcessId()).list().size() != 0) {
			// TODO Ovo prenjeti na neku drugu metodu pa ako dodje do loseg necega da moze
			// ponovo da se iskorisi
			logger.info("Start create Activation User Task");
			nextTask = taskService.createTaskQuery().processInstanceId(tokenDTO.getProcessId()).list().get(0);
			tfd = formService.getTaskFormData(nextTask.getId());
			logger.info("Finis create Activation User Task");
			List<FormField> properties = tfd.getFormFields();
			
			response = this.updateIsActivUser(nextTask.getId(), properties, tokenDTO);
		}
		
		return new ResponderHendlerDTO(null, "success", 200);
	}
	
	public Boolean updateIsActivUser(String taskId, List<FormField> properties, TokenDTO tokenDTO) {
		logger.info("Start last task, Activation User");
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
		logger.info("Finis Activation User");

		return true;
	}

	private HashMap<String, Object> mapListToDTO(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}
}
