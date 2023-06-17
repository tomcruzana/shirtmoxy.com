import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-sign-up",
    templateUrl: "./sign-up.component.html",
    styleUrls: ["./sign-up.component.scss"],
})
export class SignUpComponent {
    inputFieldType: boolean;

    form = {
        fname: "",
        lname: "",
        email: "",
        company: "",
        password: "",
        confirmPassword: "",
        agreeTerms: false,
    };

    onSubmit(): void {
        // set the eula - terms agreement to true upon submit
        this.form.agreeTerms = true;

        // TODO : implement this on using a POST service
        console.log(JSON.stringify(this.form, null, 2));
    }

    // resetForm(form: NgForm): void {
    //     form.reset();
    // }

    toggleInputFieldType() {
        this.inputFieldType = !this.inputFieldType;
    }
}
