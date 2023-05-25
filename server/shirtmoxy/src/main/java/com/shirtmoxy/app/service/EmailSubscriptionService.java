package com.shirtmoxy.app.service;

import com.shirtmoxy.app.exception.EmailSubscriptionException;

public interface EmailSubscriptionService {

	public void addEmail(String email) throws EmailSubscriptionException;
}
