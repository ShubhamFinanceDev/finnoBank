package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveryAnswerRepository extends JpaRepository<SurveyAnswers, Integer> {

	@Query("select c from SurveyAnswers c where c.questions.id =:qid")
	List<SurveyAnswers> findbyQuestionId(@Param("qid") int qid);

	

}
