package com.kemal.spring.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AirtelApplicationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String loannumber;

	private String customername;

	private Double emiamount;

	private Double totaldue;

	private Date createon;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdby;

	private String reciptnumber;

	private String paymentype;

	private Double collectedAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	private AirtelBatchDetails batchDetails;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoannumber() {
		return loannumber;
	}

	public void setLoannumber(String loannumber) {
		this.loannumber = loannumber;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public Double getEmiamount() {
		return emiamount;
	}

	public void setEmiamount(Double emiamount) {
		this.emiamount = emiamount;
	}

	public Double getTotaldue() {
		return totaldue;
	}

	public void setTotaldue(Double totaldue) {
		this.totaldue = totaldue;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	

	public User getCreatedby() {
		return createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	public String getReciptnumber() {
		return reciptnumber;
	}

	public void setReciptnumber(String reciptnumber) {
		this.reciptnumber = reciptnumber;
	}

	public String getPaymentype() {
		return paymentype;
	}

	public void setPaymentype(String paymentype) {
		this.paymentype = paymentype;
	}

	public Double getCollectedAmount() {
		return collectedAmount;
	}

	public void setCollectedAmount(Double string) {
		this.collectedAmount = string;
	}

	public AirtelBatchDetails getBatchDetails() {
		return batchDetails;
	}

	public void setBatchDetails(AirtelBatchDetails batchDetails) {
		this.batchDetails = batchDetails;
	}

	
	
	
	

}
