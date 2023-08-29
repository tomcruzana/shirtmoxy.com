import { Component, OnInit } from "@angular/core";
import { environment } from "../../../../environments/environment.development";
import { Customer } from "app/models/customer.model";

@Component({
    selector: "app-navbar-style-one",
    templateUrl: "./navbar-style-one.component.html",
    styleUrls: ["./navbar-style-one.component.scss"],
})
export class NavbarStyleOneComponent implements OnInit {
    user = new Customer();

    constructor() {}

    ngOnInit(): void {}
}
