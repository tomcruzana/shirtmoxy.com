package com.shirtmoxy.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shirtmoxy.app.entity.Color;
import com.shirtmoxy.app.entity.Size;
import com.shirtmoxy.app.repository.ColorRepository;
import com.shirtmoxy.app.repository.SizeRepository;

@SpringBootTest
class ShirtmoxyApplicationTests {

	@Autowired
	private ColorRepository colorRepo;
	
	@Autowired
	private SizeRepository sizeRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getColorsbyProductNameTest() {
//		fail();
		List<Color> availableColors = colorRepo.findColorsByProductName("Adult Topflight Heather Performance T-Shirt");
		
		for (Color color : availableColors) {
			System.out.println("Color id: " + color.getId());
		    System.out.println("Color Name: " + color.getName());
		    System.out.println("Red: " + color.getRed());
		    System.out.println("Green: " + color.getGreen());
		    System.out.println("Blue: " + color.getBlue());
		}
		
	    // Assert that the list is not null
	    assertNotNull(availableColors);

	    // Assert that the list is not empty
	    assertFalse(availableColors.isEmpty());
		
	}
	
	@Test
	public void getSizesbyProductNameTest() {
//		fail();
		List<Size> availableSizes = sizeRepo.findAllSizesByProductName("Adult Topflight Heather Performance T-Shirt");
		
		for (Size size : availableSizes) {
			System.out.println("Color id: " + size.getId());
		    System.out.println("Color Name: " + size.getType());
		}
		
	    // Assert that the list is not null
	    assertNotNull(availableSizes);

	    // Assert that the list is not empty
	    assertFalse(availableSizes.isEmpty());
	}
	
	@Test
	public void getAllProductInStockSizesTest() {
//		fail();
		List<Size> inStockSizes = sizeRepo.findAllProductInStockSizes("Adult Topflight Heather Performance T-Shirt", 4, 0);
		
		for (Size size : inStockSizes) {
			System.out.println("Color id: " + size.getId());
		    System.out.println("Color Name: " + size.getType());
		}
		
	    // Assert that the list is not null
	    assertNotNull(inStockSizes);

	    // Assert that the list is not empty
	    assertFalse(inStockSizes.isEmpty());
	}
	
	@Test
	public void getAllProductOutofStockSizesTest() {
//		fail();
		List<Size> outOfStockSizes = sizeRepo.findAllProductOutOfStockSizes("Adult Topflight Heather Performance T-Shirt", 4, 0);
		
		for (Size size : outOfStockSizes) {
			System.out.println("Color id: " + size.getId());
		    System.out.println("Color Name: " + size.getType());
		}
		
	    // Assert that the list is not null
	    assertNotNull(outOfStockSizes);

	    // Assert that the list is not empty
	    assertFalse(outOfStockSizes.isEmpty());
	}

}
