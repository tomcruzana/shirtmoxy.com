package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.ContactUsCategoryDto;
import com.shirtmoxy.app.dto.ContactUsDto;
import com.shirtmoxy.app.entity.ContactUs;
import com.shirtmoxy.app.entity.ContactUsCategory;
import com.shirtmoxy.app.exception.ContactUsCategoryException;
import com.shirtmoxy.app.exception.ContactUsException;
import com.shirtmoxy.app.repository.ContactUsCategoryRepository;
import com.shirtmoxy.app.repository.ContactUsRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsCategoryRepository contactUsCategoryRepo;
	
	@Autowired
	private ContactUsRepository contactUsRepo;

	@Autowired
	@Qualifier("ContactUsCategoryConverter")
	private ObjectConverter<ContactUsCategoryDto, ContactUsCategory> contactUsCategoryConverter;

	@Autowired
	@Qualifier("ContactUsConverter")
	private ObjectConverter<ContactUsDto, ContactUs> contactUsConverter;

	@Override
	public List<ContactUsCategoryDto> readAllCategories() throws ContactUsCategoryException {
		// Create a Sort object to sort by id in ascending order
		Sort sortById = Sort.by(Sort.Direction.ASC, "id");

		List<ContactUsCategory> contactUsCategoryList = contactUsCategoryRepo.findAll(sortById);

		// throw exception if none found
		if (contactUsCategoryList.isEmpty()) {
			throw new ContactUsCategoryException("No contactUsCategories found");
		}

		List<ContactUsCategoryDto> contactUsCategoryDtoList = new ArrayList<>();
		for (ContactUsCategory contactUsCategory : contactUsCategoryList) {
			ContactUsCategoryDto contactUsCategoryDto = contactUsCategoryConverter.convertToDTO(contactUsCategory);
			contactUsCategoryDtoList.add(contactUsCategoryDto);
		}

		return contactUsCategoryDtoList;
	}

	@Override
	public void sendMessage(ContactUsDto contactUsDto) throws ContactUsException {
		ContactUs contactUsMessageDetails = new ContactUs();
		contactUsMessageDetails = contactUsConverter.convertToEntity(contactUsDto);

		contactUsRepo.save(contactUsMessageDetails);
	}

}
