package com.example.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Votes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user; // glasac

	private Raitings raitings;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private CheckRequest checkRequest;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Files files;

	public Votes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Votes(Long id, User user, Raitings raitings, CheckRequest checkRequest) {
		super();
		this.id = id;
		this.user = user;
		this.raitings = raitings;
		this.checkRequest = checkRequest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Raitings getRaitings() {
		return raitings;
	}

	public void setRaitings(Raitings raitings) {
		this.raitings = raitings;
	}

	public CheckRequest getCheckRequest() {
		return checkRequest;
	}

	public void setCheckRequest(CheckRequest checkRequest) {
		this.checkRequest = checkRequest;
	}

}
