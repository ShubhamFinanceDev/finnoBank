package com.kemal.spring.web.controllers.viewControllers.administrativeController;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.User;
import com.kemal.spring.domain.administrative.Agreement;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.service.administrativeDetails.AdministrativeDetailsService;

@Controller
@RequestMapping("")

public class AdministratorAgreementController {

	private final UserService userService;
	private final ActivityDetailsService activityService;
	private final AdministrativeDetailsService administrativeDetailsService;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate osourceTemplate;

	public AdministratorAgreementController(AdministrativeDetailsService administrativeDetailsService,
			UserService userService, ActivityDetailsService activityService) {
		this.administrativeDetailsService = administrativeDetailsService;
		this.userService = userService;
		this.activityService = activityService;
	}

	// Get method for creating batch
	@GetMapping("/agreement")
	public ModelAndView agreement(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("administration/admin/agreementpage");

		modelAndView.addObject("agreement", new Agreement());
		modelAndView.addObject("agreementdetails", administrativeDetailsService.findAllAgreements());
		modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
		request.getSession().setAttribute("agreementdetails", administrativeDetailsService.findAllAgreements());
		return modelAndView;
	}

	@PostMapping("/saveagreement")
	public String saveagreement(ModelAndView modelAndView, @ModelAttribute("agreement") Agreement agreement,
			@RequestParam("file") MultipartFile file, BindingResult bindingResult, HttpServletRequest request,
			Errors errors,RedirectAttributes redir) throws IOException {

		modelAndView = new ModelAndView("administration/admin/agreementpage");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		User user = userService.findByUsername(name);
		if (file != null) {
			String encodedFile = Base64.getEncoder().encodeToString(file.getBytes());

			agreement.setFilename(file.getOriginalFilename());
			agreement.setAgreementpdf(encodedFile);
		}
		agreement.setCreatedby(user);
		agreement.setCreatedon(new Date());
		agreement.setLocation(agreement.getBranchcode());
		agreement.setBranchname(agreement.getBranchcode());
		agreement.setState(agreement.getBranchcode());

		administrativeDetailsService.saveagreement(agreement);

		Activity activity = new Activity();
		activity.setType("Agreement");
		activity.setUser(user);

		activity.setCreateon(new Date());
		activity.setDescription("Agrrement data saved Successfully");
		activityService.save(activity);

		agreement = new Agreement();
		modelAndView.addObject("agreement", agreement);
		redir.addFlashAttribute("successMessage", "Save Successfully");
		return "redirect:/agreement";
	}

	// delete agreement

	@RequestMapping(value = "/deleteagreement", method = RequestMethod.POST)
	public String deleteagreement(Agreement agreement, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		administrativeDetailsService.deleteagreement(agreement);

		Activity activity = new Activity();
		activity.setType("Agreement");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Agreement deleted  succefully");
		activityService.save(activity);

		modelAndView.addObject("agreement", new Agreement());
		return "redirect:/agreement";
	}

	// edit agreement

	@RequestMapping(value = "/editagreement", method = RequestMethod.POST)
	public ModelAndView editagreement(Agreement agreement, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView("administration/admin/agreementpage");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		agreement.setCreatedby(user);
		agreement.setCreatedon(new Date());
		agreement = administrativeDetailsService.findAgreementById(agreement.getId());
		modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
		Activity activity = new Activity();
		activity.setType("Agreement");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Agreement edit  succefully");
		activityService.save(activity);
		modelAndView.addObject("agreementdetails", administrativeDetailsService.findAllAgreements());
		modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());

		modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
		modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
		modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());

		request.getSession().setAttribute("agreementdetails", administrativeDetailsService.findAllAgreements());
		modelAndView.addObject("agreement", agreement);
		return modelAndView;
	}


	@GetMapping("/getagreement/{id}")
	public ResponseEntity<Object> getFile(@PathVariable String id) {

		try {
			Agreement agreement = administrativeDetailsService.findAgreementById(Integer.parseInt(id));
			byte[] fileDB = Base64.getDecoder().decode(agreement.getAgreementpdf());

			// Response type: byte[]
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + agreement.getFilename() + "\"").body(fileDB);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

}
