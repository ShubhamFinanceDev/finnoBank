package com.kemal.spring.domain.administrative;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PettyCashRepository extends JpaRepository<PettyCash, Integer>{

	List<PettyCash> active(int active);

	

	
}