package com.kemal.spring.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SurveyAnswers {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private  SurveyQuestions questions ;
	
	private String answers;
	
	private Date createon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SurveyQuestions getQuestions() {
		return questions;
	}

	public void setQuestions(SurveyQuestions questions) {
		this.questions = questions;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}
	


	
	

}
