package com.kemal.spring.domain;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class SurveyCustomer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")

	private int id;
	private String applicationnumber;
	private String applicantname;
	private String mobileno;
	private String emailid;
	private Date createon;
	private String securitycode;
	private Date expiredon;
	private Integer isSurveyPost = 0;
	private String feedback;
	private String category;
	

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

	public String getApplicantname() {
		return applicantname;
	}

	public void setApplicantname(String applicantname) {
		this.applicantname = applicantname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public Date getExpiredon() {
		return expiredon;
	}

	public void setExpiredon(Date expiredon) {
		this.expiredon = expiredon;
	}

	public Integer getIsSurveyPost() {
		return isSurveyPost;
	}

	public void setIsSurveyPost(Integer isSurveyPost) {
		this.isSurveyPost = isSurveyPost;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

}
