package com.kemal.spring.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	/*@Query("select c from BatchDetails c where c.active=1 and c.createdby.id=:userid")
	List<BatchDetails> findActiveBatch(@Param("userid") long userid);

	@Query("select c from BatchDetails c where c.active=1 and c.userstatus='created'")
	List<BatchDetails> findPendingBatch();

	@Query("select c from BatchDetails c where c.active=1")
	List<BatchDetails> findActiveBatch();*/

}
