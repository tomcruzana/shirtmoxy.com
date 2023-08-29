import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../../app/components/constants/app.constants";
import { Customer } from "../../models/customer.model";
import { environment } from "../../../environments/environment.development";

@Injectable({
    providedIn: "root",
})
export class SigninService {
    constructor(private http: HttpClient) {}

    // authenticate user
    validateSigninDetails(user: Customer) {
        console.log("validateSigninDetails() start");
        console.log(JSON.stringify(user));

        // get session from session storage
        window.sessionStorage.setItem("userdetails", JSON.stringify(user));

        console.log(window.sessionStorage.getItem("userdetails"));

        return this.http.get(
            environment.rooturl + AppConstants.SIGNIN_API_URL,
            {
                observe: "response",
                withCredentials: true,
            }
        );
    }
}
