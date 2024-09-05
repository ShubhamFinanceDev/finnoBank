package com.kemal.spring.web.dto;

public class SurveryDataSubmitDTO {

	private int customerId;
	private String applicationnumber;
	private String applicantname;
	private String mobileno;
	private String emailid;
	private String securitycode;
	private String options;
	private String feedback;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

}
