import { Component, OnInit } from "@angular/core";
import { CartService } from "app/services/cart/cart.service";

@Component({
    selector: "app-cart",
    templateUrl: "./cart.component.html",
    styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
    totalPrice: number = 0.0;
    totalDiscount: number = 0.0;

    isCouponValid: boolean = false;
    isCartEmpty: boolean;
    hidePromotion: boolean;
    readonly MAX_QTY: number = 999;
    readonly MIN_QTY: number = 0;

    constructor(private cartService: CartService) {}

    ngOnInit(): void {
        this.updateTotalPrice();

        this.isCartEmpty = false;
        this.hidePromotion = false;
    }

    updateTotalPrice(): void {
        // subscribe to the cart totalPrice subject
        this.cartService.totalPrice.subscribe(
            (data) => (this.totalPrice = data)
        );
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
