package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.CheckRequest;
import com.example.app.repositories.CheckRequestRepository;

@Service
public class CheckRequestImplService {

	@Autowired
	CheckRequestRepository  checkRequestRepository;

	public CheckRequest save(CheckRequest cr) {
		// TODO Auto-generated method stub
		return checkRequestRepository.save(cr);
	}
}
