import { Component, OnInit } from "@angular/core";
import { CartItem } from "app/models/cart-item.model";
import { CartService } from "app/services/cart/cart.service";

@Component({
    selector: "app-cart",
    templateUrl: "./cart.component.html",
    styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
    cartItems: CartItem[] = [];
    totalPrice: number = 0;
    totalQuantity: number = 0;
    totalDiscount: number = 0.0;

    isCouponValid: boolean = false;
    hidePromotion: boolean;
    readonly MAX_QTY: number = 999;
    readonly MIN_QTY: number = 0;

    constructor(private cartService: CartService) {}

    ngOnInit(): void {
        this.loadCartDetails();

        this.hidePromotion = false;
    }

    loadCartDetails(): void {
        // get a handle to the cart items
        this.cartItems = this.cartService.cartItems;

        // subscribe to the cart totalPrice subject
        this.cartService.totalPrice.subscribe(
            (data) => (this.totalPrice = data)
        );

        // subscribe to the cart totalQunatity subject
        this.cartService.totalQuantity.subscribe(
            (data) => (this.totalQuantity = data)
        );

        // compute cart total price
        this.cartService.computeCartTotals();
    }

    incrementQuantity(item: CartItem): void {
        this.cartService.addToCart(item);
    }

    decrementQuantity(item: CartItem): void {
        this.cartService.decrementQuantity(item);
    }

    remove(item: CartItem): void {
        this.cartService.remove(item);
    }

    removeAll(): void {
        this.cartService.removeAll();
    }

    toggleHidePromotion(): void {
        this.hidePromotion = !this.hidePromotion;
        alert(this.hidePromotion);
    }

    processCoupon(couponCode: string): void {
        this.isCouponValid = true;
        // @TODO temp impl
    }
}
