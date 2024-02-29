package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.SizeDto;
import com.shirtmoxy.app.exception.SizeException;

public interface SizeService {
	public List<SizeDto> readAllSizes() throws SizeException;
	
	public List<SizeDto> readProductSizesByProductName(String name)  throws SizeException;
	
	public List<SizeDto> readProductInStockSizes(String name, int colorId) throws SizeException;
	
	public List<SizeDto> readProductOutOfStockSizes(String name, int colorId) throws SizeException;
}
