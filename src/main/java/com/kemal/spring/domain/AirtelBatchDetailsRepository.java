package com.kemal.spring.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirtelBatchDetailsRepository extends JpaRepository<AirtelBatchDetails, Long> {

	@Query("select c from AirtelBatchDetails c where c.active=1 and c.createdby.id=:userid")
	List<AirtelBatchDetails> findActiveBatch(@Param("userid") long userid);

	@Query("select c from AirtelBatchDetails c where c.active=1 and c.userstatus='created'")
	List<AirtelBatchDetails> findPendingBatch();

	@Query("select c from AirtelBatchDetails c where c.active=1")
	List<AirtelBatchDetails> findActiveBatch();

	@Query("select c from AirtelBatchDetails c where c.active=1 and c.createdby.id=:userid")	
	Page<AirtelBatchDetails> findActiveBatch(Pageable paging, @Param("userid") long userid);

	@Query("select c from AirtelBatchDetails c where c.active=1")
	Page<AirtelBatchDetails> findActiveBatch(Pageable paging);

	@Query("select c from AirtelBatchDetails c where c.active=1 and c.createdby.id=:userid and (c.batchnumber like lower(concat('%',:keyword,'%')) or c.finobankacknumber like lower(concat('%',:keyword,'%')) or c.userstatus like lower(concat('%',:keyword,'%')))")	
	Page<AirtelBatchDetails> findActiveBatch(Pageable paging,@Param("userid") long userid,@Param("keyword") String keyword);

	@Query("select c from BatchDetails c where c.active=1 and (c.batchnumber like lower(concat('%',:keyword,'%')) or c.finobankacknumber like lower(concat('%',:keyword,'%')) or c.userstatus like lower(concat('%',:keyword,'%')))")
	Page<AirtelBatchDetails> findActiveBatch(Pageable paging,@Param("keyword") String keyword);

	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) = cast(:date as date)")
    List<AirtelBatchDetails> findActiveBatchByDateForCsv(String date);
}
