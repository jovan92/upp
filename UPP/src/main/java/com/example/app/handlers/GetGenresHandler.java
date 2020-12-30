package com.example.app.handlers;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.Genres;
import com.example.app.services.GenresService;
import com.example.app.services.MultipleEnumFormType;

@Service
public class GetGenresHandler implements TaskListener {

    @Autowired
    GenresService genreService;

    public void notify(DelegateTask delegateTask) {
        TaskFormData taskFormFields = delegateTask.getExecution().getProcessEngineServices().getFormService().getTaskFormData(delegateTask.getId());
        List<Genres> genres = genreService.getAllGenres();
        for (FormField f : taskFormFields.getFormFields()) {
        	
        	
            if (f.getId().equals("genres")) {
            	MultipleEnumFormType multipleEnumFormType = (MultipleEnumFormType) f.getType();
                multipleEnumFormType.getValues().put("all", genres.toString());
            }
        }
    }
}
