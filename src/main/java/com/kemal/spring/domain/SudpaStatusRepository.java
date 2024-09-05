package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudpaStatusRepository extends JpaRepository<SudDpaStatus, Long>{
	
	List<SudDpaStatus> active(int active);
	 
}
