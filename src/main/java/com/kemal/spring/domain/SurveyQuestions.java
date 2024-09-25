package com.kemal.spring.domain;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class SurveyQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "question", length = 500)
	private String question;
	
	private Date createon;
	
	private String category;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
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
