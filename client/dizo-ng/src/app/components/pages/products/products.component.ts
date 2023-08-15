import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { PageSizeEvent } from "app/models/interfaces/page-size-event";
import { Product } from "app/models/product.model";
import { ProductService } from "app/services/product/product.service";

@Component({
    selector: "app-products",
    templateUrl: "./products.component.html",
    styleUrls: ["./products.component.scss"],
})
export class ProductsComponent implements OnInit {
    // Product list properties
    products: Product[] = [];

    // Search mode toggle property
    searchMode: boolean = false;
    previousQuery: string = "";

    // Search filters id properties (default is 1)
    currentGenderId: number = 1;
    previousGenderId: number = 1;

    currentCategoryId: number = 1;
    previousCategoryId: number = 1;

    currentManufacturerId: number = 1;
    previousManufacturerId: number = 1;

    // Pagination properties defaults
    pageNumber: number = 1;
    pageSize: number = 10;
    totalElements: number = 0;

    constructor(
        private productService: ProductService,
        private route: ActivatedRoute
    ) {}

    /**
     * Lifecycle hook called when the component is initialized.
     * Subscribes to changes in the route parameters and loads the products accordingly.
     * This method is invoked when the component is created.
     *
     * @returns {void}
     */
    ngOnInit(): void {
        // On initialization, invoke this method whenever the route parameters change.
        // This ensures that the products are reloaded whenever the URL parameters change.
        this.route.paramMap.subscribe(() => {
            this.loadProducts();
        });
    }

    /**
     * Loads products based on the query parameters in the URL.
     * If the "query" query parameter is present, it turns on search mode.
     * If in search mode, it calls the search products handler, otherwise, it loads all products.
     *
     * @returns {void}
     */
    loadProducts(): void {
        // Check if the "query" parameter is available to determine search mode.
        this.searchMode = this.route.snapshot.paramMap.has("query");
        console.log(`searchMode= ${this.searchMode}`);

        // If in search mode, call the search products handler and return.
        if (this.searchMode) {
            this.handleSearchProducts();
            return;
        }

        // If not in search mode, load all products.
        this.handleLoadProducts();
    }

    handleSearchProducts(): void {
        // copy the valiu of the query param
        const theQuery: string = this.route.snapshot.paramMap.get("query")!;

        // if we have a different query than previous
        // then set page number to 1
        if (this.previousQuery != theQuery) {
            this.pageNumber = 1;
        }

        this.previousQuery = theQuery;

        // invoke http service & perform a product search using the theQuery string
        this.productService
            .searchProductsPaginate(theQuery, this.pageNumber, this.pageSize)
            .subscribe(this.processResult());

        return;
    }

    handleLoadProducts(): void {
        // check what type of id param is available
        const hasGenderId: boolean = this.hasQueryParam(this.route, "gId");
        const hasCategoryId: boolean = this.hasQueryParam(this.route, "cId");
        const hasManufacturerId: boolean = this.hasQueryParam(
            this.route,
            "mId"
        );

        // check if there's a category id
        if (hasCategoryId) {
            // convert id to number using the + operator
            this.currentCategoryId = +this.route.snapshot.paramMap.get("cId")!;

            // category filter id change validation
            this.pageNumber = this.resetPageNumberIfFilterIdChanged(
                this.currentCategoryId,
                this.previousCategoryId,
                this.pageNumber
            );
            this.previousCategoryId = this.currentCategoryId;

            // get products by category id
            this.productService
                .getProductByCategoryId(
                    this.currentCategoryId,
                    this.pageNumber,
                    this.pageSize
                )
                .subscribe(this.processResult());

            return;
        } else if (hasGenderId) {
            // convert id to number
            this.currentGenderId = +this.route.snapshot.paramMap.get("gId")!;

            // gender filter id change validation
            this.pageNumber = this.resetPageNumberIfFilterIdChanged(
                this.currentGenderId,
                this.previousGenderId,
                this.pageNumber
            );
            this.previousGenderId = this.currentGenderId;

            // get products by category id
            this.productService
                .getProductByGenderIdPaginate(
                    this.currentGenderId,
                    this.pageNumber,
                    this.pageSize
                )
                .subscribe(this.processResult());

            return;
        } else if (hasManufacturerId) {
            // convert id to number
            this.currentManufacturerId =
                +this.route.snapshot.paramMap.get("mId")!;

            // manufacturer filter id change validation
            this.pageNumber = this.resetPageNumberIfFilterIdChanged(
                this.currentManufacturerId,
                this.previousManufacturerId,
                this.pageNumber
            );
            this.previousManufacturerId = this.currentManufacturerId;

            // get products by category id
            this.productService
                .getProductByManufacturerIdPaginate(
                    this.currentManufacturerId,
                    this.pageNumber,
                    this.pageSize
                )
                .subscribe(this.processResult());

            return;
        }

        // get all products
        this.productService
            .getAllProductsPaginate(this.pageNumber, this.pageSize)
            .subscribe(this.processResult());
    }

    /**
     * Checks if the specified query parameter is present in the URL's query parameters.
     *
     * @param route The Angular ActivatedRoute instance representing the current route.
     * @param paramName The name of the query parameter to check for.
     * @returns {boolean} True if the query parameter is present, false otherwise.
     */
    hasQueryParam(route: ActivatedRoute, paramName: string): boolean {
        // Use the 'has' method of 'paramMap' to check if the query parameter exists.
        return route.snapshot.paramMap.has(paramName);
    }

    /**
     * Resets the page number to 1 if the current filter ID is different from the previous one.
     *
     * @param currentId The current filter ID to compare.
     * @param previousId The previous filter ID to compare against.
     * @param pageNumber The current page number to reset if the filter ID changed.
     * @returns The updated page number; 1 if the filter ID is new, otherwise the original page number.
     */
    resetPageNumberIfFilterIdChanged(
        currentId: number,
        previousId: number,
        pageNumber: number
    ): number {
        if (currentId !== previousId) {
            return 1; // Reset the page number to 1 if the current filter ID is new.
        }
        return pageNumber; // Otherwise, keep the current page number.
    }

    processResult() {
        return (data: any) => {
            // products data
            this.products = data.content;

            // pagination data
            this.pageNumber = data.pageable.pageNumber + 1; // +1 since spring pagination is 0-based index
            this.pageSize = data.pageable.pageSize;
            this.totalElements = data.totalElements;
        };
    }

    // page size update event change
    handleNewPageSizeEvent(e: PageSizeEvent) {
        this.pageSize = e.size;
        this.pageNumber = e.page;
        console.log(`emitted pageSize ${e.size}, pageNumber ${e.page}`);
        this.loadProducts();
    }

    // pagination & sorting
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
