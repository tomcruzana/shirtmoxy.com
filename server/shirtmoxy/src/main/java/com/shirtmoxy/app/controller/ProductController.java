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
import com.shirtmoxy.app.dto.ColorDto;
import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.dto.ManufacturerDto;
import com.shirtmoxy.app.dto.MaterialDto;
import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.dto.ProductSkuDto;
import com.shirtmoxy.app.dto.SizeDto;
import com.shirtmoxy.app.service.CategoryService;
import com.shirtmoxy.app.service.ColorService;
import com.shirtmoxy.app.service.GenderService;
import com.shirtmoxy.app.service.ManufacturerService;
import com.shirtmoxy.app.service.MaterialService;
import com.shirtmoxy.app.service.ProductService;
import com.shirtmoxy.app.service.SizeService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ColorService colorService;

	@Autowired
	GenderService genderService;

	@Autowired
	SizeService sizeService;

	@Autowired
	MaterialService materialService;

	@Autowired
	ManufacturerService manufacturerService;

	/** start of filters **/
	@GetMapping("/category/all")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {

		List<CategoryDto> categoryList = categoryService.readAllCategories();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}

	@GetMapping("/color/all")
	public ResponseEntity<List<ColorDto>> getAllColors() {

		List<ColorDto> colorList = colorService.readAllColors();
		return new ResponseEntity<>(colorList, HttpStatus.OK);
	}

	@GetMapping("/gender/all")
	public ResponseEntity<List<GenderDto>> getAllGenders() {

		List<GenderDto> genderList = genderService.readAllGenders();
		return new ResponseEntity<>(genderList, HttpStatus.OK);
	}

	@GetMapping("/size/all")
	public ResponseEntity<List<SizeDto>> getAllSizes() {

		List<SizeDto> sizeList = sizeService.readAllSizes();
		return new ResponseEntity<>(sizeList, HttpStatus.OK);
	}

	@GetMapping("/material/all")
	public ResponseEntity<List<MaterialDto>> getAllMaterials() {

		List<MaterialDto> materialList = materialService.readAllMaterials();
		return new ResponseEntity<>(materialList, HttpStatus.OK);
	}

	@GetMapping("/manufacturer/all")
	public ResponseEntity<List<ManufacturerDto>> getAllManufacturers() {

		List<ManufacturerDto> manufacturerList = manufacturerService.readAllManufacturers();
		return new ResponseEntity<>(manufacturerList, HttpStatus.OK);
	}

	/** end of filters **/

	/** start of dynamic search filters **/
	@GetMapping("/{clothing}/filteredList")
	public ResponseEntity<Page<ProductDto>> getAllFilteredProducts(
			@PathVariable(name = "clothing", required = true) String productType,
			@RequestParam(name = "query", required = false) String searchQuery,
			@RequestParam(name = "categories", required = false) List<String> categories,
			@RequestParam(name = "manufacturers", required = false) List<String> manufacturers,
			@RequestParam(name = "genders", required = false) List<String> genders,
			@RequestParam(name = "colors", required = false) List<String> colors,
			@RequestParam(name = "sizes", required = false) List<String> sizes,
			@RequestParam(name = "materials", required = false) List<String> materials,
			@RequestParam(name = "pageNum", required = true) int pageNum,
			@RequestParam(name = "pageSize", required = true) int pageSize) {

		Page<ProductDto> productPage = productService.readFilteredProducts(productType, searchQuery, categories,
				manufacturers, genders, colors, sizes, materials, pageNum, pageSize);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	/** end of dynamic search filters **/

	/** start of product details **/
	@GetMapping("/details/{sku}")
	public ResponseEntity<ProductDto> getProductDetailsBySku(@PathVariable String sku) {

		ProductDto productDetails = productService.readProductDetailsBySku(sku);
		return new ResponseEntity<>(productDetails, HttpStatus.OK);
	}

	@GetMapping("/sku-details")
	public ResponseEntity<ProductSkuDto> getProductSkuByProductNameAndColorId(@RequestParam("productName") String name,
			@RequestParam("colorId") int colorId) {

		ProductSkuDto productSkuDetails = productService.readProductSkuByNameAndColor(name, colorId);
		return new ResponseEntity<>(productSkuDetails, HttpStatus.OK);
	}

	@GetMapping("/details")
	public ResponseEntity<ProductDto> getProductDetailsByProductNameAndColorId(@RequestParam("productName") String name,
			@RequestParam("colorId") int colorId) {

		ProductDto productDto = productService.readProductDetailsByNameAndColor(name, colorId);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}

	@GetMapping("/details/available-colors")
	public ResponseEntity<List<ColorDto>> getProductColorsByProductName(@RequestParam("productName") String name) {

		List<ColorDto> availableProductColors = colorService.readProductColorsByProductName(name);
		return new ResponseEntity<>(availableProductColors, HttpStatus.OK);
	}

	@GetMapping("/details/in-stock-sizes")
	public ResponseEntity<List<SizeDto>> getInStockProductSizes(@RequestParam("productName") String name,
			@RequestParam("colorId") int colorId) {

		List<SizeDto> availableProductSizes = sizeService.readProductInStockSizes(name, colorId);
		return new ResponseEntity<>(availableProductSizes, HttpStatus.OK);
	}

	@GetMapping("/details/out-of-stock-sizes")
	public ResponseEntity<List<SizeDto>> getOutOfStockProductSizes(@RequestParam("productName") String name,
			@RequestParam("colorId") int colorId) {

		List<SizeDto> availableProductSizes = sizeService.readProductOutOfStockSizes(name, colorId);
		return new ResponseEntity<>(availableProductSizes, HttpStatus.OK);
	}

	@GetMapping("/details/available-sizes")
	public ResponseEntity<List<SizeDto>> getProductSizesByProductName(@RequestParam("productName") String name) {

		List<SizeDto> availableProductSizes = sizeService.readProductSizesByProductName(name);
		return new ResponseEntity<>(availableProductSizes, HttpStatus.OK);
	}
	/** end of product details **/

}
