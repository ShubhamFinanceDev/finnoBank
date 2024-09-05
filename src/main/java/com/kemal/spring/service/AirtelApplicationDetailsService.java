package com.kemal.spring.service;

import org.springframework.stereotype.Service;

import com.kemal.spring.domain.AirtelApplicationDetails;
import com.kemal.spring.domain.AirtelApplicationDetailsRepository;

@Service
public class AirtelApplicationDetailsService {

	private AirtelApplicationDetailsRepository applicationDetailsRepository;

	public AirtelApplicationDetailsService(AirtelApplicationDetailsRepository applicationDetailsRepository) {

		this.applicationDetailsRepository = applicationDetailsRepository;
	}

	public void save(AirtelApplicationDetails app) {
		// TODO Auto-generated method stub
		applicationDetailsRepository.save(app);
	}
}
