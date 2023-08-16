import { Injectable } from "@angular/core";
import { CartItem } from "app/models/cart-item.model";
import { Subject } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class CartService {
    cartItems: CartItem[] = [];

    totalPrice: Subject<number> = new Subject<number>();
    totalQuantity: Subject<number> = new Subject<number>();

    constructor() {}

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
        } else {
            // just add the item to the cart
            this.cartItems.push(theCartItem);
        }

        // comput the new cart items total price & qty
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
        this.totalPrice.next(totalPriceValue);
        this.totalQuantity.next(totalQuantityValue);

        // temp log cart data method call
        this.logCartData(totalPriceValue, totalQuantityValue);
    }

    // decrement item qty
    decrementQuantity(item: CartItem): void {
        item.quantity--;

        if (item.quantity === 0) {
            this.remove(item);
        } else {
            this.computeCartTotals();
        }
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

    // temp method
    logCartData(tpv: number, tqv: number): void {
        console.log("contents of the cart");
        for (let tempCartItem of this.cartItems) {
            const subTotalPrice =
                tempCartItem.quantity * tempCartItem.unitPrice;

            console.log(
                `name: ${tempCartItem.name}, qty=${tempCartItem.quantity}, unitPrice=${tempCartItem.unitPrice}, subTotalPrice=${subTotalPrice} `
            );
        }

        console.log(`totalPrice: ${tpv.toFixed(2)}, totalQty:${tqv}`);
        console.log("-------------------");
    }
}
