import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "app/components/constants/app.constants";
import { Purchase } from "app/models/purchase.model";
import { environment } from "environments/environment.development";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class CheckoutService {
    constructor(private httpClient: HttpClient) {}

    placeOrder(purchase: Purchase): Observable<any> {
        return this.httpClient.post<Purchase>(
            environment.rooturl + AppConstants.CHECKOUT_PURCHASE_URL,
            purchase
        );
    }
}
