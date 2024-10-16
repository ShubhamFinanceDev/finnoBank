package com.kemal.spring.web.dto;

import java.util.Date;

public class BatchDTO {

	private String loannumber;
	private String customername;
	private String branchname;
	private Double emiamount;
	private Double totalamount;
	private Double collectedamount;
	private String reciptnumber;
	private String paymentype;
	private String createdby;
	private Date createon;
	

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

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public Double getEmiamount() {
		return emiamount;
	}

	public void setEmiamount(Double emiamount) {
		this.emiamount = emiamount;
	}

	

	public Double getCollectedamount() {
		return collectedamount;
	}

	public void setCollectedamount(Double collectedamount) {
		this.collectedamount = collectedamount;
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

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

}
