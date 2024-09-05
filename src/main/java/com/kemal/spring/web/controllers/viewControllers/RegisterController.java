package com.kemal.spring.web.controllers.viewControllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.SurveyAnswers;
import com.kemal.spring.domain.SurveyCustomer;
import com.kemal.spring.domain.SurveyCustomerDetails;
import com.kemal.spring.domain.User;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.CustomerSurveryService;
import com.kemal.spring.service.EmailService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.web.dto.SurveryDataSubmitDTO;
import com.kemal.spring.web.dto.UserDto;

/**
 * Created by Keno&Kemo on 17.11.2017..
 */
@Controller
@RequestMapping("")
public class RegisterController {
	
	private UserService userService;
	private EmailService emailService;
	private CustomerSurveryService customerSurveryService;
	private final ActivityDetailsService activityService;
	public RegisterController(UserService userService, EmailService emailService,
			CustomerSurveryService customerSurveryService,ActivityDetailsService activityService) {
		this.userService = userService;
		this.emailService = emailService;
		this.customerSurveryService = customerSurveryService;
		this.activityService=activityService;
	}

	@GetMapping("/submit-survey/{securitycode}")
	public ModelAndView deletefetchdata(@PathVariable String securitycode, ModelAndView modelAndView, Model model,
			HttpServletRequest request) {

		System.out.println(securitycode);
		SurveyCustomer customer = customerSurveryService.findCustomerBySecurity(securitycode);
		if (customer.getIsSurveyPost() == 0) {
			modelAndView.setViewName("website/customersurvey");
			SurveyCustomerDetails customerDetails = new SurveyCustomerDetails();
			customerDetails.setCustomerid(customer);
			SurveryDataSubmitDTO surveryDataSubmitDTO = new SurveryDataSubmitDTO();
			surveryDataSubmitDTO.setCustomerId(customer.getId());
			surveryDataSubmitDTO.setApplicantname(customer.getApplicantname());
			surveryDataSubmitDTO.setApplicationnumber(customer.getApplicationnumber());
			surveryDataSubmitDTO.setEmailid(customer.getEmailid());
			surveryDataSubmitDTO.setSecuritycode(customer.getSecuritycode());
			model.addAttribute("customerDetails", customerDetails);
			model.addAttribute("surveryDataSubmitDTO", surveryDataSubmitDTO);
			model.addAttribute("surveyQuestionslist", customerSurveryService.findSurveyQuestionList(customer.getCategory()));
		} else if (customer.getIsSurveyPost() == 1) {
			modelAndView.setViewName("website/customersurveyexpired");
		}
		return modelAndView;
	}

	@PostMapping(value = "/submit-survey")
	public ModelAndView submitsurvey(ModelAndView modelAndView,
			@ModelAttribute("surveryDataSubmitDTO") SurveryDataSubmitDTO surveryDataSubmitDTO,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {

		for (String s : surveryDataSubmitDTO.getOptions().split(",")) {
			SurveyCustomerDetails data = new SurveyCustomerDetails();
			SurveyCustomer customer = customerSurveryService.findCustomerById(surveryDataSubmitDTO.getCustomerId());
			data.setCustomerid(customer);
			SurveyAnswers ans = customerSurveryService.findAnswerById(Integer.parseInt(s.replace("option", "")));
			data.setAnswerid(ans);
			data.setQuestionid(ans.getQuestions());
			data.setCreateon(new Date());
			customerSurveryService.saveSurvey(data);

		}
		SurveyCustomer customer = customerSurveryService.findCustomerById(surveryDataSubmitDTO.getCustomerId());
		customer.setExpiredon(new Date());
		customer.setIsSurveyPost(1);
		customer.setFeedback(surveryDataSubmitDTO.getFeedback());
		customerSurveryService.save(customer);
		
		Activity activity = new Activity();
		activity.setType("SURVEY");
		// activity.setUser(users);
		activity.setCreateon(new Date());
		activity.setDescription("Access Home Page");
		activityService.save(activity);
		
		modelAndView.setViewName("website/customersurveyexpired");
		return modelAndView;
	}

	@PostMapping(value = "/submit-registration")
	public ModelAndView saveUser(ModelAndView modelAndView, @ModelAttribute("userDto") @Valid final UserDto userDto,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {

		User emailExists = userService.findByEmail(userDto.getEmail());
		User userNameExists = userService.findByUsername(userDto.getUsername());

		System.out.println(emailExists);

		if (emailExists != null) {
			modelAndView.setViewName("website/register");
			bindingResult.rejectValue("email", "emailAlreadyExists");
		}

		if (userNameExists != null) {
			modelAndView.setViewName("website/register");
			bindingResult.rejectValue("username", "usernameAlreadyExists");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("website/register");
		} else { // new user so we create user and send confirmation e-mail

			User user = userService.createNewAccount(userDto);
			// Disable user until they click on confirmation link in email

			user.setEnabled(true);
			userService.save(user);

			/*
			 * String appUrl = request.getScheme() + "://" + request.getServerName();
			 * 
			 * SimpleMailMessage registrationEmail = new SimpleMailMessage();
			 * registrationEmail.setTo(user.getEmail());
			 * registrationEmail.setSubject("Registration Confirmation");
			 * registrationEmail.setText("Please confirm the registration");
			 * registrationEmail.setFrom("email@email.com");
			 * 
			 * emailService.sendEmail(registrationEmail);
			 */

			modelAndView.addObject("confirmationMessage",
					"A confirmation e-mail has been sent to " + userDto.getEmail());
			modelAndView.setViewName("website/registered");
		}

		return modelAndView;
	}
}
