package com.kemal.spring.web.controllers.viewControllers.administrativeController;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.SudDiStatus;
import com.kemal.spring.domain.SudDocketStatus;
import com.kemal.spring.domain.SudDpaStatus;
import com.kemal.spring.domain.SudFinalStatus;
import com.kemal.spring.domain.SudLegalStatus;
import com.kemal.spring.domain.SudTechnicalStatus;
import com.kemal.spring.domain.User;
import com.kemal.spring.domain.administrative.AdminBranchMaster;
import com.kemal.spring.domain.administrative.AdminCourierMaster;
import com.kemal.spring.domain.administrative.AdminDepartmentMaster;
import com.kemal.spring.domain.administrative.AdminItemMaster;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.service.administrativeDetails.AdministrativeDetailsService;

@Controller
@RequestMapping("")

public class AdministratorMasterController {

	private final UserService userService;
	private final ActivityDetailsService activityService;
	private final AdministrativeDetailsService administrativeDetailsService;

	
	public AdministratorMasterController(AdministrativeDetailsService administrativeDetailsService,
			UserService userService, ActivityDetailsService activityService) {
		this.administrativeDetailsService = administrativeDetailsService;
		this.userService = userService;
		this.activityService = activityService;
	}

	
	
	// Get method

	@GetMapping("/adminmaster")
	public ModelAndView adminmaster() {

		ModelAndView modelAndView = new ModelAndView("administration/admin/administrativeMasters");

		// master table list
		modelAndView.addObject("branchlist", administrativeDetailsService.findAdminBranchList());
		modelAndView.addObject("departmentlist", administrativeDetailsService.findAdminDepartmentList());
		modelAndView.addObject("itemlist", administrativeDetailsService.findAdminItemList());
		modelAndView.addObject("courierlist", administrativeDetailsService.findAdminCourierList());
	
		
		modelAndView.addObject("adminbranchdto", new AdminBranchMaster());
		modelAndView.addObject("admindepatmentdto", new AdminDepartmentMaster());
		modelAndView.addObject("adminitemdto", new AdminItemMaster());
		modelAndView.addObject("admincourierdto", new AdminCourierMaster());
		
		return modelAndView;

	}


		// Save Method forBranch:

		@PostMapping("/savebranch")
		public String geaneratebranch(ModelAndView modelAndView, @ModelAttribute("adminbranchdto") AdminBranchMaster adminBranchMaster,
				BindingResult bindingResult, HttpServletRequest request,RedirectAttributes redir) {

			modelAndView = new ModelAndView("administration/admin/administrativeMasters");

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			User user = userService.findByUsername(name);
			administrativeDetailsService.savebranch(adminBranchMaster);

			Activity activity = new Activity();
			activity.setType("Master");
			activity.setUser(user);
			activity.setCreateon(new Date());
			activity.setDescription("Branch save succefully");
			activityService.save(activity);

			
			redir.addFlashAttribute("successMessage", "Save Successfully");
			return "redirect:/adminmaster";
		}

		
		 // delete branch
		 
		 @RequestMapping(value = "/deletebranch", method = RequestMethod.POST) public
		  String deletebranch(AdminBranchMaster adminBranchMaster, HttpServletRequest request) {
		  
		  request.getSession().setAttribute("listtrial", null); ModelAndView
		  modelAndView = new ModelAndView();
		  
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		  User user = userService.findByUsername(auth.getName());
		  administrativeDetailsService.deletebranch(adminBranchMaster);
		  
		  Activity activity = new Activity(); activity.setType("Master");
		  activity.setUser(user); activity.setCreateon(new Date());
		  activity.setDescription("Branch deleted succefully");
		  activityService.save(activity);
		  
		  modelAndView.addObject("adminBranchMaster", new AdminBranchMaster()); 
		  return "redirect:/adminmaster"; }
		
	
			// Save Method for department:

			@PostMapping("/savedepartment")
			public String geaneratedepartment(ModelAndView modelAndView, @ModelAttribute("admindepatmentdto") AdminDepartmentMaster adminDepartmentMaster,
					BindingResult bindingResult, HttpServletRequest request,RedirectAttributes redir) {

				modelAndView = new ModelAndView("administration/admin/administrativeMasters");

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String name = auth.getName();
				User user = userService.findByUsername(name);
				administrativeDetailsService.savedepartment(adminDepartmentMaster);

				Activity activity = new Activity();
				activity.setType("Master");
				activity.setUser(user);
				activity.setCreateon(new Date());
				activity.setDescription("Department save succefully");
				activityService.save(activity);

				
				redir.addFlashAttribute("successMessage", "Save Successfully");
				return "redirect:/adminmaster";
			}

			
			 // delete department
			 
			 @RequestMapping(value = "/deletedepartment", method = RequestMethod.POST) public
			  String deletedepartment(AdminDepartmentMaster adminDepartmentMaster, HttpServletRequest request) {
			  
			  request.getSession().setAttribute("listtrial", null); ModelAndView
			  modelAndView = new ModelAndView();
			  
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 
			  User user = userService.findByUsername(auth.getName());
			  administrativeDetailsService.deletedepartment(adminDepartmentMaster);
			  
			  Activity activity = new Activity(); activity.setType("Master");
			  activity.setUser(user); activity.setCreateon(new Date());
			  activity.setDescription("Department deleted succefully");
			  activityService.save(activity);
			  
			  modelAndView.addObject("adminDepartmentMaster", new AdminDepartmentMaster()); 
			  return "redirect:/adminmaster"; }
	
			 
			 
			 
			 
				// Save Method for item:

				@PostMapping("/saveitem")
				public String geanerateitem(ModelAndView modelAndView, @ModelAttribute("adminitemdto") AdminItemMaster adminItemMaster,
						BindingResult bindingResult, HttpServletRequest request,RedirectAttributes redir) {

					modelAndView = new ModelAndView("administration/admin/administrativeMasters");

					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					String name = auth.getName();
					User user = userService.findByUsername(name);
					administrativeDetailsService.saveitem(adminItemMaster);

					Activity activity = new Activity();
					activity.setType("Master");
					activity.setUser(user);
					activity.setCreateon(new Date());
					activity.setDescription("Item save succefully");
					activityService.save(activity);

					
					redir.addFlashAttribute("successMessage", "Save Successfully");
					return "redirect:/adminmaster";
				}

				
				 // delete item
				
				
				 
				 @RequestMapping(value = "/deleteitem", method = RequestMethod.POST) public
				  String deleteitem(AdminItemMaster adminItemMaster, HttpServletRequest request) {
				  
				  request.getSession().setAttribute("listtrial", null); ModelAndView
				  modelAndView = new ModelAndView();
				  
				  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				 
				  User user = userService.findByUsername(auth.getName());
				  administrativeDetailsService.deleteitem(adminItemMaster);
				  
				  Activity activity = new Activity(); activity.setType("Master");
				  activity.setUser(user); activity.setCreateon(new Date());
				  activity.setDescription("Item deleted succefully");
				  activityService.save(activity);
				  
				  modelAndView.addObject("adminItemMaster", new AdminItemMaster()); 
				  return "redirect:/adminmaster"; }
				 
				 
				 
				 
				 
				// Save Method for courier partner:

					@PostMapping("/savecourier")
					public String geaneratecourier(ModelAndView modelAndView, @ModelAttribute("admincourierdto") AdminCourierMaster adminCourierMaster,
							BindingResult bindingResult, HttpServletRequest request,RedirectAttributes redir) {

						modelAndView = new ModelAndView("administration/admin/administrativeMasters");

						Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						String name = auth.getName();
						User user = userService.findByUsername(name);
						administrativeDetailsService.savecourier(adminCourierMaster);

						Activity activity = new Activity();
						activity.setType("Master");
						activity.setUser(user);
						activity.setCreateon(new Date());
						activity.setDescription("Courier save succefully");
						activityService.save(activity);

						
						redir.addFlashAttribute("successMessage", "Save Successfully");
						return "redirect:/adminmaster";
					}

					
					 // delete courier
					 
					 @RequestMapping(value = "/deletecourier", method = RequestMethod.POST) 
					 public String deletecourier(AdminCourierMaster adminCourierMaster, HttpServletRequest request) {
					  
					  request.getSession().setAttribute("listtrial", null); ModelAndView
					  modelAndView = new ModelAndView();
					  
					  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					 
					  User user = userService.findByUsername(auth.getName());
					  administrativeDetailsService.deletecourier(adminCourierMaster);
					  
					  Activity activity = new Activity(); activity.setType("Master");
					  activity.setUser(user); activity.setCreateon(new Date());
					  activity.setDescription("Courier deleted succefully");
					  activityService.save(activity);
					  
					  modelAndView.addObject("adminCourierMaster", new AdminCourierMaster()); 
					  return "redirect:/adminmaster"; }
		
	

}
