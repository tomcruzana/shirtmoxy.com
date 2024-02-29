package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.MaterialDto;
import com.shirtmoxy.app.entity.Material;
import com.shirtmoxy.app.exception.MaterialException;
import com.shirtmoxy.app.repository.MaterialRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepo;

	@Autowired
	@Qualifier("MaterialConverter")
	private ObjectConverter<MaterialDto, Material> materialConverter;

	@Override
	public List<MaterialDto> readAllMaterials() throws MaterialException {
		// Create a Sort object to sort by id in ascending order
		Sort sortById = Sort.by(Sort.Direction.ASC, "id");

		List<Material> materialList = materialRepo.findAll(sortById);

		// throw exception if none found
		if (materialList.isEmpty()) {
			throw new MaterialException("No materials found");
		}

		List<MaterialDto> materialDtoList = new ArrayList<>();
		for (Material material : materialList) {
			MaterialDto materialDto = materialConverter.convertToDTO(material);
			materialDtoList.add(materialDto);
		}

		return materialDtoList;
	}

}
