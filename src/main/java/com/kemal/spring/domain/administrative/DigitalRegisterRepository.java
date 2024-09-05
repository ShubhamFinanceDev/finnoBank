package com.kemal.spring.domain.administrative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalRegisterRepository extends JpaRepository<DigitalRegister, Integer>{
	
	List<DigitalRegister> active(int active);

	@Query("select c from DigitalRegister c where  c.active=1 and c.outwarddocketnumber =:docketnumber")
	DigitalRegister finddigitalbyDocketnumber(@Param("docketnumber") String docketnumber);
	 
}
