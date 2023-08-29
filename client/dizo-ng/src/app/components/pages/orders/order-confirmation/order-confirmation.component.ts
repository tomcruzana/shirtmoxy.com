import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: "app-order-confirmation",
    templateUrl: "./order-confirmation.component.html",
    styleUrls: ["./order-confirmation.component.scss"],
})
export class OrderConfirmationComponent implements OnInit {
    orderTrackingNumber: string;

    constructor(private route: ActivatedRoute, private router: Router) {}

    ngOnInit() {
        this.route.queryParams.subscribe((params) => {
            this.orderTrackingNumber = params["orderTrackingNumber"];

            // Check if the orderTrackingNumber is missing
            if (!this.orderTrackingNumber) {
                // Navigate back to the home route or any other desired route
                this.router.navigate(["/home"]);
            }
        });
    }
}
