package com.shirtmoxy.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.EmailSubscriptionDto;
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
	public void addEmail(EmailSubscriptionDto emailSubscriptionDto) throws EmailSubscriptionException {

		EmailSubscription emailSubscription = new EmailSubscription();
		emailSubscription.setEmail(emailSubscriptionDto.getEmail());
		emailSubscriptionRepo.save(emailSubscription);
	}

}
