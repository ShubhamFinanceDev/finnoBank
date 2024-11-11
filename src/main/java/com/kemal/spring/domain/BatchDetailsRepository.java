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
	Page<BatchDetails> findActiveBatchByUserId(Pageable paging, @Param("userid") long userid);

	@Query("select c from BatchDetails c where c.active=1")
	Page<BatchDetails> findActiveBatch(Pageable paging);

	@Query("select c from BatchDetails c where c.active=1 and c.createdby.id=:userid and (c.batchnumber like lower(concat('%',:keyword,'%')) or c.finobankacknumber like lower(concat('%',:keyword,'%')) or c.userstatus like lower(concat('%',:keyword,'%')))")	
	Page<BatchDetails> findActiveBatch(Pageable paging,@Param("userid") long userid,@Param("keyword") String keyword);

	@Query("select c from BatchDetails c where c.active=1 and (c.batchnumber like lower(concat('%',:keyword,'%')) or c.finobankacknumber like lower(concat('%',:keyword,'%')) or c.userstatus like lower(concat('%',:keyword,'%')))")
	Page<BatchDetails> findActiveBatch(Pageable paging,@Param("keyword") String keyword);

	@Query("select c from BatchDetails c where c.active = 1 and c.createdby.id = :userid and function('DATE', c.createon) = :date")
	Page<BatchDetails> findActiveBatchByUserIdAndDate(Pageable paging, @Param("userid") long userid, @Param("date") LocalDate date);

	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) = cast(:date as date)")
	Page<BatchDetails> findActiveBatchByDate(Pageable paging, @Param("date") LocalDate date);

	@Query("select c from BatchDetails c where c.active = 1 and function('DATE', c.createon) = cast(:date as date)")
	List<BatchDetails> findActiveBatchByDateForExcel(String date);
}
