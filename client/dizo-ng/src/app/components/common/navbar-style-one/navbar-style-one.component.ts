import { Component, OnInit } from "@angular/core";
import { KeycloakService } from "keycloak-angular";
import { KeycloakProfile } from "keycloak-js";
import { environment } from "../../../../environments/environment.development";
import { Customer } from "app/models/customer.model";

@Component({
    selector: "app-navbar-style-one",
    templateUrl: "./navbar-style-one.component.html",
    styleUrls: ["./navbar-style-one.component.scss"],
})
export class NavbarStyleOneComponent implements OnInit {
    user = new Customer();
    public isSignedIn = false;
    public userProfile: KeycloakProfile | null = null;

    constructor(private readonly keycloak: KeycloakService) {}

    public async ngOnInit() {
        this.isSignedIn = await this.keycloak.isLoggedIn();

        if (this.isSignedIn) {
            this.userProfile = await this.keycloak.loadUserProfile();
            this.user.authStatus = "AUTH";
            this.user.firstName = this.userProfile.firstName || "";
            window.sessionStorage.setItem(
                "userdetails",
                JSON.stringify(this.user)
            );
        }
    }

    public signIn(): void {
        this.keycloak.login();
    }

    public signOut(): void {
        let redirectURI: string = environment.keycloakConfig.signOutRedirectUri;
        this.keycloak.logout(redirectURI);
    }
}
