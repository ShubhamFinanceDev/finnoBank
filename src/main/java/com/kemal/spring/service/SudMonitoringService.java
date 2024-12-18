package com.kemal.spring.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemal.spring.domain.SudApplication;
import com.kemal.spring.domain.SudApplicationRepository;
import com.kemal.spring.domain.SudDiStatus;
import com.kemal.spring.domain.SudDiStatusRepository;
import com.kemal.spring.domain.SudDocketStatus;
import com.kemal.spring.domain.SudDocketStatusRepository;
import com.kemal.spring.domain.SudDpaStatus;
import com.kemal.spring.domain.SudFinalStatus;
import com.kemal.spring.domain.SudFinalStatusRepository;
import com.kemal.spring.domain.SudLegalStatus;
import com.kemal.spring.domain.SudLegalStatusRepository;
import com.kemal.spring.domain.SudMonitoring;
import com.kemal.spring.domain.SudMonitoringRepository;
import com.kemal.spring.domain.SudTechnicalStatus;
import com.kemal.spring.domain.SudTechnicalStatusRepository;
import com.kemal.spring.domain.SudpaStatusRepository;
import com.kemal.spring.domain.administrative.AdminBranchMaster;
import com.kemal.spring.web.dto.SudMonitoringDto;
import com.kemal.spring.web.dto.batchpagingDto;

@Service
public class SudMonitoringService {

	private SudMonitoringRepository sudMonitoringRepository;
	private SudDiStatusRepository sudDiStatusRepository;
	private SudDocketStatusRepository sudDocketStatusRepository;
	private SudpaStatusRepository sudpaStatusRepository;
	private SudFinalStatusRepository sudFinalStatusRepository;
	private SudLegalStatusRepository sudLegalStatusRepository;
	private SudTechnicalStatusRepository sudTechnicalStatusRepository;
	private SudApplicationRepository sudApplicationRepository;

	String[] colors = new String[] { /*
										 * "#6495ED", "#FFFACD", "#ADD8E6", "#90EE90", "#FFA07A", "#20B2AA", "#9370DB",
										 * "#7B68EE", "#FFC0CB", "#4682B4", "#40E0D0"
										 */
			"#FF7F50", "#FF6347", "#FFA500", "#008000", "#3CB371", "#7B68EE", "#8A2BE2", "#FF1493", "#F4A460",
			"#DEB887", "#FF69B4" };

	public SudMonitoringService(SudMonitoringRepository sudMonitoringRepository,
			SudDiStatusRepository sudDiStatusRepository, SudDocketStatusRepository sudDocketStatusRepository,
			SudpaStatusRepository sudpaStatusRepository, SudFinalStatusRepository sudFinalStatusRepository,
			SudLegalStatusRepository sudLegalStatusRepository,
			SudTechnicalStatusRepository sudTechnicalStatusRepository,
			SudApplicationRepository sudApplicationRepository) {
		this.sudMonitoringRepository = sudMonitoringRepository;
		this.sudDiStatusRepository = sudDiStatusRepository;
		this.sudDocketStatusRepository = sudDocketStatusRepository;
		this.sudpaStatusRepository = sudpaStatusRepository;
		this.sudFinalStatusRepository = sudFinalStatusRepository;
		this.sudLegalStatusRepository = sudLegalStatusRepository;
		this.sudTechnicalStatusRepository = sudTechnicalStatusRepository;
		this.sudApplicationRepository = sudApplicationRepository;

	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public List<SudMonitoringDto> findSudMonitoringList(String branchname) {
		// TODO Auto-generated method stub
		List<SudMonitoringDto> returnlist = new ArrayList<SudMonitoringDto>();
		List<SudMonitoring> list = new ArrayList<>();
		List<Object[]> objstr = null;
		List<SudApplication> applist = sudApplicationRepository.findAll();

		List<String> listapps = new ArrayList<String>();
		if (applist != null && applist.size() > 0) {
			for (SudApplication apps : applist) {
				listapps.add(apps.getApplicationnumber());
			}
		}

		if (branchname != null) {
			if (listapps != null && listapps.size() > 0) {
				objstr = sudMonitoringRepository.findAllByBranchNative(branchname, listapps);// sudMonitoringRepository.findSudMonitoringList();
			} else {
				objstr = sudMonitoringRepository.findAllByBranchNative(branchname);
			}
		} else {
			if (listapps != null && listapps.size() > 0) {
				objstr = sudMonitoringRepository.findSudMonitoringListNative(listapps);
			} else {
				objstr = sudMonitoringRepository.findSudMonitoringListNative();
			}
		}

		if (objstr != null) {
			for (Object[] obj : objstr) {

				String color = colors[randInt(0, colors.length - 1)];
				SudMonitoringDto dto = new SudMonitoringDto();
				dto.setRowColor(color);
				dto.setId(((BigDecimal) obj[0]).intValue());
				dto.setRegion((String) obj[1]);
				dto.setProduct((String) obj[2]);
				dto.setScheme((String) obj[3]);
				dto.setRoi(((BigDecimal) obj[4]).floatValue());
				dto.setTat((Double) obj[5]);
				dto.setApplicationnumber((String) obj[6]);
				dto.setCustomername((String) obj[7]);
				dto.setBranchname((String) obj[8]);
				dto.setRelationshipmanager((String) obj[9]);
				dto.setCurrentstatus((String) obj[10]);
				dto.setLogindate((Date) obj[11]);
				dto.setSanctiondate((Date) obj[12]);
				dto.setSanctionloanamount(((BigDecimal) obj[13]).floatValue());
				dto.setSanctionby((String) obj[14]);
				dto.setDisbursalamount((Double) obj[15]);

				if (obj[16] != null) {
					dto.setDistatus((int) obj[16]);
					dto.setDistatusname((String) obj[17]);
				}
				if (obj[18] != null) {
					dto.setDocketstatus((int) obj[18]);
					dto.setDocketstatusname((String) obj[19]);
				}
				if (obj[20] != null) {
					dto.setFinalstatus((int) obj[20]);
					dto.setFinalstatusname((String) obj[21]);
				}
				if (obj[22] != null) {
					dto.setLegalstatus((int) obj[22]);
					dto.setLegalstatusname((String) obj[23]);
				}
				if (obj[24] != null) {
					dto.setPastatus((int) obj[24]);
					dto.setPastatusname((String) obj[25]);
				}
				if (obj[26] != null) {
					dto.setTechnicalstatus((int) obj[26]);
					dto.setTechnicalstatusname((String) obj[27]);
				}
				dto.setUpdateRequired((String) obj[28]);
				if (obj[29] != null) {
					dto.setDidateifdoable((String) obj[29]);
				} else {
					dto.setDidateifdoable("");
				}

				if (obj[30] != null) {
					dto.setAdditionalremarks((String) obj[30]);
				} else {
					dto.setAdditionalremarks("");
				}
				dto.setUpdateOn((Timestamp) obj[31]);

				returnlist.add(dto);
			}
		}

		/*
		 * if (list != null && list.size() > 0) { for (int i = 0; i < list.size(); i++)
		 * { String color = colors[randInt(0, colors.length - 1)]; SudMonitoringDto dto
		 * = new SudMonitoringDto(); dto.setId(list.get(i).getId());
		 * dto.setAdditionalremarks(list.get(i).getAdditionalremarks());
		 * dto.setApplicationnumber(list.get(i).getApplicationnumber());
		 * dto.setBranchname(list.get(i).getBranchname());
		 * 
		 * dto.setCustomername(list.get(i).getCustomername());
		 * 
		 * if (list.get(i).getDidateifdoable() != null) {
		 * dto.setDidateifdoable(list.get(i).getDidateifdoable()); } else {
		 * dto.setDidateifdoable(""); }
		 * 
		 * if (list.get(i).getAdditionalremarks() != null) {
		 * dto.setAdditionalremarks(list.get(i).getAdditionalremarks()); } else {
		 * dto.setAdditionalremarks(""); }
		 * 
		 * dto.setDisbursalamount(list.get(i).getDisbursalamount());
		 * dto.setScheme(list.get(i).getScheme());
		 * 
		 * if (list.get(i).getDistatus() != null) {
		 * dto.setDistatus(list.get(i).getDistatus().getId());
		 * dto.setDistatusname(list.get(i).getDistatus().getDistatusName()); }
		 * 
		 * dto.setDivalue(list.get(i).getDivalue());
		 * 
		 * if (list.get(i).getDocketstatus() != null) {
		 * dto.setDocketstatus(list.get(i).getDocketstatus().getId());
		 * dto.setDocketstatusname(list.get(i).getDocketstatus().getDocketstatusName());
		 * }
		 * 
		 * if (list.get(i).getFinalstatus() != null) {
		 * dto.setFinalstatus(list.get(i).getFinalstatus().getId());
		 * dto.setFinalstatusname(list.get(i).getFinalstatus().getFinalstatusName()); }
		 * 
		 * if (list.get(i).getLegalstatus() != null) {
		 * dto.setLegalstatus(list.get(i).getLegalstatus().getId());
		 * dto.setLegalstatusname(list.get(i).getLegalstatus().getLegalStatusName()); }
		 * 
		 * dto.setLogindate(list.get(i).getLogindate());
		 * 
		 * if (list.get(i).getPastatus() != null) {
		 * dto.setPastatus(list.get(i).getPastatus().getId());
		 * dto.setPastatusname(list.get(i).getPastatus().getPaStatusName()); }
		 * 
		 * dto.setProduct(list.get(i).getProduct());
		 * dto.setRegion(list.get(i).getRegion()); dto.setRowColor(color);
		 * dto.setSanctionby(list.get(i).getSanctionby());
		 * dto.setSanctionloanamount(list.get(i).getSanctionloanamount());
		 * dto.setSanctiondate(list.get(i).getSanctiondate());
		 * 
		 * if(list.get(i).getUpdatedon()!=null &&
		 * list.get(i).getUpdatedon().getDay()==(new Date()).getDay() &&
		 * list.get(i).getUpdatedon().getMonth()==(new Date()).getMonth() &&
		 * list.get(i).getUpdatedon().getYear()==(new Date()).getYear()) {
		 * dto.setUpdateRequired("no"); }else { dto.setUpdateRequired("yes"); }
		 * 
		 * if (list.get(i).getTechnicalstatus() != null) {
		 * dto.setTechnicalstatus(list.get(i).getTechnicalstatus().getId());
		 * dto.setTechnicalstatusname(list.get(i).getTechnicalstatus().
		 * getTechnicalStatusName());
		 * 
		 * }
		 * 
		 * dto.setRowColor(color); returnlist.add(dto);
		 * 
		 * } }
		 */
		return returnlist;
	}

	public List<SudDiStatus> findSudDiStatusList() {
		return sudDiStatusRepository.active(1); // findAll();
	}

	public List<SudDocketStatus> findSudDocketStatusList() {
		return sudDocketStatusRepository.active(1); // findAll();
	}

	public List<SudFinalStatus> findSudFinalStatusList() {
		return sudFinalStatusRepository.active(1); // findAll();
	}

	public List<SudLegalStatus> findSudLegalStatusList() {
		return sudLegalStatusRepository.active(1); // findAll();
	}

	public List<SudDpaStatus> findSudPaStatusList() {
		return sudpaStatusRepository.active(1); // findAll();
	}

	public List<SudTechnicalStatus> findSudTechnicalStatusList() {
		return sudTechnicalStatusRepository.active(1); // findAll();
	}

	public SudMonitoring findbySudMonitoringId(Integer sudid) {
		// TODO Auto-generated method stub
		return sudMonitoringRepository.findById(sudid).get();
	}

	public void saveMonitoring(SudMonitoring suddb) {
		// TODO Auto-generated method stub
		sudMonitoringRepository.save(suddb);
	}

	public void saveSudDiStatus(SudDiStatus sudmonitoring) {
		// TODO Auto-generated method stub
		sudDiStatusRepository.save(sudmonitoring);

	}

	public void saveSudlegalStatus(SudLegalStatus sudmonitoring) {
		// TODO Auto-generated method stub
		sudLegalStatusRepository.save(sudmonitoring);

	}

	public void saveSudtechnicalStatus(SudTechnicalStatus sudmonitoring) {
		// TODO Auto-generated method stub
		sudTechnicalStatusRepository.save(sudmonitoring);
	}

	public void saveSudpaStatus(SudDpaStatus sudmonitoring) {
		// TODO Auto-generated method stub
		sudpaStatusRepository.save(sudmonitoring);
	}

	public void saveSuddocketStatus(SudDocketStatus sudmonitoring) {
		// TODO Auto-generated method stub
		sudDocketStatusRepository.save(sudmonitoring);
	}

	public void savefinalStatus(SudFinalStatus sudmonitoring) {
		// TODO Auto-generated method stub
		sudFinalStatusRepository.save(sudmonitoring);
	}

	public static Object findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletedi(SudDiStatus sudDiStatus) {
		// TODO Auto-generated method stub
		sudDiStatusRepository.delete(sudDiStatus);
	}

	public void deletelegal(SudLegalStatus sudLegalStatus) {
		// TODO Auto-generated method stub
		sudLegalStatusRepository.delete(sudLegalStatus);
	}

	public void deletetechnical(SudTechnicalStatus sudTechnicalStatus) {
		// TODO Auto-generated method stub
		sudTechnicalStatusRepository.delete(sudTechnicalStatus);
	}

	public void deletepa(SudDpaStatus sudDpaStatus) {
		// TODO Auto-generated method stub
		sudpaStatusRepository.delete(sudDpaStatus);
	}

	public void deletedocket(SudDocketStatus sudDocketStatus) {
		// TODO Auto-generated method stub
		sudDocketStatusRepository.delete(sudDocketStatus);
	}

	public void deletedocket(SudFinalStatus sudFinalStatus) {
		// TODO Auto-generated method stub
		sudFinalStatusRepository.delete(sudFinalStatus);
	}

	public void saveApplication(SudApplication invoice) {
		// TODO Auto-generated method stub
		sudApplicationRepository.save(invoice);
	}

	public List<SudApplication> findAllApplication() {
		// TODO Auto-generated method stub
		return sudApplicationRepository.findAll();
	}

	public Page<SudMonitoring> findSudMonitoringListPaging(Pageable paging, String branch) {
		// TODO Auto-generated method stub
		List<SudApplication> applist = sudApplicationRepository.findAll();

		List<String> listapps = new ArrayList<String>();
		if (applist != null && applist.size() > 0) {
			for (SudApplication apps : applist) {
				listapps.add(apps.getApplicationnumber());
			}
		}
		Date fromDate = Date.from(LocalDateTime.now().minusDays(100).atZone(ZoneId.systemDefault()).toInstant());
	    Date toDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		if (branch != null) {
			if (applist != null && applist.size() > 0) {
				return sudMonitoringRepository.findAllByBranch(paging, branch, listapps,fromDate,toDate);
			} else {
				return sudMonitoringRepository.findAllByBranch(paging, branch,fromDate,toDate);
			}

		} else {
			if (applist != null && applist.size() > 0) {
				return sudMonitoringRepository.findAll(paging, listapps,fromDate,toDate);
			} else {
				return sudMonitoringRepository.findAll(paging,fromDate,toDate);
			}
		}

	}

	public Page<SudMonitoring> findSudMonitoringListByTitleContainingIgnoreCase(String keyword, Pageable paging,
			String branch) {
		// TODO Auto-generated method stub
		List<SudApplication> applist = sudApplicationRepository.findAll();

		List<String> listapps = new ArrayList<String>();
		if (applist != null && applist.size() > 0) {
			for (SudApplication apps : applist) {
				listapps.add(apps.getApplicationnumber());
			}
		}
		Date fromDate = Date.from(LocalDateTime.now().minusDays(100).atZone(ZoneId.systemDefault()).toInstant());
	    Date toDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		if (branch != null) {
			return sudMonitoringRepository.findAllColumns(keyword, paging, branch, listapps,fromDate,toDate);
		} else {
			return sudMonitoringRepository.findAllColumns(keyword, paging, listapps,fromDate,toDate);
		}

	}

	public List<SudApplication> findAllApplicant() {
		// TODO Auto-generated method stub
		return sudApplicationRepository.findAll();
	}

	public List<SudMonitoringDto> findSudMonitoringListByIds(List<SudMonitoring> content) {
		// TODO Auto-generated method stub

		List<SudMonitoringDto> returnlist = new ArrayList<SudMonitoringDto>();
		List<SudMonitoring> list = new ArrayList<>();
		List<Object[]> objstr = null;
		List<SudApplication> applist = sudApplicationRepository.findAll();

		List<Integer> listapps = new ArrayList<Integer>();
		if (content != null && content.size() > 0) {
			for (SudMonitoring apps : content) {
				listapps.add(apps.getId());
			}
		}

		objstr = sudMonitoringRepository.findSudMonitoringListNativeByIds(listapps);

		if (objstr != null) {
			for (Object[] obj : objstr) {

				String color = colors[randInt(0, colors.length - 1)];
				SudMonitoringDto dto = new SudMonitoringDto();
				dto.setRowColor(color);
				dto.setId(((BigDecimal) obj[0]).intValue());
				dto.setRegion((String) obj[1]);
				dto.setProduct((String) obj[2]);
				dto.setScheme((String) obj[3]);
				dto.setRoi(((BigDecimal) obj[4]).floatValue());
				dto.setTat((Double) obj[5]);
				dto.setApplicationnumber((String) obj[6]);
				dto.setCustomername((String) obj[7]);
				dto.setBranchname((String) obj[8]);
				dto.setRelationshipmanager((String) obj[9]);
				dto.setCurrentstatus((String) obj[10]);
				dto.setLogindate((Date) obj[11]);
				dto.setSanctiondate((Date) obj[12]);
				dto.setSanctionloanamount(((BigDecimal) obj[13]).floatValue());
				dto.setSanctionby((String) obj[14]);
				dto.setDisbursalamount((Double) obj[15]);

				if (obj[16] != null) {
					dto.setDistatus((int) obj[16]);
					dto.setDistatusname((String) obj[17]);
				}
				if (obj[18] != null) {
					dto.setDocketstatus((int) obj[18]);
					dto.setDocketstatusname((String) obj[19]);
				}
				if (obj[20] != null) {
					dto.setFinalstatus((int) obj[20]);
					dto.setFinalstatusname((String) obj[21]);
				}
				if (obj[22] != null) {
					dto.setLegalstatus((int) obj[22]);
					dto.setLegalstatusname((String) obj[23]);
				}
				if (obj[24] != null) {
					dto.setPastatus((int) obj[24]);
					dto.setPastatusname((String) obj[25]);
				}
				if (obj[26] != null) {
					dto.setTechnicalstatus((int) obj[26]);
					dto.setTechnicalstatusname((String) obj[27]);
				}
				dto.setUpdateRequired((String) obj[28]);
				if (obj[29] != null) {
					dto.setDidateifdoable((String) obj[29]);
				} else {
					dto.setDidateifdoable("");
				}

				if (obj[30] != null) {
					dto.setAdditionalremarks((String) obj[30]);
				} else {
					dto.setAdditionalremarks("");
				}
				dto.setUpdateOn((Timestamp) obj[31]);

				returnlist.add(dto);
			}
		}

		return returnlist;
	}

	

}
