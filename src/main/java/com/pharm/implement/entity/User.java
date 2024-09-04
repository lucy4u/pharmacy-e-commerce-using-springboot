package com.pharm.implement.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="user" ,uniqueConstraints = @UniqueConstraint(columnNames="email"))
public class User {
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "phone", nullable = false)
	private Long phone;
	@Column(name = "password", nullable = false)
	private String password;
	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinTable(
	        name = "user_roles",
	        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles = new ArrayList<>();
	public User() {
	    // Default constructor
	}

	
	public User(String name, String email, Long phone, String password, Collection<Role> roles) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}

