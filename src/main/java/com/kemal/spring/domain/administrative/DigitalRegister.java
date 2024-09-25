package com.kemal.spring.domain.administrative;

import java.util.Date;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.kemal.spring.domain.User;

@Entity
@Table(name = "administration_digitalregister")
public class DigitalRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "inwarddate")
	private String inwarddate; 
	
	/*
	 * @Column(name = "inwardlocation") private String inwardlocation;
	 * 
	 * @Column(name = "inwarddocketnumber") private String inwarddocketnumber;
	 * 
	 * @Column(name = "inwardname") private String inwardname;
	 */
	
	@Column(name = "inwardreceivername")
	private String inwardreceivername;
	
	@Column(name = "inwardweight")
	private String inwardweight;
	
	@Column(name = "inwardremark")
	private String inwardremark;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminCourierMaster inwardcourierpartner;
	
	@Column(name = "outwarddate")
	private String outwarddate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster outwardlocation;
	
	@Column(name = "outwarddocketnumber")
	private String  outwarddocketnumber;
	
	@Column(name = "outwardsendername")
	private String outwardsendername;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminDepartmentMaster outwarddepartment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminItemMaster outwarditem;
	
	@Column(name = "outwardqty")
	private String outwardqty;
	
	@Column(name = "outwardweight")
	private String outwardweight;
	
	@Column(name = "outwardreceivername")
	private String outwardreceivername;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminCourierMaster outwardcourierpartner;
	
	@Column(name = "outwardassetsdeliverystatus")
	private String outwardassetsdeliverystatus;
	
	@Column(name = "outwardassetsnumber")
	private String outwardassetsnumber;
	
	@Column(name = "outwardsrnumber")
	private String outwardsrnumber;
	
	@Column(name = "outwardmonitornumber")
	private String outwardmonitornumber;
	
	@Column(name = "outwardempcode")
	private String outwardempcode;
	
	@Column(name = "outwardempname")
	private String outwardempname;
	
	@Column(name = "outwardremark")
	private String outwardremark;
	
	
	@Column(name = "createon")
	private Date createdon;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private User createdby;
	
	@Column(name = "active")
	@ColumnDefault("1")
	private int active;


	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminBranchMaster location;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminDepartmentMaster departmentName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminItemMaster itemName;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminCourierMaster courierName;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getInwarddate() {
		return inwarddate;
	}


	public void setInwarddate(String inwarddate) {
		this.inwarddate = inwarddate;
	}


	public String getInwardreceivername() {
		return inwardreceivername;
	}


	public void setInwardreceivername(String inwardreceivername) {
		this.inwardreceivername = inwardreceivername;
	}


	public String getInwardweight() {
		return inwardweight;
	}


	public void setInwardweight(String inwardweight) {
		this.inwardweight = inwardweight;
	}


	public String getInwardremark() {
		return inwardremark;
	}


	public void setInwardremark(String inwardremark) {
		this.inwardremark = inwardremark;
	}


	public AdminCourierMaster getInwardcourierpartner() {
		return inwardcourierpartner;
	}


	public void setInwardcourierpartner(AdminCourierMaster inwardcourierpartner) {
		this.inwardcourierpartner = inwardcourierpartner;
	}


	public String getOutwarddate() {
		return outwarddate;
	}


	public void setOutwarddate(String outwarddate) {
		this.outwarddate = outwarddate;
	}


	public AdminBranchMaster getOutwardlocation() {
		return outwardlocation;
	}


	public void setOutwardlocation(AdminBranchMaster outwardlocation) {
		this.outwardlocation = outwardlocation;
	}


	public String getOutwarddocketnumber() {
		return outwarddocketnumber;
	}


	public void setOutwarddocketnumber(String outwarddocketnumber) {
		this.outwarddocketnumber = outwarddocketnumber;
	}


	public String getOutwardsendername() {
		return outwardsendername;
	}


	public void setOutwardsendername(String outwardsendername) {
		this.outwardsendername = outwardsendername;
	}


	public AdminDepartmentMaster getOutwarddepartment() {
		return outwarddepartment;
	}


	public void setOutwarddepartment(AdminDepartmentMaster outwarddepartment) {
		this.outwarddepartment = outwarddepartment;
	}


	public AdminItemMaster getOutwarditem() {
		return outwarditem;
	}


	public void setOutwarditem(AdminItemMaster outwarditem) {
		this.outwarditem = outwarditem;
	}


	public String getOutwardqty() {
		return outwardqty;
	}


	public void setOutwardqty(String outwardqty) {
		this.outwardqty = outwardqty;
	}


	public String getOutwardweight() {
		return outwardweight;
	}


	public void setOutwardweight(String outwardweight) {
		this.outwardweight = outwardweight;
	}


	public String getOutwardreceivername() {
		return outwardreceivername;
	}


	public void setOutwardreceivername(String outwardreceivername) {
		this.outwardreceivername = outwardreceivername;
	}


	public AdminCourierMaster getOutwardcourierpartner() {
		return outwardcourierpartner;
	}


	public void setOutwardcourierpartner(AdminCourierMaster outwardcourierpartner) {
		this.outwardcourierpartner = outwardcourierpartner;
	}


	public String getOutwardassetsdeliverystatus() {
		return outwardassetsdeliverystatus;
	}


	public void setOutwardassetsdeliverystatus(String outwardassetsdeliverystatus) {
		this.outwardassetsdeliverystatus = outwardassetsdeliverystatus;
	}


	public String getOutwardassetsnumber() {
		return outwardassetsnumber;
	}


	public void setOutwardassetsnumber(String outwardassetsnumber) {
		this.outwardassetsnumber = outwardassetsnumber;
	}


	public String getOutwardsrnumber() {
		return outwardsrnumber;
	}


	public void setOutwardsrnumber(String outwardsrnumber) {
		this.outwardsrnumber = outwardsrnumber;
	}


	public String getOutwardmonitornumber() {
		return outwardmonitornumber;
	}


	public void setOutwardmonitornumber(String outwardmonitornumber) {
		this.outwardmonitornumber = outwardmonitornumber;
	}


	public String getOutwardempcode() {
		return outwardempcode;
	}


	public void setOutwardempcode(String outwardempcode) {
		this.outwardempcode = outwardempcode;
	}


	public String getOutwardempname() {
		return outwardempname;
	}


	public void setOutwardempname(String outwardempname) {
		this.outwardempname = outwardempname;
	}


	public String getOutwardremark() {
		return outwardremark;
	}


	public void setOutwardremark(String outwardremark) {
		this.outwardremark = outwardremark;
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


	public AdminBranchMaster getLocation() {
		return location;
	}


	public void setLocation(AdminBranchMaster location) {
		this.location = location;
	}


	public AdminDepartmentMaster getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(AdminDepartmentMaster departmentName) {
		this.departmentName = departmentName;
	}


	public AdminItemMaster getItemName() {
		return itemName;
	}


	public void setItemName(AdminItemMaster itemName) {
		this.itemName = itemName;
	}


	public AdminCourierMaster getCourierName() {
		return courierName;
	}


	public void setCourierName(AdminCourierMaster courierName) {
		this.courierName = courierName;
	}


	
	
	

	
}