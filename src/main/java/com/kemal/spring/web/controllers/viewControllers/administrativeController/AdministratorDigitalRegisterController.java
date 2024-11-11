 package com.kemal.spring.web.controllers.viewControllers.administrativeController;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.User;
import com.kemal.spring.domain.administrative.DigitalRegister;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.service.administrativeDetails.AdministrativeDetailsService;

@Controller
@RequestMapping("")

public class AdministratorDigitalRegisterController {
	private final AdministrativeDetailsService administrativeDetailsService;
	private final UserService userService;
	private final ActivityDetailsService activityService;

	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate osourceTemplate;

	public AdministratorDigitalRegisterController(AdministrativeDetailsService administrativeDetailsService,
			UserService userService,ActivityDetailsService activityService) {
		this.administrativeDetailsService = administrativeDetailsService;
		this.userService = userService;
		this.activityService=activityService;
	}

	// Get method for creating batch
	@GetMapping("/outwaardregister")
	public ModelAndView outwaardregister(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("administration/admin/outwardregister");

		modelAndView.addObject("digitalregister", new DigitalRegister());
		modelAndView.addObject("digitalregisterdetails", administrativeDetailsService.findAllDigitalRegister());
		modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
		
		modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
		modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
		modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
		request.getSession().setAttribute("digitalregisterdetails", administrativeDetailsService.findAllDigitalRegister());
		return modelAndView;
	}
	
	
	
	@PostMapping("/saveoutwardregister")
	public String saveoutwardregister(ModelAndView modelAndView, @ModelAttribute("digitalregister") DigitalRegister digitalRegister,
			BindingResult bindingResult, HttpServletRequest request, Errors errors , RedirectAttributes redir) {

		modelAndView = new ModelAndView("administration/admin/outwardregister");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		User user = userService.findByUsername(name);
		
		digitalRegister.setCreatedby(user);
		digitalRegister.setCreatedon(new Date());
		digitalRegister.setActive(1);// 1 for Outward and 2 for inward
		administrativeDetailsService.savedigitalregister(digitalRegister);
		Activity activity = new Activity();
		activity.setType("Digitalregister");
		activity.setUser(user);
		
		activity.setCreateon(new Date());
		activity.setDescription("Outward  data saved Successfully");
		activityService.save(activity);
		
		digitalRegister = new DigitalRegister();
		modelAndView.addObject("digitalRegister", digitalRegister);
		redir.addFlashAttribute("successMessage", "Save Successfully");
		return "redirect:/outwaardregister";
	}
	
	
	// Get method for creating batch
		@GetMapping("/inwardregister")
		public ModelAndView inwardregister(HttpServletRequest request) {

			ModelAndView modelAndView = new ModelAndView("administration/admin/inwardregister");

			modelAndView.addObject("digitalregister", new DigitalRegister());
			modelAndView.addObject("digitalregisterdetails", administrativeDetailsService.findAllDigitalRegister());
			modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
			
			modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
			modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
			modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
			request.getSession().setAttribute("digitalregisterdetails", administrativeDetailsService.findAllDigitalRegister());
			return modelAndView;
		}
		
		
		@PostMapping("/saveinwardregister")
		public String saveinwardregister(ModelAndView modelAndView, @ModelAttribute("digitalregister") DigitalRegister digitalRegister,
				BindingResult bindingResult, HttpServletRequest request, Errors errors, RedirectAttributes redir) {

			modelAndView = new ModelAndView("administration/admin/inwardregister");
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName(); // get logged in username

			User user = userService.findByUsername(name);
			DigitalRegister digitalRegisterdb = administrativeDetailsService.finddigitalbyDocketnumber(digitalRegister.getOutwarddocketnumber());
			
			digitalRegisterdb.setInwardreceivername(digitalRegister.getInwardreceivername());
			digitalRegisterdb.setInwardweight(digitalRegister.getInwardweight());
			digitalRegisterdb.setInwardcourierpartner(digitalRegister.getInwardcourierpartner());
			digitalRegisterdb.setInwardremark(digitalRegister.getInwardremark());
			digitalRegisterdb.setInwarddate(digitalRegister.getInwarddate());
			digitalRegisterdb.setActive(2);  // 1 for Outward and 2 for inward
			administrativeDetailsService.savedigitalregister(digitalRegisterdb);
			Activity activity = new Activity();
			activity.setType("Digitalregister");
			activity.setUser(user);
			
			activity.setCreateon(new Date());
			activity.setDescription("Inward  data saved Successfully");
			activityService.save(activity);
			
			digitalRegister = new DigitalRegister();
			modelAndView.addObject("digitalregister", digitalRegister);
			redir.addFlashAttribute("successMessage", "Save Successfully");
			return "redirect:/inwardregister";
		}

	
		//Fetching data on the basis of docket number
		@GetMapping("/fetchoutward/{docketnumber}")
		public ModelAndView fetchoutward(@PathVariable String docketnumber, ModelAndView modelAndView, Model model,
				HttpServletRequest request) {

			System.out.println(docketnumber);
			DigitalRegister digitalRegister = administrativeDetailsService.finddigitalbyDocketnumber(docketnumber);
		
         modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
			
			modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
			modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
			modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
			
			modelAndView.addObject("digitalregister", digitalRegister);
			modelAndView.setViewName("administration/admin/inwardregister");
			
			return modelAndView;
		}
		
		@PostMapping("/fetchoutward")
		public ModelAndView fetchoutwardPost(ModelAndView modelAndView, @ModelAttribute("digitalregister") DigitalRegister digitalRegister,
				BindingResult bindingResult, HttpServletRequest request, Errors errors) {

			System.out.println(digitalRegister.getOutwarddocketnumber());
			DigitalRegister digitalRegisterDB = administrativeDetailsService.finddigitalbyDocketnumber(digitalRegister.getOutwarddocketnumber());
			if(digitalRegisterDB!=null)
			{
				digitalRegister=digitalRegisterDB;
			}
			
          modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
			
			modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
			modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
			modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
			modelAndView.addObject("digitalregister", digitalRegister);
			modelAndView.setViewName("administration/admin/inwardregister");
			
			return modelAndView;
		}


}
