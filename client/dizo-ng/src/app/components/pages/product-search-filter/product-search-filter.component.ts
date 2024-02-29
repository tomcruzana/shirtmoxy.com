import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { ActivatedRoute, NavigationEnd, Router } from "@angular/router";
import { PageSizeEvent } from "app/models/interfaces/page-size-event";
import { ProductCategory } from "app/models/product-category.model";
import { ProductColor } from "app/models/product-color.model";
import { ProductGender } from "app/models/product-gender.model";
import { ProductManufacturer } from "app/models/product-manufacturer.model";
import { ProductMaterial } from "app/models/product-material";
import { ProductSize } from "app/models/product-size";
import { PageNumberService } from "app/services/page-number/page-number.service";
import { ProductFilterService } from "app/services/product-filter/product-filter.service";
import { ProductService } from "app/services/product/product.service";
import { filter } from "rxjs/operators";

@Component({
    selector: "app-product-search-filter",
    templateUrl: "./product-search-filter.component.html",
    styleUrls: ["./product-search-filter.component.scss"],
})
export class ProductSearchFilterComponent {
    // Default active button (all)
    activeButton: string = "all";

    // collapse elements
    collapseStates: { [key: string]: boolean } = {
        categoryFilterCollapse: true,
        colorFilterCollapse: true,
        priceFilterCollapse: true,
        genderFilterCollapse: true,
        brandFilterCollapse: true,
        sizeFilterCollapse: true,
        materialFilterCollapse: true,
    };

    // product filters
    productCategories: ProductCategory[] = [];
    productColors: ProductColor[] = [];
    // TODO - price
    productGenders: ProductGender[] = [];
    productManufacturers: ProductManufacturer[] = [];
    productSizes: ProductSize[] = [];
    productMaterials: ProductMaterial[] = [];

    // url params
    categoriesFilter: string = "";
    colorsFilter: string[] = [];
    gendersFilter: string[] = [];
    manufacturersFilter: string[] = [];
    sizesFilter: string[] = [];
    materialsFilter: string[] = [];

    constructor(
        private productService: ProductService,
        private route: ActivatedRoute,
        private router: Router,
        private productFilterService: ProductFilterService,
        private pageNumberService: PageNumberService
    ) {}

    ngOnInit(): void {
        // Initialize product filter menus
        this.loadProductFilters();

        // Read and parse query parameters from the URL
        this.route.queryParams.subscribe((queryParams) => {
            // Set the active category button based on query parameters
            this.categoriesFilter = queryParams["categories"] || "";

            if (this.categoriesFilter) {
                this.activeButton = this.categoriesFilter;
            }

            this.colorsFilter = queryParams["colors"]
                ? queryParams["colors"].split(",")
                : [];

            this.gendersFilter = queryParams["genders"]
                ? queryParams["genders"].split(",")
                : [];

            this.manufacturersFilter = queryParams["manufacturers"]
                ? queryParams["manufacturers"].split(",")
                : [];

            this.sizesFilter = queryParams["sizes"]
                ? queryParams["sizes"].split(",")
                : [];

            this.materialsFilter = queryParams["materials"]
                ? queryParams["materials"].split(",")
                : [];
        });
    }

    // collapse search filter
    toggleFilterCollapse(collapseId: string) {
        this.collapseStates[collapseId] = !this.collapseStates[collapseId];
    }

    // list all related product filters
    loadProductFilters(): void {
        this.productService
            .getAllProductCategories()
            .subscribe((categories) => {
                this.productCategories = categories;
            });

        this.productService.getAllProductColors().subscribe((colors) => {
            this.productColors = colors;
        });

        this.productService.getAllProductGenders().subscribe((genders) => {
            this.productGenders = genders;
        });

        this.productService
            .getAllProductManufacturers()
            .subscribe((manufacturers) => {
                this.productManufacturers = manufacturers;
            });

        this.productService.getAllProductSizes().subscribe((sizes) => {
            this.productSizes = sizes;
        });

        this.productService.getAllProductMaterials().subscribe((materials) => {
            this.productMaterials = materials;
        });
    }

    addToProductFilters(
        name: string,
        theFilter: string[] | string,
        paramName: string
    ): void {
        // Reset page number to 1
        this.pageNumberService.emitResetPageNumberEvent(1);

        this.productFilterService.addOrRemove(name, theFilter, paramName);
    }
}
