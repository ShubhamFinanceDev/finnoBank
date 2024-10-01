package com.kemal.spring.web.controllers.restApiControllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.kemal.spring.domain.*;
import com.kemal.spring.service.AirtelBatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemal.spring.service.BatchDetailsService;
import com.kemal.spring.service.CustomerSurveryService;
import com.kemal.spring.service.UserService;

/**
 * Created by Keno&Kemo on 16.12.2017..
 */

@RestController
public class restController {
	private UserService userService;

	private final BatchDetailsService batchService;
	private CustomerSurveryService customerSurveryService;
	@Autowired
	private AirtelBatchDetailsService airtelBatchDetailsService;

	public restController(UserService userService, BatchDetailsService batchService,
			CustomerSurveryService customerSurveryService) {
		this.userService = userService;
		this.batchService = batchService;
		this.customerSurveryService = customerSurveryService;
	}

	@PostMapping(value = { "/adminPage/paymendate/{batchid}/{finobankacknumber}",
			"/userPage/paymendate/{batchid}/{finobankacknumber}" })
	public ResponseEntity<String> paymentsave(@PathVariable Long batchid, @PathVariable String finobankacknumber) {
		try {
			BatchDetails batch = batchService.findById(batchid);
			batch.setFinobankacknumber(finobankacknumber);
			batch.setDepositon(new Date());
			batch.setUserstatus("deposit");
			batchService.save(batch);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
		// return modelAndView;

	}

	@PostMapping(value = { "/surveyadminPage/customer-submit-survey/{customerid}/{options}" })
	public ResponseEntity<String> paymentsave(@PathVariable Integer customerid, @PathVariable String[] options) {
		
		System.out.println(customerid+"     <>       "+options);
		try {

			for (String s : options) {
				SurveyCustomerDetails data = new SurveyCustomerDetails();
				SurveyCustomer customer = customerSurveryService.findCustomerById(customerid);
				data.setCustomerid(customer);
				SurveyAnswers ans = customerSurveryService.findAnswerById(Integer.parseInt(s));
				data.setAnswerid(ans);
				data.setQuestionid(ans.getQuestions());
				data.setCreateon(new Date());
				//customerSurveryService.saveSurvey(data);

			}
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
		// return modelAndView;

	}

	@GetMapping("/adminPage/json-users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> allUsers = userService.findAll();

		if (allUsers == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		else if (allUsers.isEmpty())
			return new ResponseEntity<>(allUsers, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	

	@DeleteMapping("/adminPage/json-users/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		Optional<User> userToDelete = userService.findById(id);

		if (!userToDelete.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		userService.deleteById(id);
		return new ResponseEntity<>(userToDelete.get(), HttpStatus.NO_CONTENT);
	}


	@PostMapping(value = { "/adminPage/airtelpaymendate/{batchid}/{finobankacknumber}",
			"/userPage/airtelpaymendate/{batchid}/{finobankacknumber}" })
	public ResponseEntity<String> paymentsaveForAirtel(@PathVariable Long batchid, @PathVariable String finobankacknumber) {
		try {
			AirtelBatchDetails airtelBatchDetails = airtelBatchDetailsService.findById(batchid);
			airtelBatchDetails.setFinobankacknumber(finobankacknumber);
			airtelBatchDetails.setDepositon(new Date());
			airtelBatchDetails.setUserstatus("deposit");
			airtelBatchDetailsService.save(airtelBatchDetails);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
		// return modelAndView;

	}
}