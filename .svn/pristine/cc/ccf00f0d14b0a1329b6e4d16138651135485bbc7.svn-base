package com.kemal.spring.service;

import org.springframework.stereotype.Service;

import com.kemal.spring.domain.ApplicationDetails;
import com.kemal.spring.domain.ApplicationDetailsRepository;

@Service
public class ApplicationDetailsService {

	private ApplicationDetailsRepository applicationDetailsRepository;

	public ApplicationDetailsService(ApplicationDetailsRepository applicationDetailsRepository) {

		this.applicationDetailsRepository = applicationDetailsRepository;
	}

	public void save(ApplicationDetails app) {
		// TODO Auto-generated method stub
		applicationDetailsRepository.save(app);
	}
}
