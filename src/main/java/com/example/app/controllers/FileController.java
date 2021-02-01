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

@Controller
@RequestMapping("/api/files")
public class FileController {

	private String procesName = "Process_Create_Writer";

	Logger logger = LogManager.getLogger(FileController.class);

	@Autowired
	FilesService filesService;

	@RequestMapping(value = "/{procesId}/{username}", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestParam("imageFile") MultipartFile file, @PathVariable String procesId,
			@PathVariable String username) {
		logger.info("Start save files in folder.");

		try {
			System.out.println("Original Image Byte Size - " + file.getBytes().length);

			Object response = filesService.saveFile(file, procesId, username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Logika skidanja bajt koda koji nosi informaciju o fajlovima
		// TODO Logika parsiranja bajta i slanja dalje
		// Object response = userWriterRegistrationService.saveFiles(); //
		// prosljedjujemo taj bajt kod
		logger.info("Finished save new user in db");
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
