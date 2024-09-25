package com.kemal.spring.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sud_technicalstatus")
public class SudTechnicalStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "technicalStatusName")
	private String technicalStatusName;
	
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTechnicalStatusName() {
		return technicalStatusName;
	}

	public void setTechnicalStatusName(String technicalStatusName) {
		this.technicalStatusName = technicalStatusName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	

}
