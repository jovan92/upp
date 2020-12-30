package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Genres;

public interface GenresRepository extends JpaRepository<Genres, Long> {
	
	
}
