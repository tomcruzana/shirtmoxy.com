import { Component, OnInit } from "@angular/core";
import { Customer } from "../../../models/customer.model";
import { SigninService } from "app/services/signin/signin.service";
import { NgForm } from "@angular/forms";
import { environment } from "../../../../environments/environment.development";
import { Router } from "@angular/router";
import { getCookie } from "typescript-cookie";

@Component({
    selector: "app-sign-in",
    templateUrl: "./sign-in.component.html",
    styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent implements OnInit {
    authStatus: string = "";
    user = new Customer();

    // TEMP


    constructor(private signinService: SigninService, private router: Router) {}

    ngOnInit(): void {
        this.user.password = "password123";
        this.user.email = "test_user@shirtmoxy.com"
    }

    validateUser(signinForm: NgForm) {
        console.log("validateUser() start");

        this.signinService
            .validateSigninDetails(this.user)
            .subscribe((responseData) => {
                console.log("subscribe() start");

                window.sessionStorage.setItem(
                    "Authorization",
                    responseData.headers.get("Authorization")!
                );

                this.user = <any>responseData.body;
                this.user.authStatus = "AUTH";

                console.log(JSON.stringify(this.user));

                window.sessionStorage.setItem(
                    "userdetails",
                    JSON.stringify(this.user)
                );

                console.log("XSRF TOKEN start");

                let xsrf = getCookie("XSRF-TOKEN")!;
                window.sessionStorage.setItem("XSRF-TOKEN", xsrf);

                console.log("navigate");

                this.router.navigate(["/user/projects"]);
            });
    }
}
