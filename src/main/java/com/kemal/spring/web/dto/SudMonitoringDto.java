package com.kemal.spring.web.dto;

import java.sql.Timestamp;
import java.util.Date;

public class SudMonitoringDto {

	private Integer id;

	private String applicationnumber;

	private String branchname;

	private String region;

	private String customername;

	private String product;

	private String scheme;

	private String relationshipmanager;

	private String currentstatus;

	private Date logindate;

	private Date sanctiondate;

	private Float sanctionloanamount;

	private Float roi;

	private String sanctionby;

	private Double tat;

	private Double disbursalamount;

	private Float sanctionvalue;

	private Float divalue;

	private int legalstatus;

	private int technicalstatus;

	private int pastatus;

	private int docketstatus;

	private int distatus;

	private int finalstatus;
	
	private String legalstatusname;

	private String technicalstatusname;

	private String pastatusname;

	private String docketstatusname;

	private String distatusname;

	private String finalstatusname;
	
	

	private String didateifdoable;

	private String additionalremarks;
	
	private String updateRequired;

	private String rowColor;
	
	private Timestamp updateOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicationnumber() {
		return applicationnumber;
	}

	public void setApplicationnumber(String applicationnumber) {
		this.applicationnumber = applicationnumber;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getRelationshipmanager() {
		return relationshipmanager;
	}

	public void setRelationshipmanager(String relationshipmanager) {
		this.relationshipmanager = relationshipmanager;
	}

	public String getCurrentstatus() {
		return currentstatus;
	}

	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	public Date getSanctiondate() {
		return sanctiondate;
	}

	public void setSanctiondate(Date sanctiondate) {
		this.sanctiondate = sanctiondate;
	}

	public Float getSanctionloanamount() {
		return sanctionloanamount;
	}

	public void setSanctionloanamount(Float sanctionloanamount) {
		this.sanctionloanamount = sanctionloanamount;
	}

	public Float getRoi() {
		return roi;
	}

	public void setRoi(Float roi) {
		this.roi = roi;
	}

	public String getSanctionby() {
		return sanctionby;
	}

	public void setSanctionby(String sanctionby) {
		this.sanctionby = sanctionby;
	}

	public Double getTat() {
		return tat;
	}

	public void setTat(Double tat) {
		this.tat = tat;
	}

	public Double getDisbursalamount() {
		return disbursalamount;
	}

	public void setDisbursalamount(Double disbursalamount) {
		this.disbursalamount = disbursalamount;
	}

	public Float getSanctionvalue() {
		return sanctionvalue;
	}

	public void setSanctionvalue(Float sanctionvalue) {
		this.sanctionvalue = sanctionvalue;
	}

	public Float getDivalue() {
		return divalue;
	}

	public void setDivalue(Float divalue) {
		this.divalue = divalue;
	}

	public int getLegalstatus() {
		return legalstatus;
	}

	public void setLegalstatus(int legalstatus) {
		this.legalstatus = legalstatus;
	}

	public int getTechnicalstatus() {
		return technicalstatus;
	}

	public void setTechnicalstatus(int technicalstatus) {
		this.technicalstatus = technicalstatus;
	}

	public int getPastatus() {
		return pastatus;
	}

	public void setPastatus(int pastatus) {
		this.pastatus = pastatus;
	}

	public int getDocketstatus() {
		return docketstatus;
	}

	public void setDocketstatus(int docketstatus) {
		this.docketstatus = docketstatus;
	}

	public int getDistatus() {
		return distatus;
	}

	public void setDistatus(int distatus) {
		this.distatus = distatus;
	}

	public int getFinalstatus() {
		return finalstatus;
	}

	public void setFinalstatus(int finalstatus) {
		this.finalstatus = finalstatus;
	}

	public String getDidateifdoable() {
		return didateifdoable;
	}

	public void setDidateifdoable(String didateifdoable) {
		this.didateifdoable = didateifdoable;
	}

	public String getAdditionalremarks() {
		return additionalremarks;
	}

	public void setAdditionalremarks(String additionalremarks) {
		this.additionalremarks = additionalremarks;
	}

	public String getRowColor() {
		return rowColor;
	}

	public void setRowColor(String rowColor) {
		this.rowColor = rowColor;
	}

	public String getLegalstatusname() {
		return legalstatusname;
	}

	public void setLegalstatusname(String legalstatusname) {
		this.legalstatusname = legalstatusname;
	}

	public String getTechnicalstatusname() {
		return technicalstatusname;
	}

	public void setTechnicalstatusname(String technicalstatusname) {
		this.technicalstatusname = technicalstatusname;
	}

	public String getPastatusname() {
		return pastatusname;
	}

	public void setPastatusname(String pastatusname) {
		this.pastatusname = pastatusname;
	}

	public String getDocketstatusname() {
		return docketstatusname;
	}

	public void setDocketstatusname(String docketstatusname) {
		this.docketstatusname = docketstatusname;
	}

	public String getDistatusname() {
		return distatusname;
	}

	public void setDistatusname(String distatusname) {
		this.distatusname = distatusname;
	}

	public String getFinalstatusname() {
		return finalstatusname;
	}

	public void setFinalstatusname(String finalstatusname) {
		this.finalstatusname = finalstatusname;
	}

	public String getUpdateRequired() {
		return updateRequired;
	}

	public void setUpdateRequired(String updateRequired) {
		this.updateRequired = updateRequired;
	}

	public Timestamp getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Timestamp updateOn) {
		this.updateOn = updateOn;
	}

	
	
	
	
	
}
