package com.shirtmoxy.app;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.shirtmoxy.app.exception.EmailSubscriptionException;
import com.shirtmoxy.app.repository.EmailSubscriptionRepository;
import com.shirtmoxy.app.service.EmailSubscriptionService;

@SpringBootTest
public class EmailSubscriptionServiceTest {

	@Autowired
	private EmailSubscriptionRepository emailSubscriptionRepo;

	@Autowired
	private EmailSubscriptionService emailSubscriptionService;

	@Test
	public void testSaveEmailSubscription() throws EmailSubscriptionException {
		fail();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void emailValidationTest() throws EmailSubscriptionException {
		// Arrange
		String validEmail = "example@example.com";
		String invalidEmail = "invalid-email";

		emailSubscriptionService.addEmail(invalidEmail);

		// Act and Assert

	}

}
