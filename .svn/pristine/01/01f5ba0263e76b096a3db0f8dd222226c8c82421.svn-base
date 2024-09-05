package com.kemal.spring.domain.administrative;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.kemal.spring.domain.User;

@Entity
@Table(name = "administration_pettycash")
public class PettyCash {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	

	
	@Column(name = "claimReceivedDate")
	private String claimReceivedDate;
	@Column(name = "manpower")
	private String manpower;
	@Column(name = "month")
	private String month;

	@Column(name = "housekeeping")
	private String housekeeping;

	@Column(name = "sweeper")
	private String sweeper;
	
	
	@Column(name = "tea")
	private String tea;
	
	@Column(name = "SnacksLunchDinner")
	private String snackslunchdinner;

	

	@Column(name = "water")
	private String water;
	@Column(name = "photocopy")
	private String photocopy;
	@Column(name = "stationery")
	private String stationery;
	@Column(name = "buildingMaintenance")
	private String buildingMaintenance;

	@Column(name = "elecBill")
	private String elecBill;
	@Column(name = "crockeryItems")
	private String crockeryItems;
	@Column(name = "housekeepingitem")
	private String housekeepingitem;
	@Column(name = "otherofficeExpenses")
	private String otherofficeExpenses;
	@Column(name = "computerMaintenance")
	private String computerMaintenance;
	@Column(name = "advertisement")
	private String advertisement;
	@Column(name = "legal")
	private String legal;
	@Column(name = "courier")
	private String courier;
	@Column(name = "telephoneinternet")
	private String telephoneinternet;
	@Column(name = "repairMaintenance")
	private String repairMaintenance;
	@Column(name = "acmaintenance")
	private String acmaintenance;
	@Column(name = "occassionalyeventsparty")
	private String occassionalyeventparty;
	
	@Column(name = "total")
	private String total;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "createon")
	private Date createdon;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private User createdby;

	@Column(name = "active")
	@ColumnDefault("1")
	private int active=1;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster branchName;
	
	
	@Column(name = "State")
	private String state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminDepartmentMaster departmentName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaimReceivedDate() {
		return claimReceivedDate;
	}

	public void setClaimReceivedDate(String claimReceivedDate) {
		this.claimReceivedDate = claimReceivedDate;
	}

	public String getManpower() {
		return manpower;
	}

	public void setManpower(String manpower) {
		this.manpower = manpower;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getHousekeeping() {
		return housekeeping;
	}

	public void setHousekeeping(String housekeeping) {
		this.housekeeping = housekeeping;
	}

	public String getSweeper() {
		return sweeper;
	}

	public void setSweeper(String sweeper) {
		this.sweeper = sweeper;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getSnackslunchdinner() {
		return snackslunchdinner;
	}

	public void setSnackslunchdinner(String snackslunchdinner) {
		this.snackslunchdinner = snackslunchdinner;
	}

	public String getWater() {
		return water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	public String getPhotocopy() {
		return photocopy;
	}

	public void setPhotocopy(String photocopy) {
		this.photocopy = photocopy;
	}

	public String getStationery() {
		return stationery;
	}

	public void setStationery(String stationery) {
		this.stationery = stationery;
	}

	public String getBuildingMaintenance() {
		return buildingMaintenance;
	}

	public void setBuildingMaintenance(String buildingMaintenance) {
		this.buildingMaintenance = buildingMaintenance;
	}

	public String getElecBill() {
		return elecBill;
	}

	public void setElecBill(String elecBill) {
		this.elecBill = elecBill;
	}

	public String getCrockeryItems() {
		return crockeryItems;
	}

	public void setCrockeryItems(String crockeryItems) {
		this.crockeryItems = crockeryItems;
	}

	public String getHousekeepingitem() {
		return housekeepingitem;
	}

	public void setHousekeepingitem(String housekeepingitem) {
		this.housekeepingitem = housekeepingitem;
	}

	public String getOtherofficeExpenses() {
		return otherofficeExpenses;
	}

	public void setOtherofficeExpenses(String otherofficeExpenses) {
		this.otherofficeExpenses = otherofficeExpenses;
	}

	public String getComputerMaintenance() {
		return computerMaintenance;
	}

	public void setComputerMaintenance(String computerMaintenance) {
		this.computerMaintenance = computerMaintenance;
	}

	public String getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String getTelephoneinternet() {
		return telephoneinternet;
	}

	public void setTelephoneinternet(String telephoneinternet) {
		this.telephoneinternet = telephoneinternet;
	}

	public String getRepairMaintenance() {
		return repairMaintenance;
	}

	public void setRepairMaintenance(String repairMaintenance) {
		this.repairMaintenance = repairMaintenance;
	}

	public String getAcmaintenance() {
		return acmaintenance;
	}

	public void setAcmaintenance(String acmaintenance) {
		this.acmaintenance = acmaintenance;
	}

	public String getOccassionalyeventparty() {
		return occassionalyeventparty;
	}

	public void setOccassionalyeventparty(String occassionalyeventparty) {
		this.occassionalyeventparty = occassionalyeventparty;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public AdminBranchMaster getBranchName() {
		return branchName;
	}

	public void setBranchName(AdminBranchMaster branchName) {
		this.branchName = branchName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public AdminDepartmentMaster getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(AdminDepartmentMaster departmentName) {
		this.departmentName = departmentName;
	}

	
	
	

}
