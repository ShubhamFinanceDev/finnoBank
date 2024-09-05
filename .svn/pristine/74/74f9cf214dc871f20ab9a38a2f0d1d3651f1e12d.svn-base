package com.kemal.spring.domain.administrative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDepartmentRepository extends JpaRepository<AdminDepartmentMaster, Long>{
	
	 List<AdminDepartmentMaster> active(int active);
	 
}
