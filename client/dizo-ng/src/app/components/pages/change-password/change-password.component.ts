import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-change-password",
    templateUrl: "./change-password.component.html",
    styleUrls: ["./change-password.component.scss"],
})
export class ChangePasswordComponent implements OnInit {
    inputFieldTypes: [boolean, boolean, boolean];

    form = {
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
    };

    constructor() {}

    ngOnInit(): void {
        this.inputFieldTypes = [false, false, false];
    }

    onSubmit(): void {
        // TODO : implement this on using a POST service
        console.log(JSON.stringify(this.form, null, 2));
    }

    // resetForm(form: NgForm): void {
    //     form.reset();
    // }

    toggleInputFieldType(i: number) {
        if (i == 0 || i == 1 || i == 2)
            this.inputFieldTypes[i] = !this.inputFieldTypes[i];
        else console.log("An error occured.");
    }
}
