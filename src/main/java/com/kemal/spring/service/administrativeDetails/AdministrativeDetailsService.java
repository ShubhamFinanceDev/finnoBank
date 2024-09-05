package com.kemal.spring.service.administrativeDetails;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.ActivityRepository;
import com.kemal.spring.domain.administrative.AdminBranchMaster;
import com.kemal.spring.domain.administrative.AdminCourierMaster;
import com.kemal.spring.domain.administrative.AdminCourierRepository;
import com.kemal.spring.domain.administrative.AdminDepartmentMaster;
import com.kemal.spring.domain.administrative.AdminDepartmentRepository;
import com.kemal.spring.domain.administrative.AdminItemMaster;
import com.kemal.spring.domain.administrative.AdminItemRepository;
import com.kemal.spring.domain.administrative.AdminMasterRepository;
import com.kemal.spring.domain.administrative.Agreement;
import com.kemal.spring.domain.administrative.AgreementRepository;
import com.kemal.spring.domain.administrative.DigitalRegister;
import com.kemal.spring.domain.administrative.DigitalRegisterRepository;
import com.kemal.spring.domain.administrative.PettyCash;
import com.kemal.spring.domain.administrative.PettyCashRepository;

@Service
public class AdministrativeDetailsService {

	private ActivityRepository activityRepository;

	private PettyCashRepository pettyCashRepository;

	private AgreementRepository agreementRepository;

	private DigitalRegisterRepository digitalRegisterRepository;
	
	private AdminMasterRepository adminMasterRepository;
	
	private AdminDepartmentRepository adminDepartmentRepository;
	
	private AdminItemRepository adminItemRepository;
	
	private AdminCourierRepository adminCourierRepository;

	public AdministrativeDetailsService(ActivityRepository activityRepository, PettyCashRepository pettyCashRepository,
			AgreementRepository agreementRepository, DigitalRegisterRepository digitalRegisterRepository , AdminMasterRepository adminMasterRepository, AdminDepartmentRepository adminDepartmentRepository,
			AdminItemRepository adminItemRepository, AdminCourierRepository adminCourierRepository) {

		this.activityRepository = activityRepository;
		this.pettyCashRepository = pettyCashRepository;
		this.agreementRepository = agreementRepository;
		this.digitalRegisterRepository = digitalRegisterRepository;
		this.adminMasterRepository = adminMasterRepository;
		this.adminDepartmentRepository =adminDepartmentRepository;
		this.adminItemRepository=adminItemRepository;
		this.adminCourierRepository=adminCourierRepository;

	}

	public void save(Activity app) {

		activityRepository.save(app);
	}

	public void savepettycash(PettyCash pettycash) {
		// TODO Auto-generated method stub
		pettyCashRepository.save(pettycash);

	}

	public List<PettyCash> findAllPettycash() {
		// TODO Auto-generated method stub
		return pettyCashRepository.findAll();
	}

	public void saveagreement(Agreement agreement) {
		// TODO Auto-generated method stub
		agreementRepository.save(agreement);

	}

	public List<Agreement> findAllAgreements() {
		// TODO Auto-generated method stub
		return agreementRepository.findAll();
	}

	public void deleteagreement(Agreement agreement) {
		// TODO Auto-generated method stub
		agreementRepository.delete(agreement);
	}

	public List<DigitalRegister> findAllDigitalRegister() {
		// TODO Auto-generated method stub
		return digitalRegisterRepository.findAll();
	}

	public void savedigitalregister(DigitalRegister digitalRegister) {
		// TODO Auto-generated method stub
		digitalRegisterRepository.save(digitalRegister);

	}

	public DigitalRegister finddigitalbyDocketnumber(String docketnumber) {
		// TODO Auto-generated method stub
		return digitalRegisterRepository.finddigitalbyDocketnumber(docketnumber);
	}

	public DigitalRegister findDigitalRegisterById(Integer id) {
		// TODO Auto-generated method stub
		Long l = new Long(id);
		return digitalRegisterRepository.findById(id).get();
	}

	public void deletepettycash(PettyCash pettyCash) {
		// TODO Auto-generated method stub
		pettyCashRepository.delete(pettyCash);

	}

	public Agreement findAgreementById(Integer id) {
		// TODO Auto-generated method stub

		return agreementRepository.findById(id).get();
	}

	public PettyCash findPettyCashById(Integer id) {
		// TODO Auto-generated method stub
		return pettyCashRepository.findById(id).get();
	}

	
	
	

	public List<AdminBranchMaster> findAdminBranchList() {
		return adminMasterRepository.active(1);  //findAll();
	}

	
	
	
	public void savebranch(AdminBranchMaster adminBranchMaster) {
		// TODO Auto-generated method stub
		adminMasterRepository.save(adminBranchMaster);

	}
	public void deletebranch(AdminBranchMaster adminBranchMaster) {
		// TODO Auto-generated method stub
		adminMasterRepository.delete(adminBranchMaster);
	}
	
	
	
	public List<AdminDepartmentMaster> findAdminDepartmentList() {
		return adminDepartmentRepository.active(1);  //findAll();
	}

	
	
	
	public void savedepartment(AdminDepartmentMaster adminDepartmentMaster) {
		// TODO Auto-generated method stub
		adminDepartmentRepository.save(adminDepartmentMaster);

	}
	public void deletedepartment(AdminDepartmentMaster adminDepartmentMaster) {
		// TODO Auto-generated method stub
		adminDepartmentRepository.delete(adminDepartmentMaster);
	}
	
	
	public List<AdminItemMaster> findAdminItemList() {
		return adminItemRepository.active(1);  //findAll();
	}

	
	
	
	public void saveitem(AdminItemMaster adminItemMaster) {
		// TODO Auto-generated method stub
		adminItemRepository.save(adminItemMaster);

	}
	public void deleteitem(AdminItemMaster adminItemMaster) {
		// TODO Auto-generated method stub
		adminItemRepository.delete(adminItemMaster);
	}
	
	
	
	public List<AdminCourierMaster> findAdminCourierList() {
		return adminCourierRepository.active(1);  //findAll();
	}

	
	
	
	public void savecourier(AdminCourierMaster adminCourierMaster) {
		// TODO Auto-generated method stub
		adminCourierRepository.save(adminCourierMaster);

	}
	public void deletecourier(AdminCourierMaster adminCourierMaster) {
		// TODO Auto-generated method stub
		adminCourierRepository.delete(adminCourierMaster);
	}





}
