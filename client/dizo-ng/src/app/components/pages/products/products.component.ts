import { Component, OnInit } from "@angular/core";
import { Product } from "app/models/product.model";
import { ProductService } from "app/services/product/product.service";

@Component({
    selector: "app-products",
    templateUrl: "./products.component.html",
    styleUrls: ["./products.component.scss"],
})
export class ProductsComponent implements OnInit {
    // search filter properties
    isExpanded: boolean = true;

    // product list properties
    products: Product[] = [];

    constructor(private productService: ProductService) {}

    ngOnInit(): void {
        // on init, invoke this method
        this.listProducts();
    }

    toggleSearchFilterCollapse() {
        this.isExpanded = !this.isExpanded;
    }

    listProducts() {
        this.productService.getAllProducts().subscribe((data) => {
            this.products = data;
        });
    }
}
