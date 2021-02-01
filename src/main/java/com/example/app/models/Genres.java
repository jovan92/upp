package com.example.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.app.dtos.GenresDTO;

@Entity
public class Genres {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String vin;

	/*@ManyToMany(mappedBy = "genres", cascade = CascadeType.MERGE)
	private List<User> users;*/

	public Genres(Long id, String name, String vin) {
		super();
		this.id = id;
		this.name = name;
		this.vin = vin;
	}

	public Genres() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genres(GenresDTO genresDTO) {
		// TODO Auto-generated constructor stub
		this.name = genresDTO.getName();
		this.vin = genresDTO.getVin();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"vin\":\"" + vin + "\"}";
	}

}
