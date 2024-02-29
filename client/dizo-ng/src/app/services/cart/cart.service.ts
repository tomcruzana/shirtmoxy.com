import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { CartItem } from "app/models/cart-item.model";
import { Subject, BehaviorSubject } from "rxjs";
import Swal from "sweetalert2";

@Injectable({
    providedIn: "root",
})
export class CartService {
    cartItems: CartItem[] = [];

    // maximum cart capacity
    readonly MAX_CART_CAPACITY: number = 5;

    // set min & max qty (This should come from the current available stock in the backend inventory)
    readonly MAX_STOCK_QTY: number = 999;
    readonly MIN_STOCK_QTY: number = 1;

    totalPrice: Subject<number> = new BehaviorSubject<number>(0);
    totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

    storage: Storage = sessionStorage;
    // storage: Storage = localStorage;

    constructor(private router: Router) {}

    persistCartItems() {
        this.storage.setItem("cartItems", JSON.stringify(this.cartItems));
    }

    addToCart(theCartItem: CartItem): void {
        // check if item already exists in the cart
        let alreadyExistsInCart: boolean = false;
        let existingCartItem: CartItem = undefined;

        if (this.cartItems.length > 0) {
            // find the item in the cart based on item id
            existingCartItem = this.cartItems.find(
                (tempCartItem) => tempCartItem.id === theCartItem.id
            );

            // check if we found it
            alreadyExistsInCart = existingCartItem != undefined;
        }

        if (alreadyExistsInCart) {
            // increment the qty
            existingCartItem.quantity++;

            if (existingCartItem.quantity > this.MAX_STOCK_QTY) {
                existingCartItem.quantity = this.MAX_STOCK_QTY;
            }
        } else {
            // check if cart is full
            if (this.cartItems.length >= this.MAX_CART_CAPACITY) {
                Swal.fire({
                    title: "Oops! Your Cart is Full",
                    text: "To continue shopping, remove items or proceed to checkout.",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#e61c5d",
                    confirmButtonText: `<p class="align-middle m-0 text-light"><i class="bx bx-cart bx-sm align-middle"></i> Go to Cart</p>`,
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.router.navigate(["/cart"]);
                    }
                });
            } else {
                // add the new item into the cart
                this.cartItems.push(theCartItem);
            }
        }

        // compute the new cart items total price & qty
        this.computeCartTotals();

        console.log(`cart length : ${this.cartItems.length}`);
    }

    // decrement item qty
    decrementQuantity(item: CartItem): void {
        item.quantity--;

        if (item.quantity <= 0) {
            // set to min qty
            item.quantity = this.MIN_STOCK_QTY;
        } else {
            this.computeCartTotals();
        }
    }

    updateQuantity(item: CartItem, updatedQty: string): void {
        // convert to numeric type
        item.quantity = +updatedQty;

        if (item.quantity <= 0) {
            // set to min qty
            item.quantity = this.MIN_STOCK_QTY;
        }

        this.computeCartTotals();
    }

    remove(item: CartItem): void {
        // get the index of the item in the array
        const itemIndex = this.cartItems.findIndex(
            (cartItem) => cartItem.id === item.id
        );

        // if found, remove the item from the array at the given index
        if (itemIndex > -1) {
            this.cartItems.splice(itemIndex, 1);

            this.computeCartTotals();
        }
    }

    removeAll(): void {
        // clear cart items array
        this.cartItems.splice(0);
        this.computeCartTotals();
    }

    computeCartTotals(): void {
        let totalPriceValue: number = 0;
        let totalQuantityValue: number = 0;

        // sum up the new price & qty values
        for (let currentCartItem of this.cartItems) {
            totalPriceValue +=
                currentCartItem.quantity * currentCartItem.unitPrice;

            totalQuantityValue += currentCartItem.quantity;
        }

        // publish the new values & all subscribers to these subject will receive the new data
        // original  this.totalPrice.next(totalPriceValue);
        this.totalPrice.next(parseFloat(totalPriceValue.toFixed(2)));
        this.totalQuantity.next(totalQuantityValue);

        // temp log cart data method call
        this.logCartData(totalPriceValue, totalQuantityValue);

        // persist cart data
        this.persistCartItems();
    }

    // temp method
    logCartData(tpv: number, tqv: number): void {
        console.log("contents of the cart");
        for (let tempCartItem of this.cartItems) {
            const subTotalPrice =
                tempCartItem.quantity * tempCartItem.unitPrice;

            console.log(
                `name: ${tempCartItem.name}, qty=${tempCartItem.quantity}, unitPrice=${tempCartItem.unitPrice}, subTotalPrice=${subTotalPrice} `
            );
            console.log(
                `subTotalPrice=${parseFloat(subTotalPrice.toFixed(2))} `
            );
        }

        console.log(`totalPrice: ${tpv.toFixed(2)}, totalQty:${tqv}`);
        console.log("-------------------");
    }
}
