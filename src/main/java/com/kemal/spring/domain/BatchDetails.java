package com.kemal.spring.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class BatchDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String batchnumber;

	private Date createon;



	private Date depositon;

	private Date paymentdate;

	private Double totalcollectedamount=0.0;

	private String userstatus;

	private String finobankacknumber;

	private int active = 1;

	
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdby;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatchnumber() {
		return batchnumber;
	}

	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	

	public Date getDepositon() {
		return depositon;
	}

	public void setDepositon(Date depositon) {
		this.depositon = depositon;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Double getTotalcollectedamount() {
		return totalcollectedamount;
	}

	public void setTotalcollectedamount(Double totalcollectedamount) {
		this.totalcollectedamount = totalcollectedamount;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getFinobankacknumber() {
		return finobankacknumber;
	}

	public void setFinobankacknumber(String finobankacknumber) {
		this.finobankacknumber = finobankacknumber;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public User getCreatedby() {
		return createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	

}
