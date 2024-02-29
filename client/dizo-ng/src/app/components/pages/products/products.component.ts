import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { PageSizeEvent } from "app/models/interfaces/page-size-event";
import { ProductOverview } from "app/models/product-overview";
import { PageNumberService } from "app/services/page-number/page-number.service";
import { ProductService } from "app/services/product/product.service";

@Component({
    selector: "app-products",
    templateUrl: "./products.component.html",
    styleUrls: ["./products.component.scss"],
})
export class ProductsComponent implements OnInit {
    // Product list properties
    productsOverviews: ProductOverview[] = [];

    // The initial color display limit when the component is loaded
    // this must match currentColorDisplayLimit initial value
    readonly INITIAL_COLOR_DISPLAY_LIMIT: number = 1;

    // The current color display limit, which may change
    currentColorDisplayLimit: number = 1;

    // Function to toggle the color display limit based on the index of the product
    toggleColorDisplayLimit(i: number) {
        this.currentColorDisplayLimit =
            this.currentColorDisplayLimit === this.INITIAL_COLOR_DISPLAY_LIMIT
                ? this.productsOverviews[i].availableColors.length
                : this.INITIAL_COLOR_DISPLAY_LIMIT;
    }

    // Function to calculate and return the remaining count of colors to display
    calculateRemainingDisplayedColors(i: number): number {
        return Math.max(
            0,
            this.productsOverviews[i].availableColors.length -
                this.currentColorDisplayLimit
        );
    }

    // Filtered list url query parameter properties (default is empty)
    currentFilteredListUrlQueryParams: string = "";
    previousFilteredListUrlQueryParams: string = "";

    // Page and size url query parameter properties
    currentPageAndSizeUrlQueryParams: string = "";

    // Pagination properties defaults
    pageNumber: number = 1;
    pageSize: number = 10;
    totalElements: number = 0;

    constructor(
        private productService: ProductService,
        private route: ActivatedRoute,
        private pageNumberService: PageNumberService
    ) {}

    ngOnInit(): void {
        this.route.queryParams.subscribe((params) => {
            // Access individual query parameters
            const search = params["query"] || "";
            const categories = params["categories"] || "";
            const manufacturers = params["manufacturers"] || "";
            const genders = params["genders"] || "";
            const colors = params["colors"] || "";
            const sizes = params["sizes"] || "";
            const materials = params["materials"] || "";

            this.currentFilteredListUrlQueryParams = `query=${search}&categories=${categories}&manufacturers=${manufacturers}&genders=${genders}&colors=${colors}&sizes=${sizes}&materials=${materials}`;

            // Check if all parameters are empty
            const allParamsEmpty =
                search === "" &&
                categories === "" &&
                manufacturers === "" &&
                genders === "" &&
                colors === "" &&
                sizes === "" &&
                materials === "";

            // Set to an empty state
            if (allParamsEmpty) {
                this.currentFilteredListUrlQueryParams = "";
            }
            // Do something with the query parameters
            console.log("Search Query:", search);
            console.log("Category:", categories);
            console.log("Manufacturers:", manufacturers);
            console.log("Genders:", genders);
            console.log("Colors:", colors);
            console.log("Sizes:", sizes);
            console.log("Materials:", materials);

            this.loadProducts();
        });
    }

    loadProducts(): void {
        // Subscribe to the resetPageNumberEvent
        this.pageNumberService.resetPageNumberEvent.subscribe(
            (pageNumber: number) => {
                // Update the pageNumber field on any filter update
                this.pageNumber = pageNumber;
                console.log(`resetting page number to ${pageNumber}`);
            }
        );

        // get products and their available colors using search filters
        this.productService
            .getAllClothingProductsAndColors(
                this.pageNumber,
                this.pageSize,
                this.currentFilteredListUrlQueryParams
            )
            .subscribe(this.processResult());
    }

    processResult(): any {
        return (data: any) => {
            // products data array
            this.productsOverviews = data.products;
            console.log(this.productsOverviews);

            // pagination data
            this.pageNumber = data.pageNumber + 1; // +1 since spring pagination is 0-based index
            this.pageSize = data.pageSize;
            this.totalElements = data.totalElements;
        };
    }

    handleChangeProductColorAndReloadDetails(
        productOverviewIndex: number,
        productName: string,
        colorId: number
    ): void {
        // Get the currently selected color's id for the given product overview
        const currentColorId =
            this.productsOverviews[productOverviewIndex].product.color.id;

        // invalidate request if color is already active
        if (currentColorId === colorId) {
            console.log("log: same color selected. invalid request");
            return;
        }

        this.productService
            .getProductDetailsByNameAndColorId(productName, colorId)
            .subscribe((newProduct) => {
                // assign the new product using the index position
                this.productsOverviews[productOverviewIndex].product =
                    newProduct;
            });
    }

    // page size update event change
    handleNewPageSizeEvent(e: PageSizeEvent): void {
        this.pageSize = e.size;
        this.pageNumber = e.page;
        console.log(`emitted pageSize ${e.size}, pageNumber ${e.page}`);
        this.loadProducts();
    }

    // pagination & sorting controls
    getPagesArray(): number[] {
        const pagesArray = [];
        for (let i = 2; i <= this.totalPages - 1; i++) {
            pagesArray.push(i);
        }
        return pagesArray;
    }

    goToPage(page: number): void {
        if (page >= 1 && page <= this.totalPages) {
            this.pageNumber = page;
            this.loadProducts();
        }
    }

    get totalPages(): number {
        return Math.ceil(this.totalElements / this.pageSize);
    }
}
