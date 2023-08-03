package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.exception.GenderException;

public interface GenderService {
	public List<GenderDto> readAllGenders() throws GenderException;
}
