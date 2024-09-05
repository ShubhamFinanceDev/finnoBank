package com.kemal.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sud_docketstatus")
public class SudDocketStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "docketstatusName")
	private String docketstatusName;
	
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocketstatusName() {
		return docketstatusName;
	}

	public void setDocketstatusName(String docketstatusName) {
		this.docketstatusName = docketstatusName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
