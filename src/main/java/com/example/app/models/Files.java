package com.example.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Files {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Boolean isStatus = false;

	@Lob
	private byte[] file;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private CheckRequest checkRequest;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "files", cascade = CascadeType.ALL)
	private Votes files;

	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Files(Long id, String name, byte[] file, Boolean isStatus, User user) {
		super();
		this.id = id;
		this.name = name;
		this.file = file;
		this.isStatus = isStatus;
		this.user = user;
	}

	public Files(String name, byte[] file, Boolean isStatus, User user) {
		super();
		this.name = name;
		this.file = file;
		this.isStatus = isStatus;
		this.user = user;
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

	public Boolean getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(Boolean isStatus) {
		this.isStatus = isStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public CheckRequest getCheckRequest() {
		return checkRequest;
	}

	public void setCheckRequest(CheckRequest checkRequest) {
		this.checkRequest = checkRequest;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", name=" + name + ", isStatus=" + isStatus + ", user=" + user + "]";
	}

}
