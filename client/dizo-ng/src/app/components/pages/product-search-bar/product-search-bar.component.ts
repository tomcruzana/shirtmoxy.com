import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { PageNumberService } from "app/services/page-number/page-number.service";
import { ProductFilterService } from "app/services/product-filter/product-filter.service";

@Component({
    selector: "app-product-search-bar",
    templateUrl: "./product-search-bar.component.html",
    styleUrls: ["./product-search-bar.component.scss"],
})
export class ProductSearchBarComponent implements OnInit {
    searchForm: FormGroup;
    previousQuery: string = "";

    constructor(
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private productFilterService: ProductFilterService,
        private pageNumberService: PageNumberService
    ) {}

    ngOnInit(): void {
        this.route.queryParams.subscribe((searchQueryParams) => {
            // copy the value of the query param
            this.previousQuery = searchQueryParams["query"] || "";

            // Initialize the form with the previous query
            this.searchForm = this.formBuilder.group({
                query: [this.previousQuery, Validators.required],
            });
        });
    }

    isNewQuery(theQuery: string): boolean {
        if (this.previousQuery !== theQuery) {
            console.log("The query is new");

            this.previousQuery = theQuery;
            return true;
        }

        console.log("The query is the same");
        return false;
    }

    doProductSearch(): void {
        const query = String(this.searchForm.get("query").value).trim();
        console.log(`q = ${query}`);

        if (this.isNewQuery(query)) {
            // Reset page number to
            this.pageNumberService.emitResetPageNumberEvent(1);

            this.productFilterService.addOrRemove(
                query,
                this.previousQuery,
                "query"
            );
        }
    }
}
