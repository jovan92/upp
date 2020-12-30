package com.example.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE)
	private List<User> users;

	
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", name=" + name + ", users=" + users + "]";
	}

}
