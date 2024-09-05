package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveryQuestionRepository extends JpaRepository<SurveyQuestions, Integer> {

	
	@Query("select c from SurveyQuestions c where c.category=:category")
	List<SurveyQuestions> findAllByCategory(@Param("category") String category);

}
