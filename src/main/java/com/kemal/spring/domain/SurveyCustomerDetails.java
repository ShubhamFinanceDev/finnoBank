package com.kemal.spring.domain;

import java.util.Date;

import jakarta.persistence.*;





@Entity
public class SurveyCustomerDetails {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	private Date createon;
	private String category;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SurveyCustomer customerid;
	

	@ManyToOne(fetch = FetchType.LAZY)
	private SurveyQuestions questionid;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SurveyAnswers answerid;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public SurveyCustomer getCustomerid() {
		return customerid;
	}


	public void setCustomerid(SurveyCustomer customerid) {
		this.customerid = customerid;
	}


	public SurveyQuestions getQuestionid() {
		return questionid;
	}


	public void setQuestionid(SurveyQuestions questionid) {
		this.questionid = questionid;
	}


	public SurveyAnswers getAnswerid() {
		return answerid;
	}


	public void setAnswerid(SurveyAnswers answerid) {
		this.answerid = answerid;
	}


	public Date getCreateon() {
		return createon;
	}


	public void setCreateon(Date createon) {
		this.createon = createon;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
	

}
