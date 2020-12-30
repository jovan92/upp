package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dtos.GenresDTO;
import com.example.app.dtos.ResponderHendlerDTO;
import com.example.app.models.Genres;
import com.example.app.repositories.GenresRepository;

@Service
public class GenresService {

	@Autowired
	private GenresRepository genresRepository;

	public List<Genres> getAllGenres() {
		return this.genresRepository.findAll();
	}

	public Object getGenres(String string) {

		List<Genres> genres = genresRepository.findAll();
		System.out.println("service: ");
		for (Genres genres2 : genres) {
			System.out.println(genres2.getName());
		}
		return genres;
	}

	public Object addNewGenres(GenresDTO genresDTO) {
		Genres newGenres = new Genres(genresDTO);
		
		Genres res = genresRepository.save(newGenres);
		return new GenresDTO(res);
	}

}
