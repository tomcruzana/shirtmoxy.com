import { Component, OnInit } from "@angular/core";

@Component({
    selector: "app-footer-style-one",
    templateUrl: "./footer-style-one.component.html",
    styleUrls: ["./footer-style-one.component.scss"],
})
export class FooterStyleOneComponent implements OnInit {
    currentYear: number = new Date().getFullYear();

    constructor() {}

    ngOnInit(): void {}
}
