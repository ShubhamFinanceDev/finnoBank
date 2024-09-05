package com.kemal.spring.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sud_application")
public class SudApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "application_number")
	private String applicationnumber;

	private Date createon;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicationnumber() {
		return applicationnumber;
	}

	public void setApplicationnumber(String applicationnumber) {
		this.applicationnumber = applicationnumber;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}
	
	
	
	
	
}
