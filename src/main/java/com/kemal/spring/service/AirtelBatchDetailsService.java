package com.kemal.spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemal.spring.domain.AirtelApplicationDetails;
import com.kemal.spring.domain.AirtelApplicationDetailsRepository;
import com.kemal.spring.domain.AirtelBatchDetails;
import com.kemal.spring.domain.AirtelBatchDetailsRepository;
import com.kemal.spring.web.dto.batchpagingDto;

@Service
public class AirtelBatchDetailsService {

	private AirtelBatchDetailsRepository batchDetailsRepository;

	private AirtelApplicationDetailsRepository applicationDetailsRepository;
	String[] colors = new String[] { /*
										 * "#6495ED", "#FFFACD", "#ADD8E6", "#90EE90", "#FFA07A", "#20B2AA", "#9370DB",
										 * "#7B68EE", "#FFC0CB", "#4682B4", "#40E0D0"
										 */
			"#F8F8F8" };

	public AirtelBatchDetailsService(AirtelBatchDetailsRepository batchDetailsRepository,
			AirtelApplicationDetailsRepository applicationDetailsRepository) {
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

		List<AirtelBatchDetails> list = new ArrayList<>();
		if (userid != null) {
			list = batchDetailsRepository.findActiveBatch(userid);
		}
		else if (date != null){
			list = batchDetailsRepository.findActiveBatchByDateForCsv(date);
		}else {
			list = batchDetailsRepository.findActiveBatch();
		}

		if (list != null && list.size() > 0) {
			int index = 1;
			for (AirtelBatchDetails batch : list) {

				List<AirtelApplicationDetails> batchlist = applicationDetailsRepository
						.findApplicationBybatchid(batch.getId());
				if (batchlist != null && batchlist.size() > 0) {
					String color = colors[randInt(0, colors.length - 1)];
					int batchindex = 1;
					for (AirtelApplicationDetails app : batchlist) {
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

	public AirtelBatchDetails findById(Long id) {
		// TODO Auto-generated method stub
		return batchDetailsRepository.findById(id).get();
	}

	public AirtelBatchDetails save(AirtelBatchDetails batch) {
		return batchDetailsRepository.save(batch);

	}

	public Object findPendingApplicationBybatchid() {
		List<batchpagingDto> returnlist = new ArrayList<batchpagingDto>();

		List<AirtelBatchDetails> list = batchDetailsRepository.findPendingBatch();

		if (list != null && list.size() > 0) {
			int index = 1;
			for (AirtelBatchDetails batch : list) {

				List<AirtelApplicationDetails> batchlist = applicationDetailsRepository
						.findApplicationBybatchid(batch.getId());
				if (batchlist != null && batchlist.size() > 0) {
					int batchindex = 1;
					String color = colors[randInt(0, colors.length - 1)];
					for (AirtelApplicationDetails app : batchlist) {
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

	public Page<AirtelBatchDetails> findApplicationBybatchIdPaging(Pageable paging, long userid, LocalDate date) {

		if (userid != 0) {
			return batchDetailsRepository.findActiveBatch(paging, userid);
		} else {
			return batchDetailsRepository.findActiveBatch(paging);
		}

	}

	public Page<AirtelBatchDetails> findApplicationBybatchidByTitleContainingIgnoreCase(String keyword, Pageable paging,
																						long userid, LocalDate date) {
		if (userid != 0) {
			return batchDetailsRepository.findActiveBatch(paging, userid, keyword);
		} else {
			return batchDetailsRepository.findActiveBatch(paging, keyword);
		}
	}

	public List<batchpagingDto> findApplicationBybatchDto(List<AirtelBatchDetails> content) {
		List<batchpagingDto> returnlist = new ArrayList<batchpagingDto>();

		if (content != null && content.size() > 0) {
			int index = 1;
			for (AirtelBatchDetails batch : content) {

				List<AirtelApplicationDetails> batchlist = applicationDetailsRepository
						.findApplicationBybatchid(batch.getId());
				if (batchlist != null && batchlist.size() > 0) {
					String color = colors[randInt(0, colors.length - 1)];
					int batchindex = 1;
					for (AirtelApplicationDetails app : batchlist) {
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
						batches.setTotaldue(app.getTotaldue() != null ? app.getTotaldue().toString() : "0.0");
						batches.setReciptnumber(app.getReciptnumber());
						batches.setPaymentype(app.getPaymentype());
						batches.setLoannumber(app.getLoannumber());
						batches.setCustomername(app.getCustomername());
						batches.setEmiamount(app.getEmiamount() != null ? app.getEmiamount().toString() : "0.0");
						batches.setCollectedamount(app.getCollectedAmount() != null ? app.getCollectedAmount().toString() : "0.0");

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
