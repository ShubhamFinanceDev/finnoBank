package com.kemal.spring.web.controllers.viewControllers.administrativeController;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.User;
import com.kemal.spring.domain.administrative.Agreement;
import com.kemal.spring.domain.administrative.PettyCash;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.service.administrativeDetails.AdministrativeDetailsService;

@Controller
@RequestMapping("")

public class AdministratorPettyCashController {
	private final AdministrativeDetailsService administrativeDetailsService;
	private final UserService userService;
	private final ActivityDetailsService activityService;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate osourceTemplate;

	public AdministratorPettyCashController(AdministrativeDetailsService administrativeDetailsService,
			UserService userService,ActivityDetailsService activityService) {
		this.administrativeDetailsService = administrativeDetailsService;
		this.userService = userService;
		this.activityService=activityService;
	}
	
	
	// Get method for creating batch
	@GetMapping("/pettycash")
	public ModelAndView  pettycash(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("administration/admin/pettycash");
		
		
		modelAndView.addObject("pettyCash", new PettyCash());
		modelAndView.addObject("pettydetails", administrativeDetailsService.findAllPettycash());
          modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
		
		modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
		modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
		modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
		
		request.getSession().setAttribute("pettydetails", administrativeDetailsService.findAllPettycash());

		
		return modelAndView;
	}
	
	
	
	
	@PostMapping("/savepettycash")
	public String savepettycash(ModelAndView modelAndView, @ModelAttribute("pettyCash") PettyCash pettyCash,
			BindingResult bindingResult, HttpServletRequest request, Errors errors, RedirectAttributes redir) {

		modelAndView = new ModelAndView("administration/admin/pettycash");
		// Authentication auth = (Authentication)
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		User user = userService.findByUsername(name);
		pettyCash.setCreatedby(user);
		pettyCash.setCreatedon(new Date());
		administrativeDetailsService.savepettycash(pettyCash);
		Activity activity = new Activity();
		activity.setType("Petty Cash");
		activity.setUser(user);
		
		activity.setCreateon(new Date());
		activity.setDescription("Petty Cash data saved Successfully");
		activityService.save(activity);
		
		pettyCash = new PettyCash();
		modelAndView.addObject("pettyCash", pettyCash);
		redir.addFlashAttribute("successMessage", "Save Successfully");
		return "redirect:/pettycash";
	}
	
	// delete pettycash

			@RequestMapping(value = "/deletepettycash", method = RequestMethod.POST)
			public String deletepettycash(PettyCash pettyCash, HttpServletRequest request) {

				request.getSession().setAttribute("listtrial", null);
				ModelAndView modelAndView = new ModelAndView();

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				User user = userService.findByUsername(auth.getName());
				administrativeDetailsService.deletepettycash(pettyCash);
				
				Activity activity = new Activity();
				activity.setType("Pettycash");
				activity.setUser(user);
				activity.setCreateon(new Date());
				activity.setDescription("Petty cash data deleted  succefully");
				activityService.save(activity);

				modelAndView.addObject("pettyCash", new PettyCash());
				return "redirect:/pettycash";
			}
			
		
			// edit pettycash

			@RequestMapping(value = "/editpettycash", method = RequestMethod.POST)
			public ModelAndView editpettycash(PettyCash pettyCash, HttpServletRequest request) {

				request.getSession().setAttribute("listtrial", null);
				ModelAndView modelAndView = new ModelAndView("administration/admin/pettycash");

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				User user = userService.findByUsername(auth.getName());
				pettyCash.setCreatedby(user);
				pettyCash.setCreatedon(new Date());
				pettyCash=administrativeDetailsService.findPettyCashById(pettyCash.getId());
				modelAndView.addObject("pettyCash", pettyCash);
				Activity activity = new Activity();
				activity.setType("PettyCash");
				activity.setUser(user);
				activity.setCreateon(new Date());
				activity.setDescription("Petty cash edit  succefully");
				activityService.save(activity);
				modelAndView.addObject("pettydetails", administrativeDetailsService.findAllPettycash());
				 modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
					
					modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
					modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
					modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
				
				request.getSession().setAttribute("pettydetails", administrativeDetailsService.findAllPettycash());
				return modelAndView;
			}

	

}
