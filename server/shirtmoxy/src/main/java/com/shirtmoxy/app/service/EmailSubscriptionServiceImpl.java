package com.shirtmoxy.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.entity.EmailSubscription;
import com.shirtmoxy.app.exception.EmailSubscriptionException;
import com.shirtmoxy.app.repository.EmailSubscriptionRepository;

@Service
public class EmailSubscriptionServiceImpl implements EmailSubscriptionService {

//	@Autowired
//	private Environment env;

	@Autowired
	private EmailSubscriptionRepository emailSubscriptionRepo;

	@Override
	public void addEmail(String email) throws EmailSubscriptionException {

		EmailSubscription emailSubscription = new EmailSubscription();
		emailSubscription.setEmail(email);

		long currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(currentTimeMillis);
		emailSubscription.setDateCreated(date);

		emailSubscriptionRepo.save(emailSubscription);

	}

}
