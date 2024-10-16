package com.kemal.spring.web.dto;
import java.util.Date;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
public class SurveryDataUpload {
	
	@ExcelRow
	private int rowIndex;
	
	@ExcelCellName("APPLICATION NUMBER")
	private String applicationnumber;
	
	@ExcelCellName("APPLICANT NAME")
	private String applicantname;
	
	@ExcelCellName("MOBILE NUMBER")
	private String mobileno;
	
	@ExcelCellName("EMAIL_ID")
	private String emailid;
	
	@ExcelCellName("CREATE ON")
	private Date createon;
	
	
	@ExcelCellName("CATEGORY")
	private String category;

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
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

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

}
