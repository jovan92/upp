package com.example.app.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String hashCode;
	private Boolean isBeta;
	private Boolean isAcive = false;

	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "user_genres", joinColumns = @JoinColumn(name = "user_id",
	 * referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name =
	 * "genres_id", referencedColumnName = "id")) private List<Genres> genres;
	 */
	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	 * 
	 * @OnDelete(action = OnDeleteAction.CASCADE) private Address address;
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Roles> roles = new ArrayList<Roles>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String username, String password, Boolean isBeta, List<Roles> roles,
			String email, String hashCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isBeta = isBeta;
		this.roles = roles;
		this.email = email;
		this.hashCode = hashCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsBeta() {
		return isBeta;
	}

	public void setIsBeta(Boolean isBeta) {
		this.isBeta = isBeta;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public Boolean getIsAcive() {
		return isAcive;
	}

	public void setIsAcive(Boolean isAcive) {
		this.isAcive = isAcive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", email=" + email
				+ ", isBeta=" + isBeta + ", isAcive=" + isAcive + "]";
	}

}
