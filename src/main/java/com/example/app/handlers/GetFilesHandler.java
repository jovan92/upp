package com.example.app.handlers;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.stereotype.Service;

import com.example.app.models.Genres;

@Service
public class GetFilesHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		
		 TaskFormData taskFormFields = delegateTask.getExecution().getProcessEngineServices().getFormService().getTaskFormData(delegateTask.getId());
	     System.out.println("GetFilesHandler iskoristiti da dovuce fajlove to kasnije srediti!!!!!");
		
	}

}
