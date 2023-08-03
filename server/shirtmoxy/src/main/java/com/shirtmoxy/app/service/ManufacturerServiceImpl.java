package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.ManufacturerDto;
import com.shirtmoxy.app.entity.Manufacturer;
import com.shirtmoxy.app.exception.ManufacturerException;
import com.shirtmoxy.app.repository.ManufacturerRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepo;

	@Autowired
	@Qualifier("ManufacturerConverter")
	private ObjectConverter<ManufacturerDto, Manufacturer> manufacturerConverter;

	@Override
	public List<ManufacturerDto> readAllManufacturers() throws ManufacturerException {
		// Create a Sort object to sort by id in ascending order
		Sort sortById = Sort.by(Sort.Direction.ASC, "id");

		List<Manufacturer> manufacturerList = manufacturerRepo.findAll(sortById);

		// throw manufacturer exception if no categories found
		if (manufacturerList.isEmpty()) {
			throw new ManufacturerException("No manufacturers found");
		}

		List<ManufacturerDto> manufacturerDtoList = new ArrayList<>();
		for (Manufacturer manufacturer : manufacturerList) {
			ManufacturerDto manufacturerDto = manufacturerConverter.convertToDTO(manufacturer);
			manufacturerDtoList.add(manufacturerDto);
		}

		return manufacturerDtoList;
	}

}
