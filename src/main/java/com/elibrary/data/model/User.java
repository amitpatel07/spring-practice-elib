package com.elibrary.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class User implements Serializable {

	private static final long serialVersionUID = 8197493244337408102L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	// Eager fetch will join and bring all the data
	// lazy fetch will bring only on-demand during get call
	@JoinColumn(name = "person_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Person person;

	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String role;

	public User() {
	}

	public User(Person person, String username, String password, String email, String role) {
		this.person = person;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", person=" + person + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}

}
