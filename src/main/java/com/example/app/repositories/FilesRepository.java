package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Files;
import com.example.app.models.User;

public interface FilesRepository extends JpaRepository<Files, Long> {

	List<Files> findByUser(User user);

}
