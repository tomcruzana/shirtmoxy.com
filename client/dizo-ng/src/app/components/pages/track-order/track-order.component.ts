import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-track-order",
    templateUrl: "./track-order.component.html",
    styleUrls: ["./track-order.component.scss"],
})
export class TrackOrderComponent implements OnInit {
    formSubmitted: boolean;

    form = {
        orderNumber: "",
    };

    //** dummy code **//

    // scenario 1 - with 'invalid order #' shows order not found
    orderInfo = {
        isValid: false,
        number: "",
        expectedArrival: {
            date: "",
        },
        courierInfo: {
            link: "",
            trackingNumber: "",
        },
        status: -1,
        notes: "",
    };

    // scenario 1 - with 'valid order # but no progress yet'
    // orderInfo = {
    //     isValid: true,
    //     number: "00000001",
    //     expectedArrival: {
    //         date: "01/01/24",
    //     },
    //     courierInfo: {
    //         link: "",
    //         trackingNumber: "",
    //     },
    //     status: -1, // we use -1
    //     notes: "",
    // };

    // scenario 2 - with 'valid order #'
    // orderInfo = {
    //     isValid: true,
    //     number: "00000001",
    //     expectedArrival: {
    //         date: "01/01/24",
    //     },
    //     courierInfo: {
    //         link: "",
    //         trackingNumber: "",
    //     },
    //     status: 1,
    //     notes: "",
    // };

    // // scenario 3 - with custom notes
    // orderInfo = {
    //     isValid: true,
    //     number: "00000001",
    //     expectedArrival: {
    //         date: "01/01/24",
    //     },
    //     courierInfo: {
    //         link: "",
    //         trackingNumber: "",
    //     },
    //     status: 0,
    //     notes: "Dear Customer, You are amazing!",
    // };

    ngOnInit(): void {}

    onSubmit() {
        // TODO: Perform form submission logic
        // ...

        this.formSubmitted = true;
    }
}
