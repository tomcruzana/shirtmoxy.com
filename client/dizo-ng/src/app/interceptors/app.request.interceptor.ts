import { Injectable } from "@angular/core";
import {
    HttpInterceptor,
    HttpRequest,
    HttpHandler,
    HttpErrorResponse,
    HttpHeaders,
} from "@angular/common/http";
import { Router } from "@angular/router";
import { tap } from "rxjs/operators";
import { Customer } from "../models/customer.model";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {
    // initialize a user model obj
    user = new Customer();
    // inject the router obj
    constructor(private router: Router) {}

    // override the intercept method
    intercept(req: HttpRequest<any>, next: HttpHandler) {
        // this http header is used to add authorization details
        let httpHeaders = new HttpHeaders();

        // map userdetails to user model if it already exists in the session storage
        if (sessionStorage.getItem("userdetails")) {
            this.user = JSON.parse(sessionStorage.getItem("userdetails")!);
        }

        // append when user is trying to login
        if (this.user && this.user.password && this.user.email) {
            httpHeaders = httpHeaders.append(
                "Authorization",
                "Basic " +
                    window.btoa(this.user.email + ":" + this.user.password)
            );
        } else {
            // read the JWT token from session storage
            let authorization = sessionStorage.getItem("Authorization");

            // if Authorization session value is found, append it to headers
            if (authorization) {
                httpHeaders = httpHeaders.append(
                    "Authorization",
                    authorization
                );
            }
        }

        // XSRF-TOKEN token
        let xsrf = sessionStorage.getItem("XSRF-TOKEN");
        if (xsrf) {
            httpHeaders = httpHeaders.append("X-XSRF-TOKEN", xsrf);
        }

        // clone headers
        httpHeaders = httpHeaders.append("X-Requested-With", "XMLHttpRequest");
        const xhr = req.clone({
            headers: httpHeaders,
        });

        return next.handle(xhr).pipe(
            tap((err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.status !== 401) {
                        // return 401 (acks valid authentication credentials)
                        return;
                    }
                    // if success, navigate to user dashboard
                    this.router.navigate(["/user/projects"]);
                }
            })
        );
    }
}
