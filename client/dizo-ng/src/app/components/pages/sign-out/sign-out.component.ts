import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "app/models/user.model";

@Component({
    selector: "app-sign-out",
    templateUrl: "./sign-out.component.html",
    styleUrls: ["./sign-out.component.scss"],
})
export class SignOutComponent implements OnInit {
    user = new User();

    constructor(private router: Router) {}

    ngOnInit(): void {
        window.sessionStorage.setItem("userdetails", "");
        window.sessionStorage.setItem("XSRF-TOKEN", "");
        this.router.navigate(["/sign-in"]);
    }
}
