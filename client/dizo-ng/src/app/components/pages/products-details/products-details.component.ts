import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CartItem } from "app/models/cart-item.model";
import { ProductColor } from "app/models/product-color.model";
import { ProductSize } from "app/models/product-size";
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

    productColors: ProductColor[] = [];
    activeColorName: string | null = null; // Initialize as null to start with no active color

    productSizes: ProductSize[] = [];
    inStockSizes: ProductSize[] = [];
    outOfStockSizes: ProductSize[] = [];

    // set min & max qty (This should come from the current available stock in the backend inventory)
    readonly MAX_STOCK_QTY: number = 999;
    readonly MIN_STOCK_QTY: number = 1;
    quantity: number = 1;

    constructor(
        private productService: ProductService,
        private cartService: CartService,
        private router: Router,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.route.paramMap.subscribe(() => {
            this.handleLoadProductDetails();
        });
    }

    handleLoadProductDetails(): void {
        const theProductSku: string = this.route.snapshot.paramMap.get("sku")!;

        this.productService
            .getProductDetailsAndColorsAndSizes(theProductSku)
            .subscribe(
                (data) => {
                    this.p = data.product;
                    this.productColors = data.colors;
                    this.productSizes = data.sizes;
                    this.inStockSizes = data.inStockSizes;
                    this.outOfStockSizes = data.outOfStockSizes;
                    this.activeColorName = data.product.color.name;
                    console.log(this.p);
                },
                (error) => {
                    this.router.navigate(["/error"]);
                }
            );
    }

    scrollTo(el: HTMLElement) {
        el.scrollIntoView({
            behavior: "smooth",
            block: "center", // This will center the element within the viewport vertically
            inline: "center", // This will center the element within the viewport horizontally
        });
    }

    handleChangeProductColorAndReloadDetails(colorId: number): void {
        // invalidate request if color is already active
        if (colorId == this.p.color.id) {
            return;
        }

        this.productService
            .getProductSkuByNameAndColorId(this.p.name, colorId)
            .subscribe((product) => {
                console.log(product);

                // Update the URL with the new product id
                this.router.navigate(["product-details", product.sku]);
            });
    }

    incrementQuantity(): void {
        if (+this.quantity !== this.MAX_STOCK_QTY) {
            this.quantity = +this.quantity + 1;
        }
        // this.cartService.addToCart(item);
    }

    decrementQuantity(): void {
        if (+this.quantity > this.MIN_STOCK_QTY) {
            this.quantity = +this.quantity - 1;
        }
        // this.cartService.decrementQuantity(item);
    }

    // TO-DO: remove
    addToCart(theProduct: Product): void {
        console.log(
            `adding to cart: ${theProduct.name} - ${theProduct.unitPrice}`
        );
        const theCartItem = new CartItem(theProduct);

        // update the quantity
        theCartItem.quantity = this.quantity;
        console.log(`item added to cart:  ${JSON.stringify(theCartItem)}`);

        this.cartService.addToCart(theCartItem);
    }
}
