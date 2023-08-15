import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { CartItem } from "app/models/cart-item.model";
import { Product } from "app/models/product.model";
import { CartService } from "app/services/cart/cart.service";
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
        private cartService: CartService,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.route.paramMap.subscribe(() => {
            this.handleLoadProductDetails();
        });
    }
    handleLoadProductDetails(): void {
        const theProductId: number = +this.route.snapshot.paramMap.get("id")!;

        this.productService
            .getProductDetailsById(theProductId)
            .subscribe((data) => {
                this.p = data;
                console.log(data);
            });
    }

    addToCart(theProduct: Product): void {
        console.log(
            `adding to cart: ${theProduct.name} - ${theProduct.unitPrice}`
        );

        // @TODO
        const theCartItem = new CartItem(theProduct);

        this.cartService.addToCart(theCartItem);
    }
}
