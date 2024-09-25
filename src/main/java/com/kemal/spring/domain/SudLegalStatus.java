package com.kemal.spring.domain;
import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sud_legalstatus")
public class SudLegalStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "legalStatusName")
	private String legalStatusName;
	
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLegalStatusName() {
		return legalStatusName;
	}

	public void setLegalStatusName(String legalStatusName) {
		this.legalStatusName = legalStatusName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	

}
