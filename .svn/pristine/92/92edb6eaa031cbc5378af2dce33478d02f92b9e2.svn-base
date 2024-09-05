package com.kemal.spring.domain.administrative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminItemRepository extends JpaRepository<AdminItemMaster, Long>{
	
	 List<AdminItemMaster> active(int active);
	 
}
