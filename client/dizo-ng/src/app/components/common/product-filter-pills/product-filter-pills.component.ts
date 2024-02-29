import { Component } from "@angular/core";
import { ActivatedRoute, NavigationExtras, Router } from "@angular/router";
import { PageNumberService } from "app/services/page-number/page-number.service";

@Component({
    selector: "app-product-filter-pills",
    templateUrl: "./product-filter-pills.component.html",
    styleUrls: ["./product-filter-pills.component.scss"],
})
export class ProductFilterPillsComponent {
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private pageNumberService: PageNumberService
    ) {}

    // Product Filter pills
    filterPills: {
        search: string;
        category: string;
        colors: string | string[];
        genders: string | string[];
        manufacturers: string | string[];
        sizes: string | string[];
        materials: string | string[];
    } = {
        search: "",
        category: "",
        colors: [],
        genders: [],
        manufacturers: [],
        sizes: [],
        materials: [],
    };

    ngOnInit(): void {
        // extract query parameters from the URL then show as filter pills
        this.route.queryParams.subscribe((queryParams) => {
            this.filterPills.search = queryParams["query"] || "";

            this.filterPills.category = queryParams["category"] || "";

            this.filterPills.colors = queryParams["colors"]
                ? queryParams["colors"].split(",")
                : [];

            this.filterPills.genders = queryParams["genders"]
                ? queryParams["genders"].split(",")
                : [];

            this.filterPills.manufacturers = queryParams["manufacturers"]
                ? queryParams["manufacturers"].split(",")
                : [];

            this.filterPills.sizes = queryParams["sizes"]
                ? queryParams["sizes"].split(",")
                : [];

            this.filterPills.materials = queryParams["materials"]
                ? queryParams["materials"].split(",")
                : [];
        });
    }

    hasFilterValue(): boolean {
        if (
            this.filterPills.search ||
            (this.filterPills.colors && this.filterPills.colors.length) ||
            (this.filterPills.genders && this.filterPills.genders.length) ||
            (this.filterPills.manufacturers &&
                this.filterPills.manufacturers.length) ||
            (this.filterPills.sizes && this.filterPills.sizes.length) ||
            (this.filterPills.materials && this.filterPills.materials.length)
        ) {
            return true;
        }
        return false;
    }

    removeFromProductFilters(
        name: string,
        theFilter: string[] | string,
        paramName: string
    ): void {
        this.pageNumberService.emitResetPageNumberEvent(1);

        if (Array.isArray(theFilter)) {
            const index = theFilter.indexOf(name);

            if (index !== -1) {
                // If the name is already in the array, remove it
                theFilter.splice(index, 1);
            }
        } else if (typeof theFilter === "string") {
            // Handle the case when theFilter is a string (categoryFilter)
            theFilter = "";
        }

        // Construct the updated query parameters
        const queryParams: NavigationExtras = {
            relativeTo: this.route,
            queryParams: {
                [paramName]: Array.isArray(theFilter)
                    ? theFilter.join(",")
                    : theFilter,
            },
            queryParamsHandling: "merge", // Merge the new parameters with existing ones
        };

        // Navigate to the updated URL with the new query parameters
        this.router.navigate([], queryParams);
    }

    clearAllFiltersExceptCategory(): void {
        this.pageNumberService.emitResetPageNumberEvent(1);

        // Clear all filters except for 'category'
        this.filterPills.search = "";
        this.filterPills.colors = [];
        this.filterPills.genders = [];
        this.filterPills.manufacturers = [];
        this.filterPills.sizes = [];
        this.filterPills.materials = [];

        // Construct the updated query parameters
        const queryParams: NavigationExtras = {
            relativeTo: this.route,
            queryParams: {
                search: "",
                colors: "",
                genders: "",
                manufacturers: "",
                sizes: "",
                materials: "",
            },
            queryParamsHandling: "merge", // Merge the new parameters with existing ones
        };

        // Navigate to the updated URL with the new query parameters
        this.router.navigate([], queryParams);
    }
}
