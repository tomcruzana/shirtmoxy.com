import { Component, OnInit } from "@angular/core";
import { OrderHistory } from "app/models/order-history.model";
import { OrderHistoryService } from "app/services/order/order-history.service";

@Component({
    selector: "app-orders",
    templateUrl: "./orders.component.html",
    styleUrls: ["./orders.component.scss"],
})
export class OrdersComponent implements OnInit {
    orderHistoryList: OrderHistory[] = [];
    storage: Storage = sessionStorage;

    constructor(private orderHistoryService: OrderHistoryService) {}

    ngOnInit(): void {
        this.loadOrderHistory();
    }

    loadOrderHistory() {
        // read the user's email address from the browser storage
        // const theEmail = JSON.parse(this.storage.getItem("userEmail"));

        // Temp code
        const theEmail = "test_user@shirtmoxy.com";

        // retrieve data from the service
        this.orderHistoryService
            .getAllOrdersByCustomerEmailPaginate(theEmail, 1, 10)
            .subscribe((data) => {
                this.orderHistoryList = data.content;
            });
    }
}
