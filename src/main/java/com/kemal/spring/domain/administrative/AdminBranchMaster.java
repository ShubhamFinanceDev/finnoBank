package com.kemal.spring.domain.administrative;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "admin_branch")
public class AdminBranchMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "branchCode")
	private String branchCode;
	
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "branchName")
	private String branchName;
	
	
	@Column(name = "State")
	private String state;
	
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active=1;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBranchCode() {
		return branchCode;
	}


	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}

	
	
	

}
