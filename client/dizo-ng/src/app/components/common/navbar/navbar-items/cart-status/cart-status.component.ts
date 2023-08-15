import { Component, OnInit } from "@angular/core";
import { CartService } from "app/services/cart/cart.service";

@Component({
    selector: "app-cart-status",
    templateUrl: "./cart-status.component.html",
    styleUrls: ["./cart-status.component.scss"],
})
export class CartStatusComponent implements OnInit {
    totalQuantity: number = 0;

    constructor(private cartService: CartService) {}

    ngOnInit(): void {
        this.updateCartTotalQuantity();
    }

    updateCartTotalQuantity() {
        // subscribe to the cart totalQuantity subject
        this.cartService.totalQuantity.subscribe(
            (data) => (this.totalQuantity = data)
        );
    }
}
