package com.kemal.spring.web.controllers.viewControllers.adminControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.AirtelApplicationDetails;
import com.kemal.spring.domain.AirtelBatchDetails;
import com.kemal.spring.domain.ApplicationDetails;
import com.kemal.spring.domain.BatchDetails;
import com.kemal.spring.domain.User;
import com.kemal.spring.service.ActivityDetailsService;
import com.kemal.spring.service.AirtelApplicationDetailsService;
import com.kemal.spring.service.AirtelBatchDetailsService;
import com.kemal.spring.service.ApplicationDetailsService;
import com.kemal.spring.service.BatchDetailsService;
import com.kemal.spring.service.UserService;
import com.kemal.spring.web.dto.BatchDTO;

@Controller
@RequestMapping("")

public class AirtelBatchController {
	private final AirtelBatchDetailsService batchService;
	private final AirtelApplicationDetailsService applicationService;
	private final UserService userService;
	private final ActivityDetailsService activityService;

	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate osourceTemplate;

	public AirtelBatchController(AirtelBatchDetailsService batchService, AirtelApplicationDetailsService applicationService,
			UserService userService,ActivityDetailsService activityService) {
		this.batchService = batchService;
		this.applicationService = applicationService;
		this.userService = userService;
		this.activityService=activityService;
	}

	// Get method for creating batch
	//@GetMapping("/pagesignout")
	public ModelAndView signout() {

		ModelAndView modelAndView = new ModelAndView("website/login");

		// modelAndView.addObject("batchDto", new BatchDTO());
		return modelAndView;
	}

	// Get method for creating batch
	@GetMapping("/airtelbatchcreate")
	public ModelAndView batchcreation(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("users/airtelbatchcreation");

		modelAndView.addObject("batchDto", new BatchDTO());
		request.getSession().setAttribute("batchdetails", new ArrayList<BatchDTO>());
		return modelAndView;
	}

	// GET method for fetch

	@PostMapping("/airtelfetchloandata")
	public ModelAndView fetch(ModelAndView modelAndView, @ModelAttribute("batchDto") BatchDTO batchDto,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {
		modelAndView = new ModelAndView("users/airtelbatchcreation");
System.out.println("select * from bbps_payment where LOAN_ACCOUNT_NUMBER like '%"+batchDto.getLoannumber()+"%' or APPLICATION_NUMBER like '%"+batchDto.getLoannumber()+"%'");
		List<BatchDTO> strlist = osourceTemplate.query(
				"select * from bbps_payment where LOAN_ACCOUNT_NUMBER like '%"+batchDto.getLoannumber()+"%' or APPLICATION_NUMBER like '%"+batchDto.getLoannumber()+"%'",
				//new Object[] { batchDto.getLoannumber(), batchDto.getLoannumber()},
				new ResultSetExtractor<List<BatchDTO>>() {

					@Override
					public List<BatchDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<BatchDTO> list = new ArrayList<BatchDTO>();

						while (rs.next()) {
							BatchDTO valuation_Details_Form = new BatchDTO();
							valuation_Details_Form.setLoannumber(rs.getString("LOAN_ACCOUNT_NUMBER"));
							valuation_Details_Form.setCustomername(rs.getString("CUSTOMER_NAME"));
							valuation_Details_Form
									.setEmiamount(Double.parseDouble(rs.getString("NEXT INSTALLMENT AMOUNT")));

							valuation_Details_Form.setTotalamount(Double.parseDouble(rs.getString("EMI_OVERDUE")));
							list.add(valuation_Details_Form);
						}

						return list; // rs.next() ? rs.getString("APPLICATION_NUMBER") : null;
					}
				});
if(strlist.isEmpty()) {return modelAndView;}
		batchDto.setCustomername(strlist.get(0).getCustomername());
		batchDto.setEmiamount(strlist.get(0).getEmiamount());
		batchDto.setTotalamount(strlist.get(0).getTotalamount());
		batchDto.setLoannumber(strlist.get(0).getLoannumber());
		List<BatchDTO> batchdetails = (List<BatchDTO>) request.getSession().getAttribute("batchdetails");
		modelAndView.addObject("batchdetails", batchdetails);
		request.getSession().setAttribute("batchdetails", batchdetails);
		modelAndView.addObject("batchDto", batchDto);
		return modelAndView;
	}

	// GET method for add

	@PostMapping("/airteladddata")
	public ModelAndView add(ModelAndView modelAndView, @ModelAttribute("batchDto") BatchDTO batchDto,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("users/airtelbatchcreation");
		List<BatchDTO> batchdetails = (List<BatchDTO>) request.getSession().getAttribute("batchdetails");
		if (batchdetails == null || batchdetails.size() == 0) {
			batchdetails = new ArrayList<>();
		}
		if (batchDto.getLoannumber() != null) {
			batchdetails.add(batchDto);
			modelAndView.addObject("batchdetails", batchdetails);
			request.getSession().setAttribute("batchdetails", batchdetails);
		}
		batchDto = new BatchDTO();
		modelAndView.addObject("batchDto", batchDto);
		return modelAndView;
	}

	@PostMapping("/airtelsavebatch")
	public String geaneratebatch(ModelAndView modelAndView, @ModelAttribute("batchDto") BatchDTO batchDto,
			BindingResult bindingResult, HttpServletRequest request, Errors errors) {

		modelAndView = new ModelAndView("users/airtelbatchcreation");
		// Authentication auth = (Authentication)
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		User user = userService.findByUsername(name);

		List<BatchDTO> batchdetails = (List<BatchDTO>) request.getSession().getAttribute("batchdetails");

		AirtelBatchDetails batch = new AirtelBatchDetails();

		batch.setActive(1);
		batch.setBatchnumber((new SimpleDateFormat("yyyymmddhhmmss")).format(new Date()));

		batch.setCreatedby(user);
		batch.setCreateon(new Date());
		for (BatchDTO bt : batchdetails) {
			batch.setTotalcollectedamount(batch.getTotalcollectedamount() + bt.getCollectedamount());
		}

		batch.setUserstatus("created");
		
		batch = batchService.save(batch);
		// BatchDetails batch = (BatchDetails) batchService.findApplicationBybatchid();
		for (BatchDTO bt : batchdetails) {

			AirtelApplicationDetails app = new AirtelApplicationDetails();
			app.setBatchDetails(batch);

			app.setCustomername(bt.getCustomername());
			app.setEmiamount(bt.getEmiamount());
			app.setTotaldue(bt.getTotalamount());
			app.setReciptnumber(bt.getReciptnumber());
			app.setPaymentype(bt.getPaymentype());
			app.setCollectedAmount(bt.getCollectedamount());
			app.setLoannumber(bt.getLoannumber());
			app.setCreatedby(user);
			app.setCreateon(bt.getCreateon());
			applicationService.save(app);
		}

		Activity activity = new Activity();
		activity.setType("BATCH");
		activity.setUser(user);
		activity.setCreateon(new Date());
		activity.setDescription("Batch created Successfully");
		activityService.save(activity);
		
		batchDto = new BatchDTO();
		modelAndView.addObject("batchDto", batchDto);
		return "redirect:/batchcreate";
	}

	@GetMapping("/airteldeletefetchdata/{id}")
	public ModelAndView deletefetchdata(@PathVariable Integer id, ModelAndView modelAndView,
			HttpServletRequest request) {
		List<BatchDTO> batchdetails = (List<BatchDTO>) request.getSession().getAttribute("batchdetails");
		modelAndView = new ModelAndView("users/airtelbatchcreation");
		for (int i = 0; i < batchdetails.size(); i++) {
			if (id.equals(i)) {
	              // throws java.util.ConcurrentModificationException
	              batchdetails.remove(batchdetails.get(i));
	         }
		}
		
		modelAndView.addObject("batchdetails", batchdetails);
		System.out.println(batchdetails.size());
		request.getSession().setAttribute("batchdetails", batchdetails);

		BatchDTO batchDto = new BatchDTO();
		modelAndView.addObject("batchDto", batchDto);
		return modelAndView;

	}

	// Get method for payment update
	@GetMapping("/airtelupdatepayment")
	public ModelAndView paymentupdation(Model model) {
		// using below lines we are populating list
		ModelAndView modelAndView = new ModelAndView("users/airtelpaymentupdate");
		model.addAttribute("batchdetails", batchService.findPendingApplicationBybatchid());
		return modelAndView;

	}

	// Get method for payment update page
	@GetMapping("/airtelupdate/{id}")
	public String batchupdate(@PathVariable String id) {

		AirtelBatchDetails batch = batchService.findById(Long.parseLong(id));
		// batch.setActive(0);
		//batch.setUserstatus("updated");
		batchService.save(batch);
		// return modelAndView;
		return "redirect:/updatepayment";

	}

	// Get method for batch delete
	@GetMapping("/airtelshowdeletebatch")
	public ModelAndView batchdelate(Model model) {
		ModelAndView modelAndView = new ModelAndView("users/airtelbatchdelete");
		model.addAttribute("batchdetails", batchService.findPendingApplicationBybatchid());
		return modelAndView;

	}

	// Get method for delete page
	@GetMapping("/airteldeletebatch/{id}")
	public String batchdelation(@PathVariable String id) {
		AirtelBatchDetails batch = batchService.findById(Long.parseLong(id));
		batch.setActive(0);
		batchService.save(batch);
		// return modelAndView;
		return "redirect:/showdeletebatch";

	}

}
