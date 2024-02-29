import { Component, OnInit } from "@angular/core";
import { CartItem } from "app/models/cart-item.model";
import { CartService } from "app/services/cart/cart.service";
import Swal from "sweetalert2";

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

    updateQuantity(item: CartItem, updatedQty: string): void {
        this.cartService.updateQuantity(item, updatedQty);
    }

    remove(item: CartItem): void {
        Swal.fire({
            title: "Confirmation",
            text: "Are you sure you want to remove this item from your cart?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            confirmButtonText: "Remove",
        }).then((result) => {
            if (result.isConfirmed) {
                this.cartService.remove(item);
            }
        });
    }

    removeAll(): void {
        Swal.fire({
            title: "Confirmation",
            text: "Are you sure you want to remove all items from your cart?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            confirmButtonText: "Remove All",
        }).then((result) => {
            if (result.isConfirmed) {
                this.cartService.removeAll();
            }
        });
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
