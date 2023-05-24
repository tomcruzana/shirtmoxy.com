package com.shirtmoxy.app.service;

import com.shirtmoxy.app.dto.EmailSubscriptionDto;
import com.shirtmoxy.app.exception.EmailSubscriptionException;

public interface EmailSubscriptionService {

	public void addEmail(EmailSubscriptionDto emailSubscriptionDto) throws EmailSubscriptionException;
}
