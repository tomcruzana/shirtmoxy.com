package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.ContactUsCategoryDto;
import com.shirtmoxy.app.dto.ContactUsDto;
import com.shirtmoxy.app.exception.ContactUsCategoryException;
import com.shirtmoxy.app.exception.ContactUsException;

public interface ContactUsService {
	public List<ContactUsCategoryDto> readAllCategories() throws ContactUsCategoryException;
	
	public void sendMessage(ContactUsDto contactUsDto) throws ContactUsException;
}
