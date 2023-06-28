import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-contact",
    templateUrl: "./contact.component.html",
    styleUrls: ["./contact.component.scss"],
})
export class ContactComponent implements OnInit {
    form = {
        fname: "",
        lname: "",
        email: "",
        phoneNumber: "",
        subject: "",
        category: "",
        message: "",
        agreeTerms: false,
    };

    constructor() {}

    ngOnInit(): void {}

    onSubmit(): void {
        // set the eula - terms agreement to true upon submit
        this.form.agreeTerms = true;

        // TODO : implement this on using a POST service
        console.log(JSON.stringify(this.form, null, 2));
    }

    // resetForm(form: NgForm): void {
    //     form.reset();
    // }
}
