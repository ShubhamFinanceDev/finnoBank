package com.kemal.spring.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveryCustomerRepository extends JpaRepository<SurveyCustomer, Integer>{

	
	@Query("select c from SurveyCustomer c where c.applicationnumber=:applicationnumber")
	SurveyCustomer findCustomerByApplication(@Param("applicationnumber")  String applicationnumber);
	
	//c.isSurveyPost=1 and
	@Query("select c from SurveyCustomer c where  c.securitycode =:securitycode")
	SurveyCustomer findCustomerBySecurity(@Param("securitycode") String securitycode);

	@Query("select c from SurveyCustomer c where lower(c.applicationnumber) like lower(concat('%',:keyword,'%')) or lower(c.applicantname) like lower(concat('%',:keyword,'%')) or lower(c.mobileno) like lower(concat('%',:keyword,'%'))  or lower(c.emailid) like lower(concat('%',:keyword,'%'))")
	Page<SurveyCustomer> findallColumns(@Param("keyword") String keyword, Pageable pageable);

}
