package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails, Long>{
	
	  ApplicationDetails findBycustomername(String customername);
	  
	  @Query("select c from ApplicationDetails c where c.batchDetails.id=:batchid")
	  List<ApplicationDetails> findApplicationBybatchid(@Param("batchid") long batchid);

}
