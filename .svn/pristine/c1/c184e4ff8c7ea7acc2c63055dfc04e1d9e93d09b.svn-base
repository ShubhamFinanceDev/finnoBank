package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudTechnicalStatusRepository extends JpaRepository<SudTechnicalStatus, Long>{
	
	List<SudTechnicalStatus> active(int active);
	 
}
