import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";

@Component({
    selector: "app-sign-in",
    templateUrl: "./sign-in.component.html",
    styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent {
    inputFieldType: boolean;

    form = {
        email: "",
        password: "",
        rememberMe: false,
    };

    onSubmit(): void {
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
