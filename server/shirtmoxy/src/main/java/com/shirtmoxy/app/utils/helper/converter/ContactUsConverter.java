package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.ContactUsCategoryDto;
import com.shirtmoxy.app.dto.ContactUsDto;
import com.shirtmoxy.app.entity.ContactUs;
import com.shirtmoxy.app.entity.ContactUsCategory;

@Component
@Qualifier("ContactUsConverter")
public class ContactUsConverter implements ObjectConverter<ContactUsDto, ContactUs> {

	@Override
	public ContactUsDto convertToDTO(ContactUs entity) {
		return null;
	}

	@Override
	public ContactUs convertToEntity(ContactUsDto dto) {
		ContactUs entity = new ContactUs();

		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setSubject(dto.getSubject());
		entity.setCategory(convertContactUsCategoryToEntity(dto.getCategory()));
		entity.setMessage(dto.getMessage());

		return entity;
	}

	public ContactUsCategory convertContactUsCategoryToEntity(ContactUsCategoryDto dto) {
		ContactUsCategory entity = new ContactUsCategory();
		
		// id field is only needed
		entity.setId(dto.getId());
		return entity;
	}

}
