package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.ManufacturerDto;
import com.shirtmoxy.app.exception.ManufacturerException;

public interface ManufacturerService {
	public List<ManufacturerDto> readAllManufacturers() throws ManufacturerException;
}
