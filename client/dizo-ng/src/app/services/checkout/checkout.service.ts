import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "app/components/constants/app.constants";
import { PaymentInfo } from "app/models/payment-info.model";
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

    createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
        return this.httpClient.post<PaymentInfo>(
            environment.rooturl + AppConstants.CHECKOUT_PAYMENT_INTENT_URL,
            paymentInfo
        );
    }
}
