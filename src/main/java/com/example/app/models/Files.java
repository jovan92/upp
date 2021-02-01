package com.example.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Files {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String link;
	private Boolean activeLink = false;
	private String userId;

	public Files(Long id, String link, Boolean activeLink, String userId) {
		super();
		this.id = id;
		this.link = link;
		this.activeLink = activeLink;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", link=" + link + ", activeLink=" + activeLink + ", userId=" + userId + "]";
	}

	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Boolean getActiveLink() {
		return activeLink;
	}

	public void setActiveLink(Boolean activeLink) {
		this.activeLink = activeLink;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
