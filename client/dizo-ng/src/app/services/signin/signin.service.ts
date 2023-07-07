import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../../app/components/contants/app.constants";
import { User } from "../../models/user.model";
import { environment } from "../../../environments/environment.development";

@Injectable({
    providedIn: "root",
})
export class SigninService {
    constructor(private http: HttpClient) {}

    validateLoginDetails(user: User) {
        window.sessionStorage.setItem("userdetails", JSON.stringify(user));
        return this.http.get(
            environment.rooturl + AppConstants.SIGNIN_API_URL,
            {
                observe: "response",
                withCredentials: true,
            }
        );
    }
}
