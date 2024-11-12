package com.kemal.spring.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDetailsRepository extends JpaRepository<BatchDetails, Long> {

	@Query("select c from BatchDetails c where c.active=1 and c.createdby.id=:userid")
	List<BatchDetails> findActiveBatch(@Param("userid") long userid);

	@Query("select c from BatchDetails c where c.active=1 and c.userstatus='created'")
	List<BatchDetails> findPendingBatch();

	@Query("select c from BatchDetails c where c.active=1")
	List<BatchDetails> findActiveBatch();

	@Query("select c from BatchDetails c where c.createdby.id=:userid")
	Page<BatchDetails> findAllBatch(Pageable paging, @Param("userid") long userid);

	@Query("select c from BatchDetails c where c.active=1 and c.createdby.id=:userid")	
	Page<BatchDetails> findActiveBatch(Pageable paging, @Param("userid") long userid);

	@Query("select c from BatchDetails c where c.active=1")
	Page<BatchDetails> findActiveBatch(Pageable paging);

	@Query("select c from BatchDetails c where c.active=1 and c.createdby.id=:userid and (c.batchnumber like lower(concat('%',:keyword,'%')) or c.finobankacknumber like lower(concat('%',:keyword,'%')) or c.userstatus like lower(concat('%',:keyword,'%')))")	
	Page<BatchDetails> findActiveBatch(Pageable paging,@Param("userid") long userid,@Param("keyword") String keyword);

	@Query("select c from BatchDetails c where c.active=1 and (c.batchnumber like lower(concat('%',:keyword,'%')) or c.finobankacknumber like lower(concat('%',:keyword,'%')) or c.userstatus like lower(concat('%',:keyword,'%')))")
	Page<BatchDetails> findActiveBatch(Pageable paging,@Param("keyword") String keyword);

	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) between cast(:fromDate as date) and cast(:toDate as date)")
	Page<BatchDetails> findActiveBatchByDate(Pageable paging, @Param("fromDate") LocalDate fromDate, LocalDate toDate);

	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) between cast(:fromDate as date) and cast(:toDate as date)")
	List<BatchDetails> findActiveBatchByDateForExcel(String fromDate, String toDate);

//	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) = cast(:date as date)")
//	Page<BatchDetails> findActiveBatchByfromDate(Pageable paging, @Param("fromDate") LocalDate fromDate);
//	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) = cast(:date as date)")
//	Page<BatchDetails> findActiveBatchBytoDate(Pageable paging, @Param("toDate") LocalDate toDate);
}
