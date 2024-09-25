package com.kemal.spring.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sud_didate")
public class SudDidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "didatevalue")
	private String didatevalue;
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDidatevalue() {
		return didatevalue;
	}

	public void setDidatevalue(String didatevalue) {
		this.didatevalue = didatevalue;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	

}
