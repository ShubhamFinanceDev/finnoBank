package com.kemal.spring.web.dto;

import java.util.List;

public class SurveyQuestionsDto {

	private int id;
	private String question;
	private String category;
	

	private List<SurveyAnswersDto> answersList;

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

	public List<SurveyAnswersDto> getAnswersList() {
		return answersList;
	}

	public void setAnswersList(List<SurveyAnswersDto> answersList) {
		this.answersList = answersList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

}
