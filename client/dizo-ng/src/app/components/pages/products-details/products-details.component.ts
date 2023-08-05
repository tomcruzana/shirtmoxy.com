import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Product } from "app/models/product.model";
import { ProductService } from "app/services/product/product.service";

@Component({
    selector: "app-products-details",
    templateUrl: "./products-details.component.html",
    styleUrls: ["./products-details.component.scss"],
})
export class ProductsDetailsComponent implements OnInit {
    p!: Product;

    constructor(
        private productService: ProductService,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.route.paramMap.subscribe(() => {
            this.handleLoadProductDetails();
        });
    }
    handleLoadProductDetails() {
        const theProductId: number = +this.route.snapshot.paramMap.get("id")!;

        this.productService
            .getProductDetailsById(theProductId)
            .subscribe((data) => {
                this.p = data;
                console.log(data);
            });
    }
}
