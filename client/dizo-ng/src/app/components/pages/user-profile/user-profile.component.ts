import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-user-profile",
    templateUrl: "./user-profile.component.html",
    styleUrls: ["./user-profile.component.scss"],
})
export class UserProfileComponent implements OnInit {
    // TODO: check https://www.youtube.com/watch?v=5yf29GZ9Dac
    states = [
        { id: "dc", name: "dc" },
        { id: "md", name: "md" },
        { id: "va", name: "va" },
    ];

    form = {
        fname: "",
        lname: "",
        email: "",
        company: "",
        countryCode: "",
        phoneNumber: "",
        receiveEmail: false,
        receiveText: false,
        shippingAddress: {
            addressLine1: "",
            addressLine2: "",
            city: "",
            stateInput: "",
            zipCode: "",
            country: "",
        },
    };

    ngOnInit(): void {
        this.form.countryCode = "+1";
        this.form.shippingAddress.country = "USA";
    }

    onSubmit(): void {
        // TODO : implement this on using a POST service
        console.log(JSON.stringify(this.form, null, 2));
    }

    // resetForm(form: NgForm): void {
    //     form.reset();
    // }
}
