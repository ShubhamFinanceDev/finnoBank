package com.kemal.spring.service;

import org.springframework.stereotype.Service;

import com.kemal.spring.domain.Activity;
import com.kemal.spring.domain.ActivityRepository;

@Service
public class ActivityDetailsService {

	private ActivityRepository activityRepository;

	public ActivityDetailsService(ActivityRepository activityRepository) {

		this.activityRepository = activityRepository;
	}

	public void save(Activity app) {
		// TODO Auto-generated method stub
		activityRepository.save(app);
	}
}
