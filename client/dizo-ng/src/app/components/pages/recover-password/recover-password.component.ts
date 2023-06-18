import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-recover-password",
    templateUrl: "./recover-password.component.html",
    styleUrls: ["./recover-password.component.scss"],
})
export class RecoverPasswordComponent implements OnInit {
    form = {
        email: "",
    };

    constructor() {}

    ngOnInit(): void {}

    onSubmit(): void {
        // TODO : implement this on using a POST service
        console.log(JSON.stringify(this.form, null, 2));
    }
}
