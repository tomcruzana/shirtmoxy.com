package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.shirtmoxy.app.entity.EmailSubscription;

public interface EmailSubscriptionRepository extends CrudRepository<EmailSubscription, Integer> {
	
}
