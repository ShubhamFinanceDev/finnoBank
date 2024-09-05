package com.kemal.spring.domain.administrative;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "admin_courier")
public class AdminCourierMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	
	
	@Column(name = "courierName")
	private String courierName;
	
	

	
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active=1;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	public String getCourierName() {
		return courierName;
	}


	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}



	

}
