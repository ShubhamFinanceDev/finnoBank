package com.kemal.spring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemal.spring.domain.SurveryAnswerRepository;
import com.kemal.spring.domain.SurveryCustomerDetailsRepository;
import com.kemal.spring.domain.SurveryCustomerRepository;
import com.kemal.spring.domain.SurveryQuestionRepository;
import com.kemal.spring.domain.SurveyAnswers;
import com.kemal.spring.domain.SurveyCustomer;
import com.kemal.spring.domain.SurveyCustomerDetails;
import com.kemal.spring.domain.SurveyQuestions;
import com.kemal.spring.web.dto.SurveyAnswersDto;
import com.kemal.spring.web.dto.SurveyCustomerDto;
import com.kemal.spring.web.dto.SurveyQuestionsDto;

@Service
public class CustomerSurveryService {

	private SurveryCustomerDetailsRepository surveryCustomerDetailsRepository;
	private SurveryCustomerRepository surveryCustomerRepository;
	private SurveryQuestionRepository surveryQuestionRepository;
	private SurveryAnswerRepository surveryAnswerRepository;

	String[] colors = new String[] { /*
										 * "#6495ED", "#FFFACD", "#ADD8E6", "#90EE90", "#FFA07A", "#20B2AA", "#9370DB",
										 * "#7B68EE", "#FFC0CB", "#4682B4", "#40E0D0"
										 */
			"#FF7F50", "#FF6347", "#FFA500", "#008000", "#3CB371", "#7B68EE", "#8A2BE2", "#FF1493", "#F4A460",
			"#DEB887", "#FF69B4" };

	public CustomerSurveryService(SurveryCustomerDetailsRepository surveryCustomerDetailsRepository,
			SurveryCustomerRepository surveryCustomerRepository, SurveryQuestionRepository surveryQuestionRepository,
			SurveryAnswerRepository surveryAnswerRepository) {
		this.surveryCustomerDetailsRepository = surveryCustomerDetailsRepository;
		this.surveryCustomerRepository = surveryCustomerRepository;
		this.surveryQuestionRepository = surveryQuestionRepository;
		this.surveryAnswerRepository = surveryAnswerRepository;

	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public List<SurveyCustomerDto> findSurveyCustomerList() {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		List<SurveyCustomerDto> returnlist = new ArrayList<SurveyCustomerDto>();
		List<SurveyCustomer> customerList = surveryCustomerRepository.findAll();

		for (SurveyCustomer customer : customerList) {
			String color = colors[randInt(0, colors.length - 1)];
			SurveyCustomerDto dto = new SurveyCustomerDto();
			dto.setApplicationnumber(customer.getApplicationnumber());
			dto.setApplicantname(customer.getApplicantname());
			dto.setEmailid(customer.getEmailid());
			dto.setMobileno(customer.getMobileno());
			dto.setRowColor(color);
			dto.setFeedback(customer.getFeedback());
			dto.setFeedbackpostdate(sdf.format(customer.getCreateon()));
			List<SurveyCustomerDetails> list = surveryCustomerDetailsRepository
					.findCustomerSurveryDetails(customer.getId());// sudMonitoringRepository.findSudMonitoringList();
			for (int i = 0; i < list.size(); i++) {
				
				dto.setFeedbacksubmitdate(sdf.format(list.get(0).getCreateon()));
				
				if (i == 0) {
					dto.setQuestion1(list.get(i).getQuestionid().getQuestion());
					dto.setAnswers1(list.get(i).getAnswerid().getAnswers());
				}
				if (i == 1) {
					dto.setQuestion2(list.get(i).getQuestionid().getQuestion());
					dto.setAnswers2(list.get(i).getAnswerid().getAnswers());
				}
				if (i == 2) {
					dto.setQuestion3(list.get(i).getQuestionid().getQuestion());
					dto.setAnswers3(list.get(i).getAnswerid().getAnswers());
				}
				if (i == 3) {
					dto.setQuestion4(list.get(i).getQuestionid().getQuestion());
					dto.setAnswers4(list.get(i).getAnswerid().getAnswers());
				}
				if (i == 4) {
					dto.setQuestion5(list.get(i).getQuestionid().getQuestion());
					dto.setAnswers5(list.get(i).getAnswerid().getAnswers());
				}

			}
			returnlist.add(dto);

		}
		return returnlist;
	}

	public SurveyCustomer findCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return surveryCustomerRepository.findById(customerId).get();

	}

	public List<SurveyQuestionsDto> findSurveyQuestionList(String category) {
		// TODO Auto-generated method stub
		List<SurveyQuestionsDto> returnlist = new ArrayList<>();
		List<SurveyQuestions> questions = surveryQuestionRepository.findAllByCategory(category);

		if (questions != null && questions.size() > 0) {
			for (SurveyQuestions q : questions) {
				SurveyQuestionsDto qt = new SurveyQuestionsDto();
				qt.setId(q.getId());
				qt.setQuestion(q.getQuestion());
				qt.setAnswersList(new ArrayList<>());
				List<SurveyAnswers> answers = surveryAnswerRepository.findbyQuestionId(q.getId());
				if (answers != null && answers.size() > 0) {
					for (SurveyAnswers a : answers) {
						SurveyAnswersDto at = new SurveyAnswersDto();
						at.setId(a.getId());
						at.setAnswers(a.getAnswers());
						qt.getAnswersList().add(at);
					}

				}
				returnlist.add(qt);

			}
		}

		return returnlist;
	}

	public void save(SurveyCustomer invoice) {

		surveryCustomerRepository.save(invoice);

	}

	public SurveyCustomer findCustomerByApplication(String applicationnumber) {
		// TODO Auto-generated method stub
		return surveryCustomerRepository.findCustomerByApplication(applicationnumber);
	}

	public void saveSurvey(SurveyCustomerDetails data) {
		// TODO Auto-generated method stub
		surveryCustomerDetailsRepository.save(data);
	}

	public SurveyAnswers findAnswerById(int answerid) {
		// TODO Auto-generated method stub
		return surveryAnswerRepository.findById(answerid).get();
	}

	public SurveyCustomer findCustomerBySecurity(String securitycode) {
		// TODO Auto-generated method stub
		return surveryCustomerRepository.findCustomerBySecurity(securitycode);
	}

	public Page<SurveyCustomer> findSurveyCustomerList(Pageable paging) {
		// TODO Auto-generated method stub		
		return surveryCustomerRepository.findAll(paging);
		
		//return surveryCustomerRepository.findAll(paging);
	}

	public Page<SurveyCustomer> findSurveyCustomerListByTitleContainingIgnoreCase(String keyword, Pageable paging) {
		// TODO Auto-generated method stub
		//return surveryCustomerRepository.findallColumns(keyword);
		return surveryCustomerRepository.findallColumns(keyword,paging);
	}

	public List<SurveyCustomerDetails> findCustomerSurveryDetails(int customerid) {
		// TODO Auto-generated method stub
		return surveryCustomerDetailsRepository.findCustomerSurveryDetails(customerid);
	}

}
