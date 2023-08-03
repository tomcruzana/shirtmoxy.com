package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.entity.Gender;
import com.shirtmoxy.app.exception.GenderException;
import com.shirtmoxy.app.repository.GenderRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class GenderServiceImpl implements GenderService {

	@Autowired
	private GenderRepository genderRepo;

	@Autowired
	@Qualifier("GenderConverter")
	private ObjectConverter<GenderDto, Gender> genderConverter;

	@Override
	public List<GenderDto> readAllGenders() throws GenderException {
		// Create a Sort object to sort by id in ascending order
		Sort sortById = Sort.by(Sort.Direction.ASC, "id");

		List<Gender> genderList = genderRepo.findAll(sortById);

		// throw category exception if no categories found
		if (genderList.isEmpty()) {
			throw new GenderException("No genders found");
		}

		List<GenderDto> genderDtoList = new ArrayList<>();
		for (Gender gender : genderList) {
			GenderDto genderDto = genderConverter.convertToDTO(gender);
			genderDtoList.add(genderDto);
		}

		return genderDtoList;
	}

}
