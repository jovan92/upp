package com.example.app.dtos;

import com.example.app.models.Genres;

public class GenresDTO {

	private Long id;
	private String name;
	private String vin;

	public GenresDTO(Long id, String name, String vin) {
		super();
		this.id = id;
		this.name = name;
		this.vin = vin;
	}

	public GenresDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenresDTO(Genres res) {
		// TODO Auto-generated constructor stub
		this.id = res.getId();
		this.name = res.getName();
		this.vin = res.getVin();
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
		return "Ganres [id=" + id + ", name=" + name + ", vin=" + vin + "]";
	}
}
