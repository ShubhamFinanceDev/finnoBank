package com.kemal.spring.web.controllers.viewControllers.adminControllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.kemal.spring.domain.SudDiStatus;
import com.kemal.spring.domain.SudDocketStatus;
import com.kemal.spring.domain.SudDpaStatus;
import com.kemal.spring.domain.SudFinalStatus;
import com.kemal.spring.domain.SudLegalStatus;
import com.kemal.spring.domain.SudTechnicalStatus;
import com.kemal.spring.domain.User;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.SudMonitoringService;
import com.kemal.spring.service.UserService;

@Controller
public class MasterContoller {

	private final SudMonitoringService sudMonitoringService;
	private final ActivityDetailsService activityService;
	private final UserService userService;

	public MasterContoller(SudMonitoringService sudMonitoringService, ActivityDetailsService activityService,
			UserService userService) {

		this.sudMonitoringService = sudMonitoringService;
		this.activityService = activityService;
		this.userService = userService;

	}
	// Get method

	@GetMapping("/sudmaster")
	public ModelAndView master() {

		ModelAndView modelAndView = new ModelAndView("website/master");

		// master table list
		modelAndView.addObject("dilist", sudMonitoringService.findSudDiStatusList());
		modelAndView.addObject("docketlist", sudMonitoringService.findSudDocketStatusList());
		modelAndView.addObject("finallist", sudMonitoringService.findSudFinalStatusList());
		modelAndView.addObject("leagallist", sudMonitoringService.findSudLegalStatusList());
		modelAndView.addObject("palist", sudMonitoringService.findSudPaStatusList());
		modelAndView.addObject("technicallist", sudMonitoringService.findSudTechnicalStatusList());
		
		modelAndView.addObject("didto", new SudDiStatus());
		modelAndView.addObject("legalstatusdto", new SudLegalStatus());
		modelAndView.addObject("technicalstatusdto", new SudTechnicalStatus());
		modelAndView.addObject("pastatusdto", new SudDpaStatus());
		modelAndView.addObject("docketstatusdto", new SudDocketStatus());
		modelAndView.addObject("finalstatusdto", new SudFinalStatus());

		return modelAndView;

	}

	// Save Method for DI Status:

	@PostMapping("/savedistatus")
	public String geaneratedi(ModelAndView modelAndView, @ModelAttribute("didto") SudDiStatus sudmonitoring,
			BindingResult bindingResult, HttpServletRequest request,RedirectAttributes redir) {

		modelAndView = new ModelAndView("website/master");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		sudMonitoringService.saveSudDiStatus(sudmonitoring);

		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("DI Status save succefully");
		activityService.save(activity);

		
		redir.addFlashAttribute("successMessage", "Deleted Successfully");
		return "redirect:/sudmaster";
	}

	// delete di

	@RequestMapping(value = "/deletedi", method = RequestMethod.POST)
	public String deletedi(SudDiStatus sudDiStatus, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		sudMonitoringService.deletedi(sudDiStatus);
		
		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("DI Status save succefully");
		activityService.save(activity);

		modelAndView.addObject("sudDistatus", new SudDiStatus());
		return "redirect:/sudmaster";
	}

	// Save Method for legal Status:

	@PostMapping("/savelegalstatus")
	public String geaneratelegal(ModelAndView modelAndView,
			@ModelAttribute("legalstatusdto") SudLegalStatus sudmonitoring, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("website/master");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		sudMonitoringService.saveSudlegalStatus(sudmonitoring);

		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Legal Status save succefully");
		activityService.save(activity);

		return "redirect:/sudmaster";
	}

	// delete legal

	@RequestMapping(value = "/deletelegal", method = RequestMethod.POST)
	public String deletelegal(SudLegalStatus sudLegalStatus, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		sudMonitoringService.deletelegal(sudLegalStatus);
		
		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Legal Status save succefully");
		activityService.save(activity);

		modelAndView.addObject("sudlegalstatus", new SudLegalStatus());
		return "redirect:/sudmaster";
	}
	
	
	
	// Save Method for technical Status:

	@PostMapping("/savetechnicalstatus")
	public String geaneratetechnical(ModelAndView modelAndView,
			@ModelAttribute("technicalstatusdto") SudTechnicalStatus sudmonitoring, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("website/master");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		sudMonitoringService.saveSudtechnicalStatus(sudmonitoring);

		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Technical Status save succefully");
		activityService.save(activity);

		return "redirect:/sudmaster";
	}
	
	
	
	// delete technical

	@RequestMapping(value = "/deletetechnical", method = RequestMethod.POST)
	public String deletetechnical(SudTechnicalStatus SudTechnicalStatus, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		sudMonitoringService.deletetechnical(SudTechnicalStatus);
		
		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Technical Status save succefully");
		activityService.save(activity);

		modelAndView.addObject("sudtechnicalstatus", new SudTechnicalStatus());
		return "redirect:/sudmaster";
	}
	

	// Save Method for PA Status:

	@PostMapping("/savepastatus")
	public String geaneratepa(ModelAndView modelAndView, @ModelAttribute("pastatusdto") SudDpaStatus sudmonitoring,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("website/master");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		sudMonitoringService.saveSudpaStatus(sudmonitoring);

		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("PA Status save succefully");
		activityService.save(activity);

		return "redirect:/sudmaster";
	}
	
	
	// delete PA

	@RequestMapping(value = "/deletepa", method = RequestMethod.POST)
	public String deletepa(SudDpaStatus sudDpaStatus, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		sudMonitoringService.deletepa(sudDpaStatus);
		
		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("PA Status save succefully");
		activityService.save(activity);

		modelAndView.addObject("sudpastatus", new SudDpaStatus());
		return "redirect:/sudmaster";
	}
	
	

	// Save Method for Docket Status:

	@PostMapping("/savedocketstatus")
	public String geaneratedocket(ModelAndView modelAndView,
			@ModelAttribute("docketstatusdto") SudDocketStatus sudmonitoring, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("website/master");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		sudMonitoringService.saveSuddocketStatus(sudmonitoring);

		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Docket Status save succefully");
		activityService.save(activity);

		return "redirect:/sudmaster";
	}
	
	
	
	// delete docket

	@RequestMapping(value = "/deletedocket", method = RequestMethod.POST)
	public String deletedocket(SudDocketStatus sudDocketStatus, HttpServletRequest request) {

		request.getSession().setAttribute("listtrial", null);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userService.findByUsername(auth.getName());
		sudMonitoringService.deletedocket(sudDocketStatus);
		
		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Docket Status save succefully");
		activityService.save(activity);

		modelAndView.addObject("suddocketstatus", new SudDocketStatus());
		return "redirect:/sudmaster";
	}
	
	
	

	// Save Method for final Status:

	@PostMapping("/savefinalstatus")
	public String geaneratefinal(ModelAndView modelAndView,
			@ModelAttribute("finalstatusdto") SudFinalStatus sudmonitoring, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("website/master");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		sudMonitoringService.savefinalStatus(sudmonitoring);

		Activity activity = new Activity();
		activity.setType("Master");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Final Status save succefully");
		activityService.save(activity);

		return "redirect:/sudmaster";
	}
	
	// delete final

		@RequestMapping(value = "/deletefinal", method = RequestMethod.POST)
		public String deletefinal(SudFinalStatus sudFinalStatus, HttpServletRequest request) {

			request.getSession().setAttribute("listtrial", null);
			ModelAndView modelAndView = new ModelAndView();

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			User user = userService.findByUsername(auth.getName());
			sudMonitoringService.deletedocket(sudFinalStatus);
			
			Activity activity = new Activity();
			activity.setType("Master");
			activity.setUser(user);
			activity.setCreateon(new Date());
			activity.setDescription("Final Status save succefully");
			activityService.save(activity);

			modelAndView.addObject("sudfinalstatus", new SudDocketStatus());
			return "redirect:/sudmaster";
		}

}
