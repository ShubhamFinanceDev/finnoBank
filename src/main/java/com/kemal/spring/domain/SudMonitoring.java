package com.kemal.spring.domain;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "sud_monitoring")
public class SudMonitoring {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_ID")
	private int id;
	
	@Column(name = "application_number")
	private String applicationnumber;
	
	@Column(name = "branch_name")
	private String branchname;
	
	@Column(name = "REGION")
	private String region;
	
	@Column(name = "customer_name")
	private String customername ;
	
	@Column(name = "PRODUCT")
	private String product;
	
	
	@Column(name = "SCHEME")
	private String scheme;        
	
	@Column(name = " relationship_manager")
	private String  relationshipmanager;   
		
	@Column(name = "current_status")
	private String currentstatus;
	
	@Column(name = "login_date")
	private Date logindate;
	
	@Column(name = "sanction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sanctiondate;
	
	@Column(name = "sanction_loan_amount")
	private Float sanctionloanamount;
	
	@Column(name = "ROI")
	private Float roi;
	
	
	@Column(name = "CREATEDON")
	private Date createdon;
	
	
	@Column(name = "sanctioned_by")
	private String sanctionby;
	
	@Column(name = "TAT")
	private Double tat;
	
	@Column(name = "disbursal_amount ")
	private Double disbursalamount;
	
	/*@Column(name = "APP_ID ")
	private Float appid;*/
	
	
	@Column(name = "sanctionvalue")
	private Float sanctionvalue;
	
	@Column(name = "divalue")
	private Float divalue ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SudLegalStatus legalstatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SudTechnicalStatus technicalstatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SudDpaStatus pastatus;

	@ManyToOne(fetch = FetchType.LAZY)
	private SudDocketStatus docketstatus;

	@ManyToOne(fetch = FetchType.LAZY)
	private SudDiStatus distatus;

	@ManyToOne(fetch = FetchType.LAZY)
	private SudFinalStatus finalstatus;
	
	
	@Column(name = "didateifdoable")
	private String didateifdoable;
	
	@Column(name = "additionalremarks")
	private String additionalremarks;
	
	@Transient
	private String rowColor;

	
	private Date updatedon;
	@ManyToOne(fetch = FetchType.LAZY)
	
	private User updatedby;
	
	private Date createon;
	
	@Column(name = "isVisible")
	private Integer isVisible=1;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	/*public Float getAppid() {
		return appid;
	}

	public void setAppid(Float appid) {
		this.appid = appid;
	}*/

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

	public SudLegalStatus getLegalstatus() {
		return legalstatus;
	}

	public void setLegalstatus(SudLegalStatus legalstatus) {
		this.legalstatus = legalstatus;
	}

	public SudTechnicalStatus getTechnicalstatus() {
		return technicalstatus;
	}

	public void setTechnicalstatus(SudTechnicalStatus technicalstatus) {
		this.technicalstatus = technicalstatus;
	}

	public SudDpaStatus getPastatus() {
		return pastatus;
	}

	public void setPastatus(SudDpaStatus pastatus) {
		this.pastatus = pastatus;
	}

	public SudDocketStatus getDocketstatus() {
		return docketstatus;
	}

	public void setDocketstatus(SudDocketStatus docketstatus) {
		this.docketstatus = docketstatus;
	}

	public SudDiStatus getDistatus() {
		return distatus;
	}

	public void setDistatus(SudDiStatus distatus) {
		this.distatus = distatus;
	}

	public SudFinalStatus getFinalstatus() {
		return finalstatus;
	}

	public void setFinalstatus(SudFinalStatus finalstatus) {
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

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public User getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(User updatedby) {
		this.updatedby = updatedby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	
	
	
}
