import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Product } from "app/models/product.model";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from "../../../environments/environment.development";
import { AppConstants } from "../../../app/components/constants/app.constants";

@Injectable({
    providedIn: "root",
})
export class ProductService {
    constructor(private httpClient: HttpClient) {}

    getAllProducts(): Observable<Product[]> {
        return this.httpClient
            .get<GetResponse>(
                environment.rooturl + AppConstants.ALL_PRODUCTS_API_URL
            )
            .pipe(map((response) => response.content));
    }
}

interface GetResponse {
    content: Product[];
}
