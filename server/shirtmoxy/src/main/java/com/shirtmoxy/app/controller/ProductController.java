package com.shirtmoxy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.CategoryDto;
import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.dto.ManufacturerDto;
import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.service.CategoryService;
import com.shirtmoxy.app.service.GenderService;
import com.shirtmoxy.app.service.ManufacturerService;
import com.shirtmoxy.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	GenderService genderService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ManufacturerService manufacturerService;

	/** start of load all products **/
	@GetMapping("/all")
	public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> productPage = productService.readAllProducts(pageNum);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	/** end of load all products **/

	/** start of search filters **/
	@GetMapping("/gender/all")
	public ResponseEntity<List<GenderDto>> getAllGenders() {

		List<GenderDto> genderList = genderService.readAllGenders();
		return new ResponseEntity<>(genderList, HttpStatus.OK);
	}

	@GetMapping("/gender")
	public ResponseEntity<Page<ProductDto>> getProductByGenderId(@RequestParam("gId") int id,
			@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> productPage = productService.readByGenderId(id, pageNum);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	@GetMapping("/category/all")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {

		List<CategoryDto> categoryList = categoryService.readAllCategories();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<Page<ProductDto>> getProductByCategoryId(@RequestParam("cId") int id,
			@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> productPage = productService.readByCategoryId(id, pageNum);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	@GetMapping("/manufacturer/all")
	public ResponseEntity<List<ManufacturerDto>> getAllManufacturers() {

		List<ManufacturerDto> manufacturerList = manufacturerService.readAllManufacturers();
		return new ResponseEntity<>(manufacturerList, HttpStatus.OK);
	}

	@GetMapping("/manufacturer")
	public ResponseEntity<Page<ProductDto>> getProductByManufacturerId(@RequestParam("mId") int id,
			@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> productPage = productService.readByManufacturerId(id, pageNum);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	/** end of search filters **/

	/** start of product search **/
	@GetMapping("/search")
	public ResponseEntity<Page<ProductDto>> searchProducts(@RequestParam("query") String keyword,
			@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> searchResult = productService.search(keyword, pageNum);
		return new ResponseEntity<>(searchResult, HttpStatus.OK);
	}

	/** end of product search **/

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductDetailsById(@PathVariable int id) {

		ProductDto productDetails = productService.readProductDetailsById(id);
		return new ResponseEntity<>(productDetails, HttpStatus.OK);
	}

}
