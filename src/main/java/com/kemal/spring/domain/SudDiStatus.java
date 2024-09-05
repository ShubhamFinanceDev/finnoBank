package com.kemal.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sud_distatus")
public class SudDiStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "distatusName")
	private String distatusName;
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistatusName() {
		return distatusName;
	}

	public void setDistatusName(String distatusName) {
		this.distatusName = distatusName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}


	
	
	
}
