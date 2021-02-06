package com.example.app.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CheckRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	private User user; // vlasnik

	@ManyToMany
	@JoinTable(name = "check_request_user", joinColumns = @JoinColumn(name = "check_request_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> commision = new ArrayList<User>(); // ko treba da glasa

	@OneToMany(mappedBy = "checkRequest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Files> files; // Falovi za koje se glasa

	@OneToMany(mappedBy = "checkRequest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Votes> votes = new ArrayList<Votes>(); // Lista ocjena

	public CheckRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CheckRequest(User user, List<User> commision, List<Files> files) {
		super();
		this.user = user;
		this.commision = commision;
		this.files = files;
	}

	public CheckRequest(Long id, User user, List<User> commision, List<Files> files, List<Votes> votes) {
		super();
		this.id = id;
		this.user = user;
		this.commision = commision;
		this.files = files;
		this.votes = votes;
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

	public List<User> getCommision() {
		return commision;
	}

	public void setCommision(List<User> commision) {
		this.commision = commision;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	public List<Votes> getVotes() {
		return votes;
	}

	public void setVotes(List<Votes> votes) {
		this.votes = votes;
	}

}
