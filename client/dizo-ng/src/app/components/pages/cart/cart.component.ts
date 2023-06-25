import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-cart",
    templateUrl: "./cart.component.html",
    styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
    isCartEmpty: boolean;
    hidePromotion: boolean;
    readonly MAX_QTY: number = 999;
    readonly MIN_QTY: number = 0;

    // dummy objects
    cart: any[];

    constructor() {
        // populate with dummy data
        // populate with dummy data
        this.cart = [
            {
                subTotal: 100.0,
                shipping: 0,
                total: 0.0,
            },
            [
                {
                    id: 1,
                    productImg: "assets/img/products/products1.jpg",
                    name: "Lorem Ipsum",
                    unitPrice: 35.0,
                    qty: 0,
                    total: 0.0,
                },
                {
                    id: 2,
                    productImg: "assets/img/products/products1.jpg",
                    name: "Lorem Ipsum Amet Cavet",
                    unitPrice: 315.0,
                    qty: 12,
                    total: 0.0,
                },
                {
                    id: 3,
                    productImg: "assets/img/products/products1.jpg",
                    name: "Lorem Ipsum Dolor",
                    unitPrice: 35.0,
                    qty: 997,
                    total: 0.0,
                },
                {
                    id: 4,
                    productImg: "assets/img/products/products1.jpg",
                    name: "Lorem Ipsum Dolor",
                    unitPrice: 35.0,
                    qty: 5,
                    total: 0.0,
                },
            ],
        ];
    }

    ngOnInit(): void {
        this.isCartEmpty = false;
        this.hidePromotion = false;
    }

    updateProductItemQty(operation: string): void {
        let cartItems = this.cart[1];
        let currentQty = cartItems[0].qty;

        // validate arithmetic operation and qty limits
        if (operation == "+" && currentQty < this.MAX_QTY) {
            // increment & update qty
            cartItems[0].qty = currentQty += 1;

            // calculate new item total
            this.calcItemTotalPrice(cartItems, currentQty);
        } else if (operation == "-" && currentQty > this.MIN_QTY) {
            // decrement & update qty
            cartItems[0].qty = currentQty -= 1;

            // calculate new item total
            this.calcItemTotalPrice(cartItems, currentQty);
        } else {
            return;
        }
    }

    calcItemTotalPrice(cartItems: any, qty: number): void {
        // calculate and update product item total
        let newItemTotal = cartItems[0].unitPrice * qty;
        console.log(newItemTotal);
        cartItems.total = newItemTotal;
    }

    calcCartTotals(): void {}

    toggleHidePromotion(): void {
        this.hidePromotion = !this.hidePromotion;
        alert(this.hidePromotion);
    }
}
