package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.EmailSubscription;

@Repository
public interface EmailSubscriptionRepository extends CrudRepository<EmailSubscription, Integer> {
	
}
