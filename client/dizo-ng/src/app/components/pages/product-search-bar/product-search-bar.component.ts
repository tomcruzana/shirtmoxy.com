import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: "app-product-search-bar",
    templateUrl: "./product-search-bar.component.html",
    styleUrls: ["./product-search-bar.component.scss"],
})
export class ProductSearchBarComponent implements OnInit {
    constructor(private router: Router) {}

    ngOnInit(): void {}

    doProductSearch(value: string): void {
        this.router.navigateByUrl(`product/search/${value}`);
    }
}
