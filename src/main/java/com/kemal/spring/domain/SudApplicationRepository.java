package com.kemal.spring.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudApplicationRepository extends JpaRepository<SudApplication, Long>{

	 
	
}
