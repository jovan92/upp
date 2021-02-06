package com.example.app.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.dtos.FilesDTO;
import com.example.app.dtos.ResponderHendlerDTO;
import com.example.app.models.Files;
import com.example.app.models.User;
import com.example.app.repositories.FilesRepository;
import com.example.app.repositories.UserRepository;

@Service
public class FilesService {
	
	@Autowired
	private FilesRepository filesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	

	public Object saveFile(MultipartFile file, String procesId, String username) throws IOException {
		User user = userRepository.findByUsername(username);
		
		Files files = new Files(file.getOriginalFilename(), file.getBytes(), false, user);
		
		Files filesResponse = filesRepository.save(files);
		
		List<FilesDTO> filesDTOs = new ArrayList<FilesDTO>();
		FilesDTO filsesDTO = new FilesDTO(filesResponse);
		
		filesDTOs.add(filsesDTO);
		return new ResponderHendlerDTO(200, "success", filesDTOs);
	}

	public Object getAllByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userServiceImpl.findByUsername(username);
		
		List<Files> files = filesRepository.findByUser(user);
		
		
		List<FilesDTO> filesDTO = new ArrayList<FilesDTO>();
		for (Files f: files) {
			filesDTO.add(new FilesDTO(f));
		}

		return new ResponderHendlerDTO(200, "success", filesDTO);
	}

	public void deleteFile(Long fileId) {
		// TODO Auto-generated method stub
		Optional<Files> files = filesRepository.findById(fileId);
		
		Files f = files.get();
		
		filesRepository.delete(f);
	}

}
