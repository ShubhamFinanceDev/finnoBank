package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudDocketStatusRepository extends JpaRepository<SudDocketStatus, Long>{
	
	List<SudDocketStatus> active(int active);
	 
}
