package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.ColorDto;
import com.shirtmoxy.app.entity.Color;
import com.shirtmoxy.app.exception.ColorException;
import com.shirtmoxy.app.repository.ColorRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorRepository colorRepo;

	@Autowired
	@Qualifier("ColorConverter")
	private ObjectConverter<ColorDto, Color> colorConverter;

	@Override
	public List<ColorDto> readAllColors() throws ColorException {
		// Create a Sort object to sort by id in ascending order
		Sort sortById = Sort.by(Sort.Direction.ASC, "id");

		List<Color> colorList = colorRepo.findAll(sortById);

		// throw exception if none found
		if (colorList.isEmpty()) {
			throw new ColorException("No colors found");
		}

		List<ColorDto> colorDtoList = new ArrayList<>();
		for (Color color : colorList) {
			ColorDto colorDto = colorConverter.convertToDTO(color);
			colorDtoList.add(colorDto);
		}

		return colorDtoList;
	}

	@Override
	public List<ColorDto> readProductColorsByProductName(String name) throws ColorException {
		List<Color> availableColors = colorRepo.findColorsByProductName(name);

		// check if availableColors is null or empty
		if (availableColors == null || availableColors.isEmpty()) {
			throw new ColorException("No available colors found.");
		}

		List<ColorDto> availableColorsDtos = new ArrayList<>();

		for (Color color : availableColors) {
			ColorDto availableColorsDto = colorConverter.convertToDTO(color);
			availableColorsDtos.add(availableColorsDto);
		}

		return availableColorsDtos;
	}

}
