package com.kemal.spring.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sud_finalstatus")
public class SudFinalStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "finalstatusName")
	private String finalstatusName;
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFinalstatusName() {
		return finalstatusName;
	}

	public void setFinalstatusName(String finalstatusName) {
		this.finalstatusName = finalstatusName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	

}
