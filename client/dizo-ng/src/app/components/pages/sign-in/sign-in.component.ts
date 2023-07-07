import { Component, OnInit } from "@angular/core";
import { User } from "../../../models/user.model";
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import { SigninService } from "app/services/signin/signin.service";
import { getCookie } from "typescript-cookie";
import { environment } from "../../../../environments/environment.development";

@Component({
    selector: "app-sign-in",
    templateUrl: "./sign-in.component.html",
    styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent implements OnInit {
    inputFieldType: boolean;
    authStatus: string = "";
    model = new User();

    // dummy form obj
    form = {
        email: "",
        password: "",
        rememberMe: false,
    };

    constructor(private signinService: SigninService, private router: Router) {}

    ngOnInit(): void {}

    toggleInputFieldType() {
        this.inputFieldType = !this.inputFieldType;
    }

    onSignIn(): void {
        // TODO : implement this on using a POST service
        console.log(JSON.stringify(this.form, null, 2));

        this.validateUser();
    }

    // resetForm(form: NgForm): void {
    //     form.reset();
    // }

    validateUser() {
        this.signinService
            .validateLoginDetails(this.model)
            .subscribe((responseData) => {
                window.sessionStorage.setItem(
                    "Authorization",
                    responseData.headers.get("Authorization")!
                );
                this.model = <any>responseData.body;
                this.model.authStatus = "AUTH";
                window.sessionStorage.setItem(
                    "userdetails",
                    JSON.stringify(this.model)
                );
                let xsrf = getCookie("XSRF-TOKEN")!;
                window.sessionStorage.setItem("XSRF-TOKEN", xsrf);
                this.router.navigate(["user/projects"]);
            });
    }
}
