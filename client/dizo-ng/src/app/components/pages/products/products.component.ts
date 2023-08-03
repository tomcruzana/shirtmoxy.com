import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Product } from "app/models/product.model";
import { ProductService } from "app/services/product/product.service";

@Component({
    selector: "app-products",
    templateUrl: "./products.component.html",
    styleUrls: ["./products.component.scss"],
})
export class ProductsComponent implements OnInit {
    // product list properties
    products: Product[] = [];

    // the current product gender id
    currentGenderId: number;

    // the current product category id
    currentCategoryId: number;

    // the current product manufacturer id
    currentManufacturerId: number;

    constructor(
        private productService: ProductService,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        // on init, invoke this method
        // subscribe and react to changes in the route parameters of this component
        this.route.paramMap.subscribe(() => {
            this.loadProducts();
        });
    }

    loadProducts(): void {
        // check what type of id param is available
        const hasGenderId: boolean = this.route.snapshot.paramMap.has("gId");
        const hasCategoryId: boolean = this.route.snapshot.paramMap.has("cId");
        const hasManufacturerId: boolean =
            this.route.snapshot.paramMap.has("mId");

        // check if there's a category id
        if (hasCategoryId) {
            // convert id to number
            this.currentCategoryId = +this.route.snapshot.paramMap.get("cId")!;

            // get products by category id
            this.productService
                .getProductByCategoryId(this.currentCategoryId)
                .subscribe((data) => {
                    this.products = data;
                });

            return;
        } else if (hasGenderId) {
            // convert id to number
            this.currentGenderId = +this.route.snapshot.paramMap.get("gId")!;

            // get products by category id
            this.productService
                .getProductByGenderId(this.currentGenderId)
                .subscribe((data) => {
                    this.products = data;
                });

            return;
        } else if (hasManufacturerId) {
            // convert id to number
            this.currentManufacturerId = +this.route.snapshot.paramMap.get("mId")!;

            // get products by category id
            this.productService
                .getProductByManufacturerId(this.currentManufacturerId)
                .subscribe((data) => {
                    this.products = data;
                });

            return;
        }

        // get all products
        this.productService.getAllProducts().subscribe((data) => {
            this.products = data;
        });
    }
}
