import { Injectable } from "@angular/core";
import {
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Router,
    UrlTree,
} from "@angular/router";
import { Customer } from "app/models/customer.model";
import { Observable } from "rxjs";

@Injectable()
export class AuthActivateRouteGuard {
    user = new Customer();

    constructor(private router: Router) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ):
        | Observable<boolean | UrlTree>
        | Promise<boolean | UrlTree>
        | boolean
        | UrlTree {
        // if userdetails session obj doesn't exist, reroute to signin page
        if (sessionStorage.getItem("userdetails") === null) {
            // reroute to /signin page
            this.router.navigate(["sign-in"]);
            return false;
        }

        // if userdetails session obj exists, map to user model
        this.user = JSON.parse(sessionStorage.getItem("userdetails")!);
        return true;
    }
}
