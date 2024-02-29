package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.MaterialDto;
import com.shirtmoxy.app.exception.MaterialException;

public interface MaterialService {
	public List<MaterialDto> readAllMaterials() throws MaterialException;
}
