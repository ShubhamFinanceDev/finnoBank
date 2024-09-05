package com.kemal.spring.domain.administrative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer>{

	

		List<Agreement> active(int active);
}


