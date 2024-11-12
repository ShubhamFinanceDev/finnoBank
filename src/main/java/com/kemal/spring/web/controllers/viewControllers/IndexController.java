package com.kemal.spring.web.controllers.viewControllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemal.spring.configuration.SendSMS;
import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.AirtelBatchDetails;
import com.kemal.spring.domain.BatchDetails;
import com.kemal.spring.domain.Role;
import com.kemal.spring.domain.SudApplication;
import com.kemal.spring.domain.SudDataUpload;
import com.kemal.spring.domain.SudDiStatus;
import com.kemal.spring.domain.SudDocketStatus;
import com.kemal.spring.domain.SudDpaStatus;
import com.kemal.spring.domain.SudFinalStatus;
import com.kemal.spring.domain.SudLegalStatus;
import com.kemal.spring.domain.SudMonitoring;
import com.kemal.spring.domain.SudTechnicalStatus;
import com.kemal.spring.domain.SurveyCustomer;
import com.kemal.spring.domain.SurveyCustomerDetails;
import com.kemal.spring.domain.User;
import com.kemal.spring.domain.administrative.DigitalRegister;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.AirtelBatchDetailsService;
import com.kemal.spring.service.BatchDetailsService;
import com.kemal.spring.service.CustomerSurveryService;
//import com.kemal.spring.service.CustomerSurveyService;
import com.kemal.spring.service.SudMonitoringService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.service.administrativeDetails.AdministrativeDetailsService;
import com.kemal.spring.web.dto.DashboardDTO;
import com.kemal.spring.web.dto.SudMonitoringDto;
import com.kemal.spring.web.dto.SurveryDataUpload;
import com.kemal.spring.web.dto.SurveyCustomerDto;
import com.kemal.spring.web.dto.UserDto;
import com.kemal.spring.web.dto.batchpagingDto;
import com.opencsv.CSVWriter;
import com.poiji.bind.Poiji;

/**
 * Created by Keno&Kemo on 30.09.2017..
 */

@Controller
@RequestMapping("")
public class IndexController {

	String[] colors = new String[] { /*
										 * "#6495ED", "#FFFACD", "#ADD8E6", "#90EE90", "#FFA07A", "#20B2AA", "#9370DB",
										 * "#7B68EE", "#FFC0CB", "#4682B4", "#40E0D0"
										 */
			"#FF7F50", "#FF6347", "#FFA500", "#008000", "#3CB371", "#7B68EE", "#8A2BE2", "#FF1493", "#F4A460",
			"#DEB887", "#FF69B4" };
	private String surverySMS = "Dear+Customer,+thank+you+for+being+our+valuable+customer.+Here,+we+are+conducting+a+customer+satisfaction+survey,+and+your+response+would+be+appreciated.+Please+click+the+link+to+fill+your+feedback+<link>+We+will+use+your+feedback+to+further+improve+our+service.%0aThank+you.%0aShubham+Housing";
	private String surveySMSHindi = "प्रिय+ग्राहक,+हमारे+मूल्यवान+ग्राहक+होने+के+लिए+धन्यवाद।+यहां,+हम+एक+ग्राहक+संतुष्टि+सर्वेक्षण+कर+रहे+हैं,+और+आपकी+प्रतिक्रिया+की+सराहना+की+जाएगी।+कृपया+अपनी+प्रतिक्रिया+भरने+के+लिए+लिंक+पर+क्लिक+करें+<link>+हम+आपकी+प्रतिक्रिया+का+उपयोग+हमारी+सेवा+को+और+बेहतर+बनाने+में+के+लिए+करेंगे।%0aधन्यवाद।%0aशुभम+हाउसिंग";

	private final BatchDetailsService batchService;
	private final AirtelBatchDetailsService airtelBatchService;
	private final UserService userService;
	private final SudMonitoringService sudMonitoringService;
	private final CustomerSurveryService customerSurveryService;
	private final ActivityDetailsService activityService;
	private final JavaMailSender mailSender;

	private final AdministrativeDetailsService administrativeDetailsService;
	private String surveyUrl = "https://houseportal.shubham.co:8949/submit-survey/";

	public IndexController(BatchDetailsService batchService, UserService userService,
			SudMonitoringService sudMonitoringService, CustomerSurveryService customerSurveryService,
			AdministrativeDetailsService administrativeDetailsService, ActivityDetailsService activityService,AirtelBatchDetailsService airtelBatchService,
			JavaMailSender mailSender) {

		this.batchService = batchService;
		this.userService = userService;
		this.sudMonitoringService = sudMonitoringService;
		this.customerSurveryService = customerSurveryService;
		this.activityService = activityService;
		this.administrativeDetailsService = administrativeDetailsService;
		this.mailSender = mailSender;
		this.airtelBatchService=airtelBatchService;
	}

	// @GetMapping(value = {"/", "/index"})
	@GetMapping(value = { "/", "/index" })
	public String index() {

		Activity activity = new Activity();
		activity.setType("HIT");
		// activity.setUser(users);
		activity.setCreateon(new Date());
		activity.setDescription("Access Home Page");
		activityService.save(activity);

		return "website/firstpage";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "website/login";
	}

	@GetMapping(value = "/sudlogin")
	public String sudlogin() {
		return "website/sudlogin";
	}

	@GetMapping(value = "/surveylogin")
	public String surveylogin() {
		return "website/surveylogin";
	}

	@GetMapping(value = { "administratorlogin" })
	public String administratorlogin() {
		return "website/administratorlogin";
	}

	@GetMapping(value = "/airteldashboard")
	public String airteldashboard(WebRequest request, Model model, @RequestParam(required = false) String keyword,
								  @RequestParam(required = false, defaultValue = "0") Long userId, // User ID parameter
								  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
								  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
								  @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		System.out.println(
				"User Selected role is selectedrole @@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@  "
						+ request.getParameter("selectedrole"));
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			model.addAttribute("lisrRoles", user.getRoles());
		} else {
			model.addAttribute("lisrRoles", null);
		}
		Activity activity = new Activity();
		activity.setType("LOGIN");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("User Login Successfully");
		activityService.save(activity);
		if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_ADMIN")
				|| user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_COLLECTION")) {
			List<batchpagingDto> list = new ArrayList<>();

			Pageable paging = PageRequest.of(page - 1, size);

			Page<AirtelBatchDetails> pageTuts;
			if (keyword == null) {

				pageTuts = airtelBatchService.findApplicationBybatchIdPaging(paging, userId, fromDate, toDate);
				list = airtelBatchService.findApplicationBybatchDto(pageTuts.getContent());

			} else {
				pageTuts = airtelBatchService.findApplicationBybatchidByTitleContainingIgnoreCase(keyword, paging, userId, fromDate, toDate);
				model.addAttribute("keyword", keyword);
				list = airtelBatchService.findApplicationBybatchDto(pageTuts.getContent());
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			// list = (batchService.findApplicationBybatchid(null));

			if (list != null && list.size() > 0) {

				int total = 0, pending = 0, closed = 0;

				// total=list.size();
				for (batchpagingDto batch : list) {

					if (batch.getBatchnumber() != null) {
						if (batch.getUserstatus().equalsIgnoreCase("created")) {

							pending++;
						} else {
							closed++;
						}
						total++;
					}

				}
				dashboardDTO.setClosed(closed);
				dashboardDTO.setPending(pending);
				dashboardDTO.setTotal(total);
			}
			model.addAttribute("dashboard", dashboardDTO);
			model.addAttribute("tutorials", list);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);

			return "website/airteldashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_USER")) {

			List<batchpagingDto> list = new ArrayList<>();

			Pageable paging = PageRequest.of(page - 1, size);

			Page<AirtelBatchDetails> pageTuts;
			if (keyword == null) {

				pageTuts = airtelBatchService.findApplicationBybatchIdPaging(paging, userId, fromDate, toDate);
				list = airtelBatchService.findApplicationBybatchDto(pageTuts.getContent());

			} else {
				pageTuts = airtelBatchService.findApplicationBybatchidByTitleContainingIgnoreCase(keyword, paging, userId, fromDate, toDate);
				model.addAttribute("keyword", keyword);
				list = airtelBatchService.findApplicationBybatchDto(pageTuts.getContent());
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			// list = (batchService.findApplicationBybatchid(null));

			if (list != null && list.size() > 0) {

				int total = 0, pending = 0, closed = 0;

				// total=list.size();
				for (batchpagingDto batch : list) {

					if (batch.getBatchnumber() != null) {
						if (batch.getUserstatus().equalsIgnoreCase("created")) {

							pending++;
						} else {
							closed++;
						}
						total++;
					}

				}
				dashboardDTO.setClosed(closed);
				dashboardDTO.setPending(pending);
				dashboardDTO.setTotal(total);
			}
			model.addAttribute("dashboard", dashboardDTO);
			model.addAttribute("tutorials", list);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);

			return "website/airteldashboard";
		}
		return "website/airteldashboard";
	}
	@GetMapping(value = "/dashboard")
	public String dashboard(WebRequest request, Model model,
							@RequestParam(required = false) String keyword,
							@RequestParam(required = false, defaultValue = "0") Long userId, // User ID parameter
							@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
							@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
							@RequestParam(defaultValue = "1") int page,
							@RequestParam(defaultValue = "10") int size) {
		System.out.println(userId + " in controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		System.out.println(
				"User Selected role is selectedrole @@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@  "
						+ request.getParameter("selectedrole"));
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			model.addAttribute("lisrRoles", user.getRoles());
		} else {
			model.addAttribute("lisrRoles", null);
		}
		Activity activity = new Activity();
		activity.setType("LOGIN");
		activity.setUser(user);
		activity.setCreateon(new Date());

		activity.setDescription("User Login Successfully");
		activityService.save(activity);
		if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_ADMIN")
				|| user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_COLLECTION")) {
			List<batchpagingDto> list = new ArrayList<>();

			Pageable paging = PageRequest.of(page - 1, size);

			Page<BatchDetails> pageTuts;

			if (keyword == null || keyword.isEmpty() && userId == 0 && fromDate != null && toDate != null) {
				pageTuts = batchService.findApplicationBybatchIdPaging(paging, userId, fromDate,toDate);
				list = batchService.findApplicationBybatchDto(pageTuts.getContent());

			} else {
//				pageTuts = batchService.findApplicationBybatchidByTitleContainingIgnoreCase(keyword, paging, 0);   //add search filter for employee code
				pageTuts = batchService.findApplicationByEmployeeId(keyword, paging);
				model.addAttribute("keyword", keyword);
				list = batchService.findApplicationBybatchDto(pageTuts.getContent());
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			// list = (batchService.findApplicationBybatchid(null));

			if (list != null && list.size() > 0) {

				int total = 0, pending = 0, closed = 0;

				// total=list.size();
				for (batchpagingDto batch : list) {

					if (batch.getBatchnumber() != null) {
						if (batch.getUserstatus().equalsIgnoreCase("created")) {

							pending++;
						} else {
							closed++;
						}
						total++;
					}

				}
				dashboardDTO.setClosed(closed);
				dashboardDTO.setPending(pending);
				dashboardDTO.setTotal(total);
			}
			model.addAttribute("dashboard", dashboardDTO);
			model.addAttribute("tutorials", list);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);

			return "website/dashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_USER")) {

			List<batchpagingDto> list = new ArrayList<>();

			Pageable paging = PageRequest.of(page - 1, size);

			Page<BatchDetails> pageTuts;

			if (keyword == null || keyword.isEmpty() && userId != 0 || fromDate != null && toDate != null)  {
				pageTuts = batchService.findApplicationBybatchIdPaging(paging, userId, fromDate, toDate);
				list = batchService.findApplicationBybatchDto(pageTuts.getContent());

			} else {
//				pageTuts = batchService.findApplicationBybatchidByTitleContainingIgnoreCase(keyword, paging,   //add search filter for employee code
//						user.getId());
				pageTuts = batchService.findApplicationByEmployeeId(keyword, paging);
				model.addAttribute("keyword", keyword);
				list = batchService.findApplicationBybatchDto(pageTuts.getContent());
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			// list = (batchService.findApplicationBybatchid(null));

			if (list != null && list.size() > 0) {

				int total = 0, pending = 0, closed = 0;

				// total=list.size();
				for (batchpagingDto batch : list) {

					if (batch.getBatchnumber() != null) {
						if (batch.getUserstatus().equalsIgnoreCase("created")) {

							pending++;
						} else {
							closed++;
						}
						total++;
					}

				}
				dashboardDTO.setClosed(closed);
				dashboardDTO.setPending(pending);
				dashboardDTO.setTotal(total);
			}
			model.addAttribute("dashboard", dashboardDTO);
			model.addAttribute("tutorials", list);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);

			return "website/dashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SUD_USER")) {
			model.addAttribute("sudMonitoringDto", new SudMonitoringDto());

			List<SudMonitoringDto> tutorials = new ArrayList<SudMonitoringDto>();
			Pageable paging = PageRequest.of(page - 1, size);

			Page<SudMonitoring> pageTuts;
			if (keyword == null || keyword.isEmpty()) {

				pageTuts = sudMonitoringService.findSudMonitoringListPaging(paging, user.getEmpbranch());
				tutorials = sudMonitoringService.findSudMonitoringListByIds(pageTuts.getContent());

			} else {
				pageTuts = sudMonitoringService.findSudMonitoringListByTitleContainingIgnoreCase(keyword, paging,
						user.getEmpbranch());
				model.addAttribute("keyword", keyword);
				tutorials = sudMonitoringService.findSudMonitoringListByIds(pageTuts.getContent());
			}

			model.addAttribute("tutorials", tutorials);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);

			// model.addAttribute("batchdetails",
			// sudMonitoringService.findSudMonitoringList(user.getEmpbranch()));
			// master data population

			model.addAttribute("dilist", sudMonitoringService.findSudDiStatusList());
			model.addAttribute("docketlist", sudMonitoringService.findSudDocketStatusList());
			model.addAttribute("finallist", sudMonitoringService.findSudFinalStatusList());
			model.addAttribute("leagallist", sudMonitoringService.findSudLegalStatusList());
			model.addAttribute("palist", sudMonitoringService.findSudPaStatusList());
			model.addAttribute("technicallist", sudMonitoringService.findSudTechnicalStatusList());

			return "website/suddashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SUD_ADMIN")) {
			model.addAttribute("sudMonitoringDto", new SudMonitoringDto());

			List<SudMonitoringDto> tutorials = new ArrayList<SudMonitoringDto>();
			Pageable paging = PageRequest.of(page - 1, size);

			Page<SudMonitoring> pageTuts;
			if (keyword == null) {

				pageTuts = sudMonitoringService.findSudMonitoringListPaging(paging, null);
				tutorials = sudMonitoringService.findSudMonitoringListByIds(pageTuts.getContent());

			} else {
				pageTuts = sudMonitoringService.findSudMonitoringListByTitleContainingIgnoreCase(keyword, paging, null);
				model.addAttribute("keyword", keyword);
				tutorials = sudMonitoringService.findSudMonitoringListByIds(pageTuts.getContent());
			}

			model.addAttribute("tutorials", tutorials);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);
			// model.addAttribute("batchdetails",
			// sudMonitoringService.findSudMonitoringList(null));

			return "website/suddashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SURVEY_ADMIN")) {
			System.out.println("ROLE_SURVEY_ADMIN calling@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			try {
				List<SurveyCustomerDto> tutorials = new ArrayList<SurveyCustomerDto>();
				Pageable paging = PageRequest.of(page - 1, size);

				Page<SurveyCustomer> pageTuts;
				if (keyword == null) {
					pageTuts = customerSurveryService.findSurveyCustomerList(paging);
				} else {
					pageTuts = customerSurveryService.findSurveyCustomerListByTitleContainingIgnoreCase(keyword,
							paging);
					model.addAttribute("keyword", keyword);
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				for (SurveyCustomer customer : pageTuts.getContent()) {
					String color = colors[randInt(0, colors.length - 1)];
					SurveyCustomerDto dto = new SurveyCustomerDto();
					dto.setApplicationnumber(customer.getApplicationnumber());
					dto.setApplicantname(customer.getApplicantname());
					dto.setEmailid(customer.getEmailid());
					dto.setMobileno(customer.getMobileno());
					dto.setRowColor(color);
					dto.setFeedback(customer.getFeedback());
					dto.setFeedbackpostdate(sdf.format(customer.getCreateon()));
					List<SurveyCustomerDetails> listr = customerSurveryService
							.findCustomerSurveryDetails(customer.getId());// sudMonitoringRepository.findSudMonitoringList();
					for (int i = 0; i < listr.size(); i++) {

						dto.setFeedbacksubmitdate(sdf.format(listr.get(0).getCreateon()));

						if (i == 0) {
							dto.setQuestion1(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers1(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 1) {
							dto.setQuestion2(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers2(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 2) {
							dto.setQuestion3(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers3(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 3) {
							dto.setQuestion4(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers4(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 4) {
							dto.setQuestion5(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers5(listr.get(i).getAnswerid().getAnswers());
						}

					}
					tutorials.add(dto);

				}

				model.addAttribute("tutorials", tutorials);
				model.addAttribute("currentPage", pageTuts.getNumber() + 1);
				model.addAttribute("totalItems", pageTuts.getTotalElements());
				model.addAttribute("totalPages", pageTuts.getTotalPages());
				model.addAttribute("pageSize", size);
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
			}

			// model.addAttribute("batchdetails",
			// customerSurveryService.findSurveyCustomerList());

			return "website/surveydashboard";

		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SURVEY_USER")) {

			try {
				List<SurveyCustomerDto> tutorials = new ArrayList<SurveyCustomerDto>();
				Pageable paging = PageRequest.of(page - 1, size);

				Page<SurveyCustomer> pageTuts;
				if (keyword == null || keyword.isEmpty()) {
					pageTuts = customerSurveryService.findSurveyCustomerList(paging);
				} else {
					pageTuts = customerSurveryService.findSurveyCustomerListByTitleContainingIgnoreCase(keyword,
							paging);
					model.addAttribute("keyword", keyword);
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				for (SurveyCustomer customer : pageTuts.getContent()) {
					String color = colors[randInt(0, colors.length - 1)];
					SurveyCustomerDto dto = new SurveyCustomerDto();
					dto.setApplicationnumber(customer.getApplicationnumber());
					dto.setApplicantname(customer.getApplicantname());
					dto.setEmailid(customer.getEmailid());
					dto.setMobileno(customer.getMobileno());
					dto.setRowColor(color);
					dto.setFeedback(customer.getFeedback());
					dto.setFeedbackpostdate(sdf.format(customer.getCreateon()));
					List<SurveyCustomerDetails> listr = customerSurveryService
							.findCustomerSurveryDetails(customer.getId());// sudMonitoringRepository.findSudMonitoringList();
					for (int i = 0; i < listr.size(); i++) {

						dto.setFeedbacksubmitdate(sdf.format(listr.get(0).getCreateon()));

						if (i == 0) {
							dto.setQuestion1(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers1(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 1) {
							dto.setQuestion2(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers2(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 2) {
							dto.setQuestion3(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers3(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 3) {
							dto.setQuestion4(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers4(listr.get(i).getAnswerid().getAnswers());
						}
						if (i == 4) {
							dto.setQuestion5(listr.get(i).getQuestionid().getQuestion());
							dto.setAnswers5(listr.get(i).getAnswerid().getAnswers());
						}

					}
					tutorials.add(dto);

				}

				model.addAttribute("tutorials", tutorials);
				model.addAttribute("currentPage", pageTuts.getNumber() + 1);
				model.addAttribute("totalItems", pageTuts.getTotalElements());
				model.addAttribute("totalPages", pageTuts.getTotalPages());
				model.addAttribute("pageSize", size);
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
			}
			// model.addAttribute("batchdetails",
			// customerSurveryService.findSurveyCustomerList());
			return "website/surveydashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_ADMINISTRATOR_ADMIN")) {

			model.addAttribute("agreementlist", administrativeDetailsService.findAllAgreements());
			model.addAttribute("branchlist", administrativeDetailsService.findAdminBranchList());
			model.addAttribute("departmentlist", administrativeDetailsService.findAdminDepartmentList());
			model.addAttribute("itemlist", administrativeDetailsService.findAdminItemList());
			model.addAttribute("courierlist", administrativeDetailsService.findAdminCourierList());

			int sent = 0;
			int pending = 0;
			int received = 0;
			int agreement = administrativeDetailsService.findAllAgreements().size();
			int pettycash = administrativeDetailsService.findAllPettycash().size();

			model.addAttribute("digitalregisterlist", administrativeDetailsService.findAllDigitalRegister());

			for (DigitalRegister ds : administrativeDetailsService.findAllDigitalRegister()) {
				sent = sent + 1;
				if (ds.getActive() == 1) {
					pending = pending + 1;
				} else if (ds.getActive() == 2) {
					received = received + 1;
				}
			}

			model.addAttribute("pettycashlist", administrativeDetailsService.findAllPettycash());

			model.addAttribute("courierSent", sent);
			model.addAttribute("courierPending", pending);
			model.addAttribute("courierReceived", received);
			model.addAttribute("agreementCount", agreement);
			model.addAttribute("pettycashCount", pettycash);
			return "website/administrationdashboard";

		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_ADMINISTRATOR_USER")) {
			model.addAttribute("digitalregister", new DigitalRegister());
			model.addAttribute("digitalregisterdetails", administrativeDetailsService.findAllDigitalRegister());
			model.addAttribute("branchlist", administrativeDetailsService.findAdminBranchList());
			model.addAttribute("departmentlist", administrativeDetailsService.findAdminDepartmentList());

			model.addAttribute("digitalregisterlist", administrativeDetailsService.findAllDigitalRegister());
			for (DigitalRegister ds : administrativeDetailsService.findAllDigitalRegister())
				/*
				 * { sent=sent+1; if(ds.getActive()==1) { pending=pending+1; }else
				 * if(ds.getActive()==2){ received=received+1; } }
				 */

				// return "administration/admin/inwardregister";
				return "website/administrationdashboard";
		}

		return "website/administrationdashboard";
	}

	@PostMapping(value = "/dashboard")
	public String dashboardPost(WebRequest request, Model model, HttpServletRequest request2) {

		// model.addAttribute("batchdetails", batchService.findApplicationBybatchid());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		System.out.println(
				"User Selected role is selectedrole @@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@  "
						+ request2.getParameter("selectedrole"));
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			model.addAttribute("lisrRoles", user.getRoles());
		} else {
			model.addAttribute("lisrRoles", null);
		}
		Activity activity = new Activity();
		activity.setType("LOGIN");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("User Login Successfully");
		activityService.save(activity);
		if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_ADMIN")
				|| user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_COLLECTION")) {

			List<batchpagingDto> list = new ArrayList<>();

			list = (batchService.findApplicationBybatchid(null, null, null ));
			DashboardDTO dashboardDTO = new DashboardDTO();

			if (list != null && list.size() > 0) {

				int total = 0, pending = 0, closed = 0;

				// total=list.size();
				for (batchpagingDto batch : list) {

					if (batch.getBatchnumber() != null) {
						if (batch.getUserstatus().equalsIgnoreCase("created")) {

							pending++;
						} else {
							closed++;
						}
						total++;
					}

				}
				dashboardDTO.setClosed(closed);
				dashboardDTO.setPending(pending);
				dashboardDTO.setTotal(total);
			}
			model.addAttribute("dashboard", dashboardDTO);
			model.addAttribute("batchdetails", list);
			return "website/dashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_USER")) {
			List<batchpagingDto> list = new ArrayList<>();
			list = (batchService.findApplicationBybatchid(user.getId(), null, null));
			DashboardDTO dashboardDTO = new DashboardDTO();

			if (list != null && list.size() > 0) {

				int total = 0, pending = 0, closed = 0;

				// total=list.size();
				for (batchpagingDto batch : list) {

					if (batch.getBatchnumber() != null) {
						if (batch.getUserstatus().equalsIgnoreCase("created")) {

							pending++;
						} else {
							closed++;
						}
						total++;
					}

				}
				dashboardDTO.setClosed(closed);
				dashboardDTO.setPending(pending);
				dashboardDTO.setTotal(total);
			}
			model.addAttribute("dashboard", dashboardDTO);
			model.addAttribute("batchdetails", list);
			return "website/dashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SUD_USER")) {
			model.addAttribute("sudMonitoringDto", new SudMonitoringDto());
			model.addAttribute("batchdetails", sudMonitoringService.findSudMonitoringList(user.getEmpbranch()));
			// master data population

			model.addAttribute("dilist", sudMonitoringService.findSudDiStatusList());
			model.addAttribute("docketlist", sudMonitoringService.findSudDocketStatusList());
			model.addAttribute("finallist", sudMonitoringService.findSudFinalStatusList());
			model.addAttribute("leagallist", sudMonitoringService.findSudLegalStatusList());
			model.addAttribute("palist", sudMonitoringService.findSudPaStatusList());
			model.addAttribute("technicallist", sudMonitoringService.findSudTechnicalStatusList());

			return "website/suddashboard";
		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SUD_ADMIN")) {

			if (user.getRoles().size() > 1 && request2.getParameter("selectedrole") != null
					&& request2.getParameter("selectedrole") != "") {
				for (Role r : user.getRoles()) {
					if (r.getId().toString().equalsIgnoreCase(request2.getParameter("selectedrole"))
							&& r.getName().equalsIgnoreCase("ROLE_SURVEY_ADMIN")) {
						model.addAttribute("batchdetails", customerSurveryService.findSurveyCustomerList());
						return "website/surveydashboard";
					} else if (r.getId().toString().equalsIgnoreCase(request2.getParameter("selectedrole"))
							&& r.getName().equalsIgnoreCase("ROLE_SUD_ADMIN")) {
						model.addAttribute("sudMonitoringDto", new SudMonitoringDto());
						model.addAttribute("batchdetails", sudMonitoringService.findSudMonitoringList(null));
						return "website/suddashboard";
					}
				}
			} else {
				model.addAttribute("sudMonitoringDto", new SudMonitoringDto());
				model.addAttribute("batchdetails", sudMonitoringService.findSudMonitoringList(null));
				return "website/suddashboard";
			}

		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SURVEY_ADMIN")) {

			if (user.getRoles().size() > 1 && request2.getParameter("selectedrole") != null
					&& request2.getParameter("selectedrole") != "") {
				for (Role r : user.getRoles()) {
					if (r.getId().toString().equalsIgnoreCase(request2.getParameter("selectedrole"))
							&& r.getName().equalsIgnoreCase("ROLE_SURVEY_ADMIN")) {
						model.addAttribute("batchdetails", customerSurveryService.findSurveyCustomerList());
						return "website/surveydashboard";
					} else if (r.getId().toString().equalsIgnoreCase(request2.getParameter("selectedrole"))
							&& r.getName().equalsIgnoreCase("ROLE_SUD_ADMIN")) {
						model.addAttribute("sudMonitoringDto", new SudMonitoringDto());
						model.addAttribute("batchdetails", sudMonitoringService.findSudMonitoringList(null));
						return "website/suddashboard";
					}
				}
			} else {
				model.addAttribute("batchdetails", customerSurveryService.findSurveyCustomerList());
				return "website/surveydashboard";

			}

		} else if (user.getRoles().get(0).getName().equalsIgnoreCase("ROLE_SURVEY_USER")) {
			model.addAttribute("batchdetails", customerSurveryService.findSurveyCustomerList());
			return "website/surveydashboard";
		}

		return "website/dashboard";
	}

	@PostMapping("/savesudmonitoring")
	public String add(ModelAndView modelAndView, @ModelAttribute("sudMonitoringDto") SudMonitoringDto sudMonitoringDto,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		User user = userService.findByUsername(name);
		SudMonitoring suddb = sudMonitoringService.findbySudMonitoringId(sudMonitoringDto.getId());

		suddb.setDidateifdoable(sudMonitoringDto.getDidateifdoable());

		if (sudMonitoringDto.getDistatus() != 0) {
			SudDiStatus distatus = new SudDiStatus();
			distatus.setId(sudMonitoringDto.getDistatus());
			suddb.setDistatus(distatus);
		}

		if (sudMonitoringDto.getLegalstatus() != 0) {
			SudLegalStatus loginstatus = new SudLegalStatus();
			loginstatus.setId(sudMonitoringDto.getLegalstatus());
			suddb.setLegalstatus(loginstatus);
		}
		if (sudMonitoringDto.getTechnicalstatus() != 0) {

			SudTechnicalStatus technicalstatus = new SudTechnicalStatus();
			technicalstatus.setId(sudMonitoringDto.getTechnicalstatus());
			suddb.setTechnicalstatus(technicalstatus);
		}
		if (sudMonitoringDto.getPastatus() != 0) {

			SudDpaStatus dpstatus = new SudDpaStatus();
			dpstatus.setId(sudMonitoringDto.getPastatus());
			suddb.setPastatus(dpstatus);
		}

		if (sudMonitoringDto.getDocketstatus() != 0) {

			SudDocketStatus docketstatus = new SudDocketStatus();
			docketstatus.setId(sudMonitoringDto.getDocketstatus());
			suddb.setDocketstatus(docketstatus);
		}
		if (sudMonitoringDto.getFinalstatus() != 0) {

			SudFinalStatus finalstatus = new SudFinalStatus();
			finalstatus.setId(sudMonitoringDto.getFinalstatus());
			suddb.setFinalstatus(finalstatus);
		}
		suddb.setAdditionalremarks(sudMonitoringDto.getAdditionalremarks());
		suddb.setUpdatedon(new Date());
		suddb.setUpdatedby(user);
		sudMonitoringService.saveMonitoring(suddb);

		return "redirect:/dashboard";
	}

	// GET Method to upload data
	@GetMapping(value = "/uploadcustomer")
	public ModelAndView UploadBulkData(HttpServletRequest request) {

		request.getSession().setAttribute("uploadbulkDatalist", null);

		ModelAndView modelAndView = new ModelAndView("website/surverycustomerupload");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			modelAndView.addObject("lisrRoles", user.getRoles());
		} else {
			modelAndView.addObject("lisrRoles", null);
		}

		modelAndView.addObject("surveryDataUpload", new SurveryDataUpload());
		return modelAndView;

	}

	// Post Method to view upload data
	@PostMapping(value = "/validateuploadcustomer")
	public ModelAndView validateUploadBulkData(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView("website/surverycustomerupload");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			modelAndView.addObject("lisrRoles", user.getRoles());
		} else {
			modelAndView.addObject("lisrRoles", null);
		}
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());

		file.transferTo(convFile);

		List<SurveryDataUpload> invoices = Poiji.fromExcel(convFile, SurveryDataUpload.class);
		System.out.println("Printing List Data: " + invoices);

		modelAndView.addObject("uploadbulkDatalist", invoices);
		request.getSession().setAttribute("uploadbulkDatalist", invoices);
		return modelAndView;
	}

	// Save Method to upload data
	@PostMapping(value = "/saveuploadcustomer")
	public ModelAndView saveUploadBulkData(SurveryDataUpload surveryDataUpload,
			@RequestParam("file") MultipartFile file, BindingResult bindingResult, HttpServletRequest request,
			RedirectAttributes redir) throws MessagingException {
		ModelAndView modelAndView = new ModelAndView("website/surverycustomerupload");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<SurveryDataUpload> invoices = (List<SurveryDataUpload>) request.getSession()
				.getAttribute("uploadbulkDatalist");
		System.out.println("Printing List Data: " + invoices);
		for (SurveryDataUpload excel : invoices) {
			surverySMS = "Dear+Customer,+thank+you+for+being+our+valuable+customer.+Here,+we+are+conducting+a+customer+satisfaction+survey,+and+your+response+would+be+appreciated.+Please+click+the+link+to+fill+your+feedback+<link>+We+will+use+your+feedback+to+further+improve+our+service.%0aThank+you.%0aShubham+Housing";
			surveyUrl = "https://houseportal.shubham.co:8949/submit-survey/";
			SurveyCustomer invoice = new SurveyCustomer();

			/*
			 * invoice =
			 * customerSurveryService.findCustomerByApplication(excel.getApplicationnumber()
			 * ); if (invoice == null) { invoice = new SurveyCustomer(); }
			 */
			String security = generateString();
			invoice.setApplicationnumber(excel.getApplicationnumber());
			invoice.setApplicantname(excel.getApplicantname());
			invoice.setMobileno(excel.getMobileno());
			invoice.setEmailid(excel.getEmailid());
			invoice.setCreateon(new Date());
			invoice.setSecuritycode(security);
			invoice.setCategory(excel.getCategory());
			customerSurveryService.save(invoice);
			surveyUrl = surveyUrl + security;

			surverySMS = surverySMS.replace("<link>", surveyUrl);
			surveySMSHindi = surveySMSHindi.replace("<link>", surveyUrl);
			// System.out.println( maskString(str, 0, 12, '*') );
			String url = surverySMS;
			System.out.println(url);
			SendSMS sms = new SendSMS(invoice.getMobileno(), url);
			Thread th = new Thread(sms);
			th.start();

			/*
			 * url = surveySMSHindi; System.out.println(url); sms = new
			 * SendSMS(invoice.getMobileno(), url); th = new Thread(sms); th.start();
			 */

			/*
			 * MimeMessage emailMessage = mailSender.createMimeMessage(); MimeMessageHelper
			 * mailBuilder = new MimeMessageHelper(emailMessage, true);
			 * 
			 * mailBuilder.setTo(excel.getEmailid());
			 * mailBuilder.setFrom("alerts.etl@shubham.co"); mailBuilder.setText("Hi " +
			 * invoice.getApplicantname() +
			 * "<br><br> Thanks for taking loan from Shubham Housing. Please use the below Link to give your feedback."
			 * + "<br><br> Click on Link: " + surveyUrl, true); // Second parameter
			 * indicates that this is HTML // mail
			 * mailBuilder.setSubject("Shubham Housing Customer Survey");
			 * 
			 * mailSender.send(emailMessage);
			 */
			redir.addFlashAttribute("successMessage", "SMS/Email has been sent successfully");
			modelAndView.addObject("successMessage", "SMS/Email has been sent successfully");

		}
		return new ModelAndView("redirect:uploadcustomer");

	}

	@GetMapping(value = "/register")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "website/register";
	}

	public String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	// GET Method to upload SUD data
	@GetMapping(value = "/uploaddata")
	public ModelAndView UploadData(HttpServletRequest request) {

		request.getSession().setAttribute("uploadDatalist", null);

		ModelAndView modelAndView = new ModelAndView("website/suddataupload");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			modelAndView.addObject("lisrRoles", user.getRoles());
		} else {
			modelAndView.addObject("lisrRoles", null);
		}

		modelAndView.addObject("sudDataUpload", new SudDataUpload());
		modelAndView.addObject("sudApplicationList", sudMonitoringService.findAllApplication());
		return modelAndView;

	}

	// Post Method to view upload data of SUD
	@PostMapping(value = "/validatedata")
	public ModelAndView validatedata(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView("website/suddataupload");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		User user = userService.findByUsername(name);
		if (user.getRoles() != null && user.getRoles().size() > 1) {
			modelAndView.addObject("lisrRoles", user.getRoles());
		} else {
			modelAndView.addObject("lisrRoles", null);
		}
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());

		file.transferTo(convFile);

		List<SudDataUpload> invoices = Poiji.fromExcel(convFile, SudDataUpload.class);
		System.out.println("Printing List Data: " + invoices);

		modelAndView.addObject("uploadDatalist", invoices);
		request.getSession().setAttribute("uploadDatalist", invoices);
		return modelAndView;
	}

	// Save Method to upload data
	@PostMapping(value = "/savedata")
	public ModelAndView saveUploadData(SudDataUpload sudDataUpload, @RequestParam("file") MultipartFile file,
			BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redir)
			throws MessagingException {
		ModelAndView modelAndView = new ModelAndView("website/suddataupload");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<SudDataUpload> invoices = (List<SudDataUpload>) request.getSession().getAttribute("uploadDatalist");
		System.out.println("Printing List Data: " + invoices);
		for (SudDataUpload excel : invoices) {
			SudApplication invoice = new SudApplication();

			/*
			 * invoice =
			 * customerSurveryService.findCustomerByApplication(excel.getApplicationnumber()
			 * ); if (invoice == null) { invoice = new SurveyCustomer(); }
			 */
			String security = generateString();
			invoice.setApplicationnumber(excel.getApplicationnumber());

			invoice.setCreateon(new Date());

			sudMonitoringService.saveApplication(invoice);

			redir.addFlashAttribute("successMessage", "Data uploaded successfully");
			modelAndView.addObject("successMessage", "Data uploaded successfully");

		}
		return new ModelAndView("redirect:uploaddata");

	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	@GetMapping("/excel/{downloadType}")
	public ResponseEntity<Resource> downloadExcel(
			@PathVariable String downloadType,
			@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate) {

		try {
			System.out.println("Inside excel download---------------------------" + downloadType);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

			// Create Excel workbook
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(downloadType);
			//CustomerSurveyReport
			if (downloadType.equalsIgnoreCase("CustomerSurveyReport")) {
				List<SurveyCustomerDto> dataList = customerSurveryService.findSurveyCustomerList();
				int rowNum = 0;
				Row rowHeader = sheet.createRow(rowNum++);
				rowHeader.createCell(0).setCellValue("Application Number");
				rowHeader.createCell(1).setCellValue("Applicant Name");
				rowHeader.createCell(2).setCellValue("Mobile No");
				rowHeader.createCell(3).setCellValue("Email");
				rowHeader.createCell(4).setCellValue("SMS Send On");
				rowHeader.createCell(5).setCellValue("Survery Submit On");
				rowHeader.createCell(6).setCellValue("Question1");
				rowHeader.createCell(7).setCellValue("Answers1");
				rowHeader.createCell(8).setCellValue("Question2");
				rowHeader.createCell(9).setCellValue("Answers2");
				rowHeader.createCell(10).setCellValue("Question3");
				rowHeader.createCell(11).setCellValue("Answers3");
				rowHeader.createCell(12).setCellValue("Question4");
				rowHeader.createCell(13).setCellValue("Answers4");
				rowHeader.createCell(14).setCellValue("Question5");
				rowHeader.createCell(15).setCellValue("Answers5");
				rowHeader.createCell(16).setCellValue("Feedback");

				for (SurveyCustomerDto data : dataList) {
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(data.getApplicationnumber());
					row.createCell(1).setCellValue(data.getApplicantname());
					row.createCell(2).setCellValue(data.getMobileno());
					row.createCell(3).setCellValue(data.getEmailid());
					row.createCell(4).setCellValue(data.getFeedbackpostdate());
					row.createCell(5).setCellValue(data.getFeedbacksubmitdate());
					row.createCell(6).setCellValue(data.getQuestion1());
					row.createCell(7).setCellValue(data.getAnswers1());
					row.createCell(8).setCellValue(data.getQuestion2());
					row.createCell(9).setCellValue(data.getAnswers2());
					row.createCell(10).setCellValue(data.getQuestion3());
					row.createCell(11).setCellValue(data.getAnswers3());
					row.createCell(12).setCellValue(data.getQuestion4());
					row.createCell(13).setCellValue(data.getAnswers4());
					row.createCell(14).setCellValue(data.getQuestion5());
					row.createCell(15).setCellValue(data.getAnswers5());
					row.createCell(16).setCellValue(data.getFeedback());

				}

			} else if (downloadType.equalsIgnoreCase("FinnoBankreport") && fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {

				List<batchpagingDto> dataList = batchService.findApplicationBybatchid(null, fromDate, toDate);
				int rowNum = 0;
				Row rowHeader = sheet.createRow(rowNum++);
				// Define column headers
				rowHeader.createCell(0).setCellValue("ID");
				rowHeader.createCell(1).setCellValue("Batch Number");
				rowHeader.createCell(2).setCellValue("FinnoBank Number");
				rowHeader.createCell(3).setCellValue("Status");
				rowHeader.createCell(4).setCellValue("Total Collected Amount");
				rowHeader.createCell(5).setCellValue("Branch Name");
				rowHeader.createCell(6).setCellValue("Employee Code");

				rowHeader.createCell(7).setCellValue("Created By");
				rowHeader.createCell(8).setCellValue("Created On");
				rowHeader.createCell(9).setCellValue("Deposit On");
				rowHeader.createCell(10).setCellValue("Loan Number");
				rowHeader.createCell(11).setCellValue("CUSTOMER NAME");
				rowHeader.createCell(12).setCellValue("EMI Amount");
				rowHeader.createCell(13).setCellValue("Total Dues");
				rowHeader.createCell(14).setCellValue("Collected Amount");
				rowHeader.createCell(15).setCellValue("Receipt Number");
				rowHeader.createCell(16).setCellValue("Receipt Purpose");

				String previousId = null; // To keep track of the previous ID
				int forzenColumnCount = 9;
				String[] previousValues = new String[forzenColumnCount]; // Array to store the frozen values

				for (batchpagingDto data : dataList) {
					Row row = sheet.createRow(rowNum++);
					String currentId = String.valueOf(data.getId());

					if (currentId.equals(previousId)) {
						// For duplicate ID: fill the ID and columns with previous values (frozen values)
						row.createCell(0).setCellValue(previousId);  // Repeat the ID for the duplicate row
						for (int i = 0; i < previousValues.length; i++) {
							row.createCell(i + 1).setCellValue(previousValues[i]); // Fill in the frozen columns with the previous row's data
						}
					} else {
						// New ID: Populate all fields and save values for subsequent duplicate rows
						row.createCell(0).setCellValue(currentId);  // ID column
						row.createCell(1).setCellValue(data.getBatchnumber());
						row.createCell(2).setCellValue(data.getFinobankacknumber());
						row.createCell(3).setCellValue(data.getUserstatus());
						row.createCell(4).setCellValue(data.getTotalcollectedamount());
						row.createCell(5).setCellValue(data.getBranchname());
						row.createCell(6).setCellValue(data.getEmpcode());
						row.createCell(7).setCellValue(data.getCreatedby());
						row.createCell(8).setCellValue(data.getCreateon() != null ? sdf.format(data.getCreateon()) : "");
						row.createCell(9).setCellValue(data.getDepositon() != null ? sdf.format(data.getDepositon()) : "");

						// Save the frozen values for the duplicate rows
						previousId = currentId;
						previousValues = new String[]{
								data.getBatchnumber(),
								data.getFinobankacknumber(),
								data.getUserstatus(),
								data.getTotalcollectedamount(),
								data.getBranchname(),
								data.getEmpcode(),
								data.getCreatedby(),
								data.getCreateon() != null ? sdf.format(data.getCreateon()) : "",
								data.getDepositon() != null ? sdf.format(data.getDepositon()) : ""
						};
					}

					// Populate the varying columns (those that change per row)
					row.createCell(10).setCellValue(data.getLoannumber());
					row.createCell(11).setCellValue(data.getCustomername());
					row.createCell(12).setCellValue(data.getEmiamount());
					row.createCell(13).setCellValue(data.getTotaldue());
					row.createCell(14).setCellValue(data.getCollectedamount());
					row.createCell(15).setCellValue(data.getReciptnumber());
					row.createCell(16).setCellValue(data.getPaymentype());
				}

			} else if (downloadType.equalsIgnoreCase("Airtelreport") && fromDate == null && fromDate.isEmpty() && toDate == null && toDate.isEmpty()) {

				List<batchpagingDto> dataList = airtelBatchService.findApplicationBybatchid(null, fromDate, toDate);
				int rowNum = 0;
				Row rowHeader = sheet.createRow(rowNum++);
				rowHeader.createCell(0).setCellValue("ID");
				rowHeader.createCell(1).setCellValue("Batch Number");
				rowHeader.createCell(2).setCellValue("FinnoBank Number");
				rowHeader.createCell(3).setCellValue("Status");
				rowHeader.createCell(4).setCellValue("Total Collected Amount");
				rowHeader.createCell(5).setCellValue("Branch Name");
				rowHeader.createCell(6).setCellValue("Employee Code");

				rowHeader.createCell(7).setCellValue("Created By");
				rowHeader.createCell(8).setCellValue("Created On");
				rowHeader.createCell(9).setCellValue("Deposit On");
				rowHeader.createCell(10).setCellValue("Loan Number");
				rowHeader.createCell(11).setCellValue("CUSTOMER NAME");
				rowHeader.createCell(12).setCellValue("EMI Amount");
				rowHeader.createCell(13).setCellValue("Total Dues");
				rowHeader.createCell(14).setCellValue("Collected Amount");
				rowHeader.createCell(15).setCellValue("Receipt Number");
				rowHeader.createCell(16).setCellValue("Receipt Purpose");

				for (batchpagingDto data : dataList) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(String.valueOf(data.getId()));
					row.createCell(1).setCellValue(data.getBatchnumber());
					row.createCell(2).setCellValue(data.getFinobankacknumber());
					row.createCell(3).setCellValue(data.getUserstatus());
					row.createCell(4).setCellValue(data.getTotalcollectedamount());
					row.createCell(5).setCellValue(data.getBranchname());
					row.createCell(6).setCellValue(data.getEmpcode());
					row.createCell(7).setCellValue(data.getCreatedby());
					
					if(data.getCreateon()!=null)
					row.createCell(8).setCellValue(sdf.format(data.getCreateon()));
					else
					row.createCell(8).setCellValue("");	
						
					
					if(data.getDepositon()!=null)
					row.createCell(9).setCellValue(sdf.format(data.getDepositon()));
					else
					row.createCell(9).setCellValue("");	
						
					
					row.createCell(10).setCellValue(data.getLoannumber());
					row.createCell(11).setCellValue(data.getCustomername());
					row.createCell(12).setCellValue(data.getEmiamount());
					row.createCell(13).setCellValue(data.getTotaldue());
					row.createCell(14).setCellValue(data.getCollectedamount());
					row.createCell(15).setCellValue(data.getReciptnumber());
					row.createCell(16).setCellValue(data.getPaymentype());

				}

			}else if (downloadType.equalsIgnoreCase("SUDMonitoringReport")) {

				List<SudMonitoringDto> dataList = sudMonitoringService.findSudMonitoringList(null);
				int rowNum = 0;
				Row rowHeader = sheet.createRow(rowNum++);
				rowHeader.createCell(0).setCellValue("Application ID");
				rowHeader.createCell(1).setCellValue("Application Number");
				rowHeader.createCell(2).setCellValue("Applicant name");
				rowHeader.createCell(3).setCellValue("Branch Name");
				rowHeader.createCell(4).setCellValue("REGION");
				rowHeader.createCell(5).setCellValue("PRODUCT");
				rowHeader.createCell(6).setCellValue("SCHEME");
				rowHeader.createCell(7).setCellValue("Login Date");
				rowHeader.createCell(8).setCellValue("Sanction Date");
				rowHeader.createCell(9).setCellValue("Sanction Loan Amount");
				rowHeader.createCell(10).setCellValue("Disbursal Amount");
				rowHeader.createCell(11).setCellValue("Legal Status");
				rowHeader.createCell(12).setCellValue("Technical Status");
				rowHeader.createCell(13).setCellValue("PA Status");
				rowHeader.createCell(14).setCellValue("Docket Status");
				rowHeader.createCell(15).setCellValue("DI Status");
				rowHeader.createCell(16).setCellValue("Final Status");
				rowHeader.createCell(17).setCellValue("DI Date if Doable");
				rowHeader.createCell(18).setCellValue("Additional Remarks");
				rowHeader.createCell(19).setCellValue("Update On");

				for (SudMonitoringDto data : dataList) {
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(data.getId().toString());
					row.createCell(1).setCellValue(data.getApplicationnumber());
					row.createCell(2).setCellValue(data.getCustomername());
					row.createCell(3).setCellValue(data.getBranchname());
					row.createCell(4).setCellValue(data.getRegion());
					row.createCell(5).setCellValue(data.getProduct());
					row.createCell(6).setCellValue(data.getScheme());
					
					if(data.getLogindate()!=null)
					row.createCell(7).setCellValue(sdf.format(data.getLogindate()));
					else
					row.createCell(7).setCellValue("");	
						
					
					if(data.getSanctiondate()!=null)
					row.createCell(8).setCellValue(sdf.format(data.getSanctiondate()));
					else
					row.createCell(8).setCellValue("");	
						
					
					row.createCell(9).setCellValue(data.getSanctionloanamount().toString());
					row.createCell(10).setCellValue(data.getDisbursalamount().toString());
					row.createCell(11).setCellValue(data.getLegalstatusname());
					row.createCell(12).setCellValue(data.getTechnicalstatusname());
					row.createCell(13).setCellValue(data.getPastatusname());
					row.createCell(14).setCellValue(data.getDocketstatusname());
					row.createCell(15).setCellValue(data.getDistatusname());
					row.createCell(16).setCellValue(data.getFinalstatusname());
					row.createCell(17).setCellValue(data.getDidateifdoable());
					row.createCell(18).setCellValue(data.getAdditionalremarks());
					
					if(data.getUpdateOn()!=null)
					row.createCell(19).setCellValue(sdf.format(data.getUpdateOn()));
					else
					row.createCell(19).setCellValue("");	
						

				}
			} else if (downloadType.equalsIgnoreCase("FinnoBankreport") && fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
				List<batchpagingDto> dataList = batchService.findApplicationBybatchid(null, fromDate,toDate );
				int rowNum = 0;
				Row rowHeader = sheet.createRow(rowNum++);
				rowHeader.createCell(0).setCellValue("ID");
				rowHeader.createCell(1).setCellValue("Batch Number");
				rowHeader.createCell(2).setCellValue("FinnoBank Number");
				rowHeader.createCell(3).setCellValue("Status");
				rowHeader.createCell(4).setCellValue("Total Collected Amount");
				rowHeader.createCell(5).setCellValue("Branch Name");
				rowHeader.createCell(6).setCellValue("Employee Code");

				rowHeader.createCell(7).setCellValue("Created By");
				rowHeader.createCell(8).setCellValue("Created On");
				rowHeader.createCell(9).setCellValue("Deposit On");
				rowHeader.createCell(10).setCellValue("Loan Number");
				rowHeader.createCell(11).setCellValue("CUSTOMER NAME");
				rowHeader.createCell(12).setCellValue("EMI Amount");
				rowHeader.createCell(13).setCellValue("Total Dues");
				rowHeader.createCell(14).setCellValue("Collected Amount");
				rowHeader.createCell(15).setCellValue("Receipt Number");
				rowHeader.createCell(16).setCellValue("Receipt Purpose");

				for (batchpagingDto data : dataList) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(String.valueOf(data.getId()));
					row.createCell(1).setCellValue(data.getBatchnumber());
					row.createCell(2).setCellValue(data.getFinobankacknumber());
					row.createCell(3).setCellValue(data.getUserstatus());
					row.createCell(4).setCellValue(data.getTotalcollectedamount());
					row.createCell(5).setCellValue(data.getBranchname());
					row.createCell(6).setCellValue(data.getEmpcode());
					row.createCell(7).setCellValue(data.getCreatedby());

					if (data.getCreateon() != null)
						row.createCell(8).setCellValue(sdf.format(data.getCreateon()));
					else
						row.createCell(8).setCellValue("");


					if (data.getDepositon() != null)
						row.createCell(9).setCellValue(sdf.format(data.getDepositon()));
					else
						row.createCell(9).setCellValue("");


					row.createCell(10).setCellValue(data.getLoannumber());
					row.createCell(11).setCellValue(data.getCustomername());
					row.createCell(12).setCellValue(data.getEmiamount());
					row.createCell(13).setCellValue(data.getTotaldue());
					row.createCell(14).setCellValue(data.getCollectedamount());
					row.createCell(15).setCellValue(data.getReciptnumber());
					row.createCell(16).setCellValue(data.getPaymentype());

				}

			}else if (downloadType.equalsIgnoreCase("Airtelreport") && fromDate != null || !fromDate.isEmpty() && toDate != null || !toDate.isEmpty()) {

				List<batchpagingDto> dataList = airtelBatchService.findApplicationBybatchid(null, fromDate, toDate);
				int rowNum = 0;
				Row rowHeader = sheet.createRow(rowNum++);
				rowHeader.createCell(0).setCellValue("ID");
				rowHeader.createCell(1).setCellValue("Batch Number");
				rowHeader.createCell(2).setCellValue("FinnoBank Number");
				rowHeader.createCell(3).setCellValue("Status");
				rowHeader.createCell(4).setCellValue("Total Collected Amount");
				rowHeader.createCell(5).setCellValue("Branch Name");
				rowHeader.createCell(6).setCellValue("Employee Code");

				rowHeader.createCell(7).setCellValue("Created By");
				rowHeader.createCell(8).setCellValue("Created On");
				rowHeader.createCell(9).setCellValue("Deposit On");
				rowHeader.createCell(10).setCellValue("Loan Number");
				rowHeader.createCell(11).setCellValue("CUSTOMER NAME");
				rowHeader.createCell(12).setCellValue("EMI Amount");
				rowHeader.createCell(13).setCellValue("Total Dues");
				rowHeader.createCell(14).setCellValue("Collected Amount");
				rowHeader.createCell(15).setCellValue("Receipt Number");
				rowHeader.createCell(16).setCellValue("Receipt Purpose");

				for (batchpagingDto data : dataList) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(String.valueOf(data.getId()));
					row.createCell(1).setCellValue(data.getBatchnumber());
					row.createCell(2).setCellValue(data.getFinobankacknumber());
					row.createCell(3).setCellValue(data.getUserstatus());
					row.createCell(4).setCellValue(data.getTotalcollectedamount());
					row.createCell(5).setCellValue(data.getBranchname());
					row.createCell(6).setCellValue(data.getEmpcode());
					row.createCell(7).setCellValue(data.getCreatedby());

					if (data.getCreateon() != null)
						row.createCell(8).setCellValue(sdf.format(data.getCreateon()));
					else
						row.createCell(8).setCellValue("");


					if (data.getDepositon() != null)
						row.createCell(9).setCellValue(sdf.format(data.getDepositon()));
					else
						row.createCell(9).setCellValue("");


					row.createCell(10).setCellValue(data.getLoannumber());
					row.createCell(11).setCellValue(data.getCustomername());
					row.createCell(12).setCellValue(data.getEmiamount());
					row.createCell(13).setCellValue(data.getTotaldue());
					row.createCell(14).setCellValue(data.getCollectedamount());
					row.createCell(15).setCellValue(data.getReciptnumber());
					row.createCell(16).setCellValue(data.getPaymentype());

				}

			}
			// Write workbook to ByteArrayOutputStream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);

			// Create Resource from ByteArrayOutputStream
			byte[] excelBytes = baos.toByteArray();
			ByteArrayResource resource = new ByteArrayResource(excelBytes);

			// Set Content-Disposition header
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadType + ".xlsx");

			// Return ResponseEntity with resource and headers
			return ResponseEntity.ok().headers(headers).contentLength(excelBytes.length)
					.contentType(MediaType
							.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
					.body(resource);
		} catch (IOException e) {
			// Handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/csv/{downloadType}")
	public ResponseEntity<Resource> downloadCSV(@PathVariable(required = false) String downloadType, @RequestParam(name = "fromDate", required = false) String fromDate,@RequestParam(name = "toDate", required = false) String toDate) {

		try {
			System.out.println("Inside csv download---------------------------" + downloadType);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			// Create CSV file
			File csvFile = File.createTempFile(downloadType, ".csv");

			CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
			if (downloadType.equalsIgnoreCase("CustomerSurveyReport")) {
				List<SurveyCustomerDto> csvData = customerSurveryService.findSurveyCustomerList();
				// Add CSV data to csvData

				writer.writeNext(new String[] { "Application Number", "Applicant Name", "Mobile No", "Email",
						"SMS Send On", "Survery Submit On", "Question1", "Answers1", "Question2", "Answers2",
						"Question3", "Answers3", "Question4", "Answers4", "Question5", "Answers5", "Feedback" });
				for (SurveyCustomerDto data : csvData) {
					writer.writeNext(new String[] { data.getApplicationnumber(), data.getApplicantname(),
							data.getMobileno(), data.getEmailid(), data.getFeedbackpostdate(),
							data.getFeedbacksubmitdate(), data.getQuestion1(), data.getAnswers1(), data.getQuestion2(),
							data.getAnswers2(), data.getQuestion3(), data.getAnswers3(), data.getQuestion4(),
							data.getAnswers4(), data.getQuestion5(), data.getAnswers5(), data.getFeedback() });
				}
			} else if (downloadType.equalsIgnoreCase("FinnoBankreport") && fromDate == null || fromDate.isEmpty()&& toDate == null || toDate.isEmpty()) {
				List<batchpagingDto> dataList = batchService.findApplicationBybatchid(null, null, null);
				// Write the header row
				writer.writeNext(new String[] { "ID", "Batch Number", "FinnoBank Number", "Status",
						"Total Collected Amount", "Branch Name", "Employee Code", "Created By", "Created On",
						"Deposit On", "Loan Number", "CUSTOMER NAME", "EMI Amount", "Total Dues", "Collected Amount",
						"Receipt Number", "Receipt Purpose", });

				// Write the POJO data to the CSV file
				for (batchpagingDto data : dataList) {
					writer.writeNext(new String[] { String.valueOf(data.getId()), data.getBatchnumber(),
							data.getFinobankacknumber(), data.getUserstatus(), data.getTotalcollectedamount(),
							data.getBranchname(), data.getEmpcode(), data.getCreatedby(),
							(data.getCreateon()!=null?sdf.format(data.getCreateon()):""), 
							(data.getDepositon()!=null?sdf.format(data.getDepositon()):""), data.getLoannumber(),
							data.getCustomername(), data.getEmiamount(), data.getTotaldue(), data.getCollectedamount(),
							data.getReciptnumber(), data.getPaymentype() });
				}

			} else if (downloadType.equalsIgnoreCase("Airtelreport") && fromDate == null || fromDate.isEmpty()&& toDate == null || toDate.isEmpty()) {
				List<batchpagingDto> dataList = airtelBatchService.findApplicationBybatchid(null, null,null);
				// Write the header row
				writer.writeNext(new String[] { "ID", "Batch Number", "FinnoBank Number", "Status",
						"Total Collected Amount", "Branch Name", "Employee Code", "Created By", "Created On",
						"Deposit On", "Loan Number", "CUSTOMER NAME", "EMI Amount", "Total Dues", "Collected Amount",
						"Receipt Number", "Receipt Purpose", });

				// Write the POJO data to the CSV file
				for (batchpagingDto data : dataList) {
					writer.writeNext(new String[] { String.valueOf(data.getId()), data.getBatchnumber(),
							data.getFinobankacknumber(), data.getUserstatus(), data.getTotalcollectedamount(),
							data.getBranchname(), data.getEmpcode(), data.getCreatedby(),
							(data.getCreateon()!=null?sdf.format(data.getCreateon()):""), 
							(data.getDepositon()!=null?sdf.format(data.getDepositon()):""), data.getLoannumber(),
							data.getCustomername(), data.getEmiamount(), data.getTotaldue(), data.getCollectedamount(),
							data.getReciptnumber(), data.getPaymentype() });
				}

			} else if (downloadType.equalsIgnoreCase("SUDMonitoringReport")) {
				List<SudMonitoringDto> dataList = sudMonitoringService.findSudMonitoringList(null);
				writer.writeNext(new String[] { "Application ID", "Application Number", "Applicant name", "Branch Name",
						"REGION", "PRODUCT", "SCHEME", "Login Date", "Sanction Date", "Sanction Loan Amount",
						"Disbursal Amount", "Legal Status", "Technical Status", "PA Status", "Docket Status",
						"DI Status", "Final Status", "DI Date if Doable", "Additional Remarks", "Update On", });

				// Write the POJO data to the CSV file
				for (SudMonitoringDto data : dataList) {
					writer.writeNext(new String[] { data.getId().toString(), data.getApplicationnumber(),
							data.getCustomername(), data.getBranchname(), data.getRegion(), data.getProduct(),
							data.getScheme(), 
							(data.getLogindate()!=null?sdf.format(data.getLogindate()):""), (data.getSanctiondate()!=null?sdf.format(data.getSanctiondate()):""),
							data.getSanctionloanamount().toString(), data.getDisbursalamount().toString(),
							data.getLegalstatusname(), data.getTechnicalstatusname(), data.getPastatusname(),
							data.getDistatusname(), data.getFinalstatusname(), data.getDidateifdoable(),
							data.getAdditionalremarks(),
							(data.getUpdateOn()!=null?sdf.format(data.getUpdateOn()):"") });
				}

			} else if (downloadType.equalsIgnoreCase("FinnoBankreport") && fromDate != null && toDate != null) {
				List<batchpagingDto> dataList = batchService.findApplicationBybatchid(null, fromDate,toDate);
				// Write the header row
				writer.writeNext(new String[]{"ID", "Batch Number", "FinnoBank Number", "Status",
						"Total Collected Amount", "Branch Name", "Employee Code", "Created By", "Created On",
						"Deposit On", "Loan Number", "CUSTOMER NAME", "EMI Amount", "Total Dues", "Collected Amount",
						"Receipt Number", "Receipt Purpose",});

				// Write the POJO data to the CSV file
				for (batchpagingDto data : dataList) {
					writer.writeNext(new String[]{String.valueOf(data.getId()), data.getBatchnumber(),
							data.getFinobankacknumber(), data.getUserstatus(), data.getTotalcollectedamount(),
							data.getBranchname(), data.getEmpcode(), data.getCreatedby(),
							(data.getCreateon() != null ? sdf.format(data.getCreateon()) : ""),
							(data.getDepositon() != null ? sdf.format(data.getDepositon()) : ""), data.getLoannumber(),
							data.getCustomername(), data.getEmiamount(), data.getTotaldue(), data.getCollectedamount(),
							data.getReciptnumber(), data.getPaymentype()});
				}
			}else if (downloadType.equalsIgnoreCase("Airtelreport") && fromDate != null && toDate != null) {
				List<batchpagingDto> dataList = airtelBatchService.findApplicationBybatchid(null, fromDate,toDate);
				// Write the header row
				writer.writeNext(new String[]{"ID", "Batch Number", "FinnoBank Number", "Status",
						"Total Collected Amount", "Branch Name", "Employee Code", "Created By", "Created On",
						"Deposit On", "Loan Number", "CUSTOMER NAME", "EMI Amount", "Total Dues", "Collected Amount",
						"Receipt Number", "Receipt Purpose",});

				// Write the POJO data to the CSV file
				for (batchpagingDto data : dataList) {
					writer.writeNext(new String[]{String.valueOf(data.getId()), data.getBatchnumber(),
							data.getFinobankacknumber(), data.getUserstatus(), data.getTotalcollectedamount(),
							data.getBranchname(), data.getEmpcode(), data.getCreatedby(),
							(data.getCreateon() != null ? sdf.format(data.getCreateon()) : ""),
							(data.getDepositon() != null ? sdf.format(data.getDepositon()) : ""), data.getLoannumber(),
							data.getCustomername(), data.getEmiamount(), data.getTotaldue(), data.getCollectedamount(),
							data.getReciptnumber(), data.getPaymentype()});
				}
			}
			writer.close();

			// Create Resource from file
			FileSystemResource resource = new FileSystemResource(csvFile);

			// Set Content-Disposition header
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadType + ".csv");

			// Return ResponseEntity with resource and headers
			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
					.contentType(MediaType.parseMediaType("text/csv")).body(resource);
		} catch (IOException e) {
			// Handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
