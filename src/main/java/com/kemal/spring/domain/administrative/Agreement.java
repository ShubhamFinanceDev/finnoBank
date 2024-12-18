package com.kemal.spring.domain.administrative;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.annotations.ColumnDefault;

import com.kemal.spring.domain.User;



@Entity
@Table(name = "administration_agreement")
public class Agreement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "Cities")
	private String cities;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster branchcode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster  branchname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster state;
	
	
	@Column(name = "Shiftclosedactiveclosed")
	private String shiftclosedactiveclosed;
	
	
	@Column(name = "Branchopenn")
	private String branchopenon;
	
	@Column(name = "BranchopendateasperNHB")
	private String branchopendateasperNHB;
	
	

	@Column(name = "Nhbintimationdate")
	private String nhbintimationdate;
	

	@Column(name = "Agreementfrom")
	private String agreementfrom;
	

	@Column(name = "BranchClosingAgreementValidTill")
	private String branchclosingagreementvalidtill;
	

	@Column(name = "Agreementfor")
	private String agreementfor;
	

	@Column(name = "Flag")
	private String flag;
	

	@Column(name = "CompleteAddressofcurrentlocation")
	private String completeAddressofCurrentLocation;
	

	@Column(name = "Pincode")
	private String pincode;
	

	@Column(name = "Geotag")
	private String geotag;
		
	
	@Column(name = "Carpetarea")
	private String carpetarea;
	
	@Column(name = "Ac")
	private String ac;
	
	@Column(name = "Electricityviadirectsub")
	private String electricityviadirectsub;
	
	@Column(name = "Rateperunits")
	private String rateperunits;
	
	@Column(name = "Workstation")
	private String workstation;
	
	@Column(name = "Cubical")
	private String cubical;
	
	@Column(name = "meetingroom")
	private String meetingroom;
	
	@Column(name = "Cabin")
	private String cabin;
	
	@Column(name = "LandlordName")
	private String landlordname;
	
	
	@Column(name = "Sdamounts")
	private String sdamounts;
	
	@Column(name = "Rent")
	private String rent;
	
	@Column(name = "Maintenance")
	private String maintenance;
	
	@Column(name = "Esclation")
	private String esclation;
	
	@Column(name = "Stampduty")
	private String stampduty;
	
	@Column(name = "Contactperson")
	private String contactperson;
	
	@Column(name = "Contactnumber")
	private String contactnumber;
	
	@Column(name = "Emailid")
	private String emailid;
	
	@Column(name = "Shopacecertificaneno")
	private String shopacecertificaneno;
	
	
	
	@Column(name = "Opton")
	private String opton;
	
	@Column(name = "Sevalidfrom")
	private String sevalidfrom;
	
	@Column(name = "Sevalidtill")
	private String sevalidtill;
	
	@Column(name = "Remarks")
	private String remarks;
	
	@Column(name = "createon")
	private Date createdon;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private User createdby;

	

	@Column(name = "active")
	@ColumnDefault("1")
	private int active=1;


	@Column(name = "filename")
	private String filename;

	@Lob
	@Column(name = "agreementpdf")
	private String agreementpdf;
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getCities() {
		return cities;
	}



	public void setCities(String cities) {
		this.cities = cities;
	}



	public AdminBranchMaster getBranchcode() {
		return branchcode;
	}



	public void setBranchcode(AdminBranchMaster branchcode) {
		this.branchcode = branchcode;
	}



	public AdminBranchMaster getLocation() {
		return location;
	}



	public void setLocation(AdminBranchMaster location) {
		this.location = location;
	}



	public AdminBranchMaster getBranchname() {
		return branchname;
	}



	public void setBranchname(AdminBranchMaster branchname) {
		this.branchname = branchname;
	}



	public AdminBranchMaster getState() {
		return state;
	}



	public void setState(AdminBranchMaster state) {
		this.state = state;
	}



	public String getShiftclosedactiveclosed() {
		return shiftclosedactiveclosed;
	}



	public void setShiftclosedactiveclosed(String shiftclosedactiveclosed) {
		this.shiftclosedactiveclosed = shiftclosedactiveclosed;
	}



	public String getBranchopenon() {
		return branchopenon;
	}



	public void setBranchopenon(String branchopenon) {
		this.branchopenon = branchopenon;
	}



	public String getBranchopendateasperNHB() {
		return branchopendateasperNHB;
	}



	public void setBranchopendateasperNHB(String branchopendateasperNHB) {
		this.branchopendateasperNHB = branchopendateasperNHB;
	}



	public String getNhbintimationdate() {
		return nhbintimationdate;
	}



	public void setNhbintimationdate(String nhbintimationdate) {
		this.nhbintimationdate = nhbintimationdate;
	}



	public String getAgreementfrom() {
		return agreementfrom;
	}



	public void setAgreementfrom(String agreementfrom) {
		this.agreementfrom = agreementfrom;
	}



	public String getBranchclosingagreementvalidtill() {
		return branchclosingagreementvalidtill;
	}



	public void setBranchclosingagreementvalidtill(String branchclosingagreementvalidtill) {
		this.branchclosingagreementvalidtill = branchclosingagreementvalidtill;
	}



	public String getAgreementfor() {
		return agreementfor;
	}



	public void setAgreementfor(String agreementfor) {
		this.agreementfor = agreementfor;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getCompleteAddressofCurrentLocation() {
		return completeAddressofCurrentLocation;
	}



	public void setCompleteAddressofCurrentLocation(String completeAddressofCurrentLocation) {
		this.completeAddressofCurrentLocation = completeAddressofCurrentLocation;
	}



	public String getPincode() {
		return pincode;
	}



	public void setPincode(String pincode) {
		this.pincode = pincode;
	}



	public String getGeotag() {
		return geotag;
	}



	public void setGeotag(String geotag) {
		this.geotag = geotag;
	}



	public String getCarpetarea() {
		return carpetarea;
	}



	public void setCarpetarea(String carpetarea) {
		this.carpetarea = carpetarea;
	}



	public String getAc() {
		return ac;
	}



	public void setAc(String ac) {
		this.ac = ac;
	}



	public String getElectricityviadirectsub() {
		return electricityviadirectsub;
	}



	public void setElectricityviadirectsub(String electricityviadirectsub) {
		this.electricityviadirectsub = electricityviadirectsub;
	}



	public String getRateperunits() {
		return rateperunits;
	}



	public void setRateperunits(String rateperunits) {
		this.rateperunits = rateperunits;
	}



	public String getWorkstation() {
		return workstation;
	}



	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}



	public String getCubical() {
		return cubical;
	}



	public void setCubical(String cubical) {
		this.cubical = cubical;
	}



	public String getMeetingroom() {
		return meetingroom;
	}



	public void setMeetingroom(String meetingroom) {
		this.meetingroom = meetingroom;
	}



	public String getCabin() {
		return cabin;
	}



	public void setCabin(String cabin) {
		this.cabin = cabin;
	}



	public String getLandlordname() {
		return landlordname;
	}



	public void setLandlordname(String landlordname) {
		this.landlordname = landlordname;
	}



	public String getSdamounts() {
		return sdamounts;
	}



	public void setSdamounts(String sdamounts) {
		this.sdamounts = sdamounts;
	}



	public String getRent() {
		return rent;
	}



	public void setRent(String rent) {
		this.rent = rent;
	}



	public String getMaintenance() {
		return maintenance;
	}



	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}



	public String getEsclation() {
		return esclation;
	}



	public void setEsclation(String esclation) {
		this.esclation = esclation;
	}



	public String getStampduty() {
		return stampduty;
	}



	public void setStampduty(String stampduty) {
		this.stampduty = stampduty;
	}



	public String getContactperson() {
		return contactperson;
	}



	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}



	public String getContactnumber() {
		return contactnumber;
	}



	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}



	public String getEmailid() {
		return emailid;
	}



	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}



	public String getShopacecertificaneno() {
		return shopacecertificaneno;
	}



	public void setShopacecertificaneno(String shopacecertificaneno) {
		this.shopacecertificaneno = shopacecertificaneno;
	}



	public String getOpton() {
		return opton;
	}



	public void setOpton(String opton) {
		this.opton = opton;
	}



	public String getSevalidfrom() {
		return sevalidfrom;
	}



	public void setSevalidfrom(String sevalidfrom) {
		this.sevalidfrom = sevalidfrom;
	}



	public String getSevalidtill() {
		return sevalidtill;
	}



	public void setSevalidtill(String sevalidtill) {
		this.sevalidtill = sevalidtill;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public Date getCreatedon() {
		return createdon;
	}



	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}



	public User getCreatedby() {
		return createdby;
	}



	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}



	public int getActive() {
		return active;
	}



	public void setActive(int active) {
		this.active = active;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getAgreementpdf() {
		return agreementpdf;
	}



	public void setAgreementpdf(String agreementpdf) {
		this.agreementpdf = agreementpdf;
	}
	
	
}
