package com.kemal.spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.kemal.spring.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemal.spring.web.dto.batchpagingDto;

@Service
public class BatchDetailsService {

	@Autowired
	private UserService userService;
	private BatchDetailsRepository batchDetailsRepository;

	private ApplicationDetailsRepository applicationDetailsRepository;
	String[] colors = new String[] { /*
										 * "#6495ED", "#FFFACD", "#ADD8E6", "#90EE90", "#FFA07A", "#20B2AA", "#9370DB",
										 * "#7B68EE", "#FFC0CB", "#4682B4", "#40E0D0"
										 */

			 "#F8F8F8" };

	public BatchDetailsService(BatchDetailsRepository batchDetailsRepository,
			ApplicationDetailsRepository applicationDetailsRepository) {
		this.batchDetailsRepository = batchDetailsRepository;
		this.applicationDetailsRepository = applicationDetailsRepository;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public List<batchpagingDto> findApplicationBybatchid(Long userid, String date) {
		// TODO Auto-generated method stub
		List<batchpagingDto> returnlist = new ArrayList<batchpagingDto>();

		List<BatchDetails> list = new ArrayList<>();
		if (userid != null) {
			list = batchDetailsRepository.findActiveBatch(userid);
		} else if (date != null){
			list = batchDetailsRepository.findActiveBatchByDateForExcel(date);
		} else {
			list = batchDetailsRepository.findActiveBatch();
		}

		if (list != null && list.size() > 0) {
			int index = 1;
			for (BatchDetails batch : list) {

				List<ApplicationDetails> batchlist = applicationDetailsRepository
						.findApplicationBybatchid(batch.getId());
				if (batchlist != null && batchlist.size() > 0) {
					String color = colors[randInt(0, colors.length - 1)];
					int batchindex = 1;
					for (ApplicationDetails app : batchlist) {
						System.out.println(index + "<===============================================>" + batchindex);
						batchpagingDto batches = new batchpagingDto();
						batches.setRowColor(color);
						batches.setId((batch.getId()).intValue());
						if (batchindex == 1) {
							// add batch data here (add those fields which is repeated)
							// get from batch
							// app unique get values
							// batches set
							// batches.setId(batch.getId());
							batches.setCreatedby(
									batch.getCreatedby().getName() + " " + batch.getCreatedby().getSurname());
							batches.setCreateon(batch.getCreateon());

							batches.setBatchnumber(batch.getBatchnumber());
							batches.setFinobankacknumber(batch.getFinobankacknumber());
							batches.setPaymentdate(batch.getPaymentdate());
							batches.setTotalcollectedamount(batch.getTotalcollectedamount().toString());
							batches.setEmpcode(batch.getCreatedby().getEmpcode());
							batches.setFinobankacknumber(batch.getFinobankacknumber());

							batches.setBranchname(batch.getCreatedby().getEmpbranch());
							// batches.setBranchname(batch.getCreatedby().getEmpbranch());

							batches.setDepositon(batch.getDepositon());
							batches.setUserstatus(batch.getUserstatus());

						}

						// add application data here (add those fields which is not repeating)
						batches.setTotaldue(app.getTotaldue().toString());
						batches.setReciptnumber(app.getReciptnumber());
						batches.setPaymentype(app.getPaymentype());
						batches.setLoannumber(app.getLoannumber());
						batches.setCustomername(app.getCustomername());
						batches.setEmiamount(app.getEmiamount().toString());
						batches.setCollectedamount(app.getCollectedAmount().toString());

						index++;
						batchindex = batchindex + 1;
						returnlist.add(batches);
					}
				}
			}
		}
		return returnlist;
	}

	public BatchDetails findById(Long id) {
		// TODO Auto-generated method stub
		return batchDetailsRepository.findById(id).get();
	}

	public BatchDetails save(BatchDetails batch) {
		return batchDetailsRepository.save(batch);

	}

	public Object findPendingApplicationBybatchid() {
		List<batchpagingDto> returnlist = new ArrayList<batchpagingDto>();

		List<BatchDetails> list = batchDetailsRepository.findPendingBatch();

		if (list != null && list.size() > 0) {
			int index = 1;
			for (BatchDetails batch : list) {

				List<ApplicationDetails> batchlist = applicationDetailsRepository
						.findApplicationBybatchid(batch.getId());
				if (batchlist != null && batchlist.size() > 0) {
					int batchindex = 1;
					String color = colors[randInt(0, colors.length - 1)];
					for (ApplicationDetails app : batchlist) {
						System.out.println(index + "<===============================================>" + batchindex);
						batchpagingDto batches = new batchpagingDto();
						batches.setId((batch.getId()).intValue());
						batches.setRowColor(color);
						if (batchindex == 1) {
							// add batch data here (add those fields which is repeated)
							// get from batch
							// app unique get values
							// batches set
							// batches.setId(batch.getId());
							batches.setCreatedby(batch.getCreatedby().getName());
							batches.setCreateon(batch.getCreateon());

							batches.setBatchnumber(batch.getBatchnumber());
							batches.setFinobankacknumber(batch.getFinobankacknumber());
							batches.setPaymentdate(batch.getPaymentdate());
							batches.setTotalcollectedamount(batch.getTotalcollectedamount().toString());
							batches.setEmpcode(batch.getCreatedby().getEmpcode());
							batches.setFinobankacknumber(batch.getFinobankacknumber());

							batches.setBranchname(batch.getCreatedby().getEmpbranch());
							// batches.setBranchname(batch.getCreatedby().getEmpbranch());

							batches.setDepositon(batch.getDepositon());
							batches.setUserstatus(batch.getUserstatus());

						}

						// add application data here (add those fields which is not repeating)
						batches.setTotaldue(app.getTotaldue().toString());
						batches.setReciptnumber(app.getReciptnumber());
						batches.setPaymentype(app.getPaymentype());
						batches.setLoannumber(app.getLoannumber());
						batches.setCustomername(app.getCustomername());
						batches.setEmiamount(app.getEmiamount().toString());
						batches.setCollectedamount(app.getCollectedAmount().toString());

						index++;
						batchindex = batchindex + 1;
						returnlist.add(batches);
					}
				}
			}
		}
		return returnlist;
	}

	public Page<BatchDetails> findApplicationBybatchIdPaging(Pageable paging, long userid, LocalDate date) {

		if (userid != 0 && date != null) {
			System.out.println(userid + "  userid and date in findApplicationBybatchIdPaging");
			return batchDetailsRepository.findActiveBatchByUserIdAndDate(paging, userid, date);
		} else if (userid != 0) {
			return batchDetailsRepository.findActiveBatchByUserId(paging, userid);
		} else if (date != null) {
			return batchDetailsRepository.findActiveBatchByDate(paging, date);
		} else {
			return batchDetailsRepository.findActiveBatch(paging);
		}
	}

	public Page<BatchDetails> findApplicationBybatchidByTitleContainingIgnoreCase(String keyword, Pageable paging,
			long userid) {
		if (userid != 0) {
			return batchDetailsRepository.findActiveBatch(paging, userid, keyword);
		} else {
			return batchDetailsRepository.findActiveBatch(paging, keyword);
		}
	}

	public  Page<BatchDetails> findApplicationByEmployeeId(String keyword, Pageable paging)
	{
		Optional<User> user= userService.finByEmployeeCode(keyword);
		return user.isPresent() ? batchDetailsRepository.findAllBatch(paging,user.get().getId()): batchDetailsRepository.findActiveBatch(paging);

	}

	public List<batchpagingDto> findApplicationBybatchDto(List<BatchDetails> content) {
		List<batchpagingDto> returnlist = new ArrayList<batchpagingDto>();

		if (content != null && content.size() > 0) {
			int index = 1;
			for (BatchDetails batch : content) {

				List<ApplicationDetails> batchlist = applicationDetailsRepository
						.findApplicationBybatchid(batch.getId());
				if (batchlist != null && batchlist.size() > 0) {
					String color = colors[randInt(0, colors.length - 1)];
					int batchindex = 1;
					for (ApplicationDetails app : batchlist) {
						System.out.println(index + "<===============================================>" + batchindex);
						batchpagingDto batches = new batchpagingDto();
						batches.setRowColor(color);
						batches.setId((batch.getId()).intValue());
						if (batchindex == 1) {
							// add batch data here (add those fields which is repeated)
							// get from batch
							// app unique get values
							// batches set
							// batches.setId(batch.getId());
							batches.setCreatedby(
									batch.getCreatedby().getName() + " " + batch.getCreatedby().getSurname());
							batches.setCreateon(batch.getCreateon());

							batches.setBatchnumber(batch.getBatchnumber());
							batches.setFinobankacknumber(batch.getFinobankacknumber());
							batches.setPaymentdate(batch.getPaymentdate());
							batches.setTotalcollectedamount(batch.getTotalcollectedamount().toString());
							batches.setEmpcode(batch.getCreatedby().getEmpcode());
							batches.setFinobankacknumber(batch.getFinobankacknumber());

							batches.setBranchname(batch.getCreatedby().getEmpbranch());
							// batches.setBranchname(batch.getCreatedby().getEmpbranch());

							batches.setDepositon(batch.getDepositon());
							batches.setUserstatus(batch.getUserstatus());

						}

						// add application data here (add those fields which is not repeating)
						batches.setTotaldue(app.getTotaldue().toString());
						batches.setReciptnumber(app.getReciptnumber());
						batches.setPaymentype(app.getPaymentype());
						batches.setLoannumber(app.getLoannumber());
						batches.setCustomername(app.getCustomername());
						batches.setEmiamount(app.getEmiamount().toString());
						batches.setCollectedamount(app.getCollectedAmount().toString());

						index++;
						batchindex = batchindex + 1;
						returnlist.add(batches);
					}
				}
			}
		}
		return returnlist;
	}

}
