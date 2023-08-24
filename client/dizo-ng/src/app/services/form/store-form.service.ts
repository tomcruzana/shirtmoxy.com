import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "app/components/constants/app.constants";
import { Country } from "app/models/country.model";
import { State } from "app/models/state.model";
import { environment } from "environments/environment.development";
import { Observable, of } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class StoreFormService {
    constructor(private httpClient: HttpClient) {}

    getAllCountries(): Observable<Country[]> {
        return this.httpClient.get<Country[]>(
            environment.rooturl + AppConstants.ALL_COUNTRIES
        );
    }

    getAllStates(theCountryCode: string): Observable<State[]> {
        return this.httpClient.get<State[]>(
            environment.rooturl +
                AppConstants.ALL_STATES_BY_COUNTRY_CODE +
                `?countryCode=${theCountryCode}`
        );
    }

    getCreditCardMonths(startMonth: number): Observable<number[]> {
        let data: number[] = [];

        // month drop-down list
        for (let theMonth = startMonth; theMonth <= 12; theMonth++) {
            data.push(theMonth);
        }

        return of(data);
    }

    getCreditCardYears(): Observable<number[]> {
        let data: number[] = [];

        // year drop-down list
        const startYear: number = new Date().getFullYear();
        const endYear: number = startYear + 10;

        for (let theYear = startYear; theYear <= endYear; theYear++) {
            data.push(theYear);
        }

        return of(data);
    }
}
