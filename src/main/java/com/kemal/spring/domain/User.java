package com.kemal.spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String surname;

	private String empcode;

	private String empdepartment;

	private String empbranch;

	private String empcontactno;

	private Date createdon;

	private String otp;

	private String expiretime;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	@Column(length = 60)
	private String password;

	private boolean enabled;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> roles = new ArrayList<>();

	public User() {
	}

	public User(String name, String surname, String username, String email, String password, boolean enabled,
			String empcode, String empbranch, String empcontactno, String empdepartment) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.empcode = empcode;
		this.empbranch = empbranch;
		this.empcontactno = empcontactno;
		this.empdepartment = empdepartment;

	}

	public User(String name, String surname, String username, String email, String password, boolean enabled,
			String empcode, String empbranch, String empcontactno, String empdepartment, List<Role> roles) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
		this.empcode = empcode;
		this.empbranch = empbranch;
		this.empcontactno = empcontactno;
		this.empdepartment = empdepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getEmpbranch() {
		return empbranch;
	}

	public void setEmpbranch(String empbranch) {
		this.empbranch = empbranch;
	}

	public String getEmpcontactno() {
		return empcontactno;
	}

	public void setEmpcontactno(String empcontactno) {
		this.empcontactno = empcontactno;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getEmpdepartment() {
		return empdepartment;
	}

	public void setEmpdepartment(String empdepartment) {
		this.empdepartment = empdepartment;
	}

}
