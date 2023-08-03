import { Component, OnInit } from "@angular/core";
import { ProductCategory } from "app/models/product-category.model";
import { ProductGender } from "app/models/product-gender.model";
import { ProductManufacturer } from "app/models/product-manufacturer.model";
import { ProductService } from "app/services/product/product.service";

@Component({
    selector: "app-search-filter",
    templateUrl: "./search-filter.component.html",
    styleUrls: ["./search-filter.component.scss"],
})
export class SearchFilterComponent implements OnInit {
    // search filter properties
    isExpanded: boolean = true;

    productCategories: ProductCategory[] = [];
    productGenders: ProductGender[] = [];
    productManufacturers: ProductManufacturer[] = [];

    constructor(private productService: ProductService) {}

    // load all product search filters on init
    ngOnInit(): void {
        this.loadProductSearchFilters();
    }

    // collapse search filter
    toggleSearchFilterCollapse() {
        this.isExpanded = !this.isExpanded;
    }

    // list all related product filters
    loadProductSearchFilters(): void {
        this.productService.getAllProductGenders().subscribe((data) => {
            this.productGenders = data;
        });

        this.productService.getAllProductCategories().subscribe((data) => {
            this.productCategories = data;
        });

        this.productService.getAllProductManufacturers().subscribe((data) => {
            this.productManufacturers = data;
        });
    }
}
