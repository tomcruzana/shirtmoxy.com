import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "app/components/constants/app.constants";
import { ContactUsCategory } from "app/models/contact-us-category.model";
import { ContactUs } from "app/models/contact-us.model";
import { environment } from "environments/environment.development";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class ContactUsService {
    constructor(private httpClient: HttpClient) {}

    getAllContactUsCategories(): Observable<ContactUsCategory[]> {
        return this.httpClient.get<ContactUsCategory[]>(
            environment.rooturl + AppConstants.ALL_CONTACT_US_CATEGORIES
        );
    }

    sendContactUsDetails(contactUsDetails: ContactUs): Observable<ContactUs> {
        return this.httpClient.post<ContactUs>(
            environment.rooturl + AppConstants.CONTACT_US_DETAILS_URL,
            contactUsDetails
        );
    }
}
