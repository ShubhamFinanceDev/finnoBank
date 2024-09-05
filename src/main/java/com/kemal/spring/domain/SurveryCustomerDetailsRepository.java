package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveryCustomerDetailsRepository extends JpaRepository<SurveyCustomerDetails, Integer>{

	
	@Query("SELECT u FROM SurveyCustomerDetails u WHERE u.customerid.id = (:id)")
	List<SurveyCustomerDetails> findCustomerSurveryDetails(@Param("id") int id);
	
	

}
