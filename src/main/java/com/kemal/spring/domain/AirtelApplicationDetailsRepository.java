package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirtelApplicationDetailsRepository extends JpaRepository<AirtelApplicationDetails, Long>{
	
	AirtelApplicationDetails findBycustomername(String customername);
	  
	  @Query("select c from AirtelApplicationDetails c where c.batchDetails.id=:batchid")
	  List<AirtelApplicationDetails> findApplicationBybatchid(@Param("batchid") long batchid);

}
