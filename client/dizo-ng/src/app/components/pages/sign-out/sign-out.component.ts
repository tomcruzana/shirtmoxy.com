import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Customer } from "app/models/customer.model";

@Component({
    selector: "app-sign-out",
    templateUrl: "./sign-out.component.html",
    styleUrls: ["./sign-out.component.scss"],
})
export class SignOutComponent implements OnInit {
    user = new Customer();

    constructor() {}

    ngOnInit(): void {
        window.sessionStorage.setItem("userdetails", "");
        window.sessionStorage.setItem("XSRF-TOKEN", "");
        // this.router.navigate(["/sign-in"]);
    }
}
