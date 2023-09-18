package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.ColorDto;
import com.shirtmoxy.app.exception.ColorException;

public interface ColorService {
	public List<ColorDto> readAllColors() throws ColorException;
	
	public List<ColorDto> readProductColorsByProductName(String name)  throws ColorException;
}
