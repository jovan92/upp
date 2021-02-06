package com.example.app.controllers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.services.FilesService;
import com.example.app.services.HelperService;

@Controller
@RequestMapping("/api/files")
public class FileController {

	private String procesName = "Process_Create_Writer";

	Logger logger = LogManager.getLogger(FileController.class);

	@Autowired
	FilesService filesService;
	
	@Autowired
	HelperService helperService;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllByUsername(@PathVariable String username) {
		logger.info("Start getAllByUsername method.");
		
		Object response = filesService.getAllByUsername(username);
		
		logger.info("Finished getAllByUsername method.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{procesId}/{username}", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestParam("pdf") MultipartFile file, @PathVariable String procesId,
			@PathVariable String username) {
		logger.info("Start save files in folder.");
		Object response = null;
		try {
			System.out.println("Original Image Byte Size - " + file.getBytes().length);

			response = filesService.saveFile(file, procesId, username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Finished save new user in db");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{procesId}/{username}", method = RequestMethod.PUT)
	public ResponseEntity<Object> commit(@PathVariable String username, @PathVariable String procesId) {
		logger.info("Start commit method.");
		Object response = null;
		response = helperService.commit(procesId, username);
		logger.info("Finished commit method.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{procesId}/{fileId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String procesId, @PathVariable Long fileId) {
		logger.info("Start delete file method.");
		Object response = helperService.delete(procesId, fileId);
		logger.info("Finished delete file method.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
