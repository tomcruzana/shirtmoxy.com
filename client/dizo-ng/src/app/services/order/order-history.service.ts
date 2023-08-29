import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "app/components/constants/app.constants";
import { OrderHistory } from "app/models/order-history.model";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment.development";

@Injectable({
    providedIn: "root",
})
export class OrderHistoryService {
    constructor(private httpClient: HttpClient) {}

    // get all orders by customer email
    getAllOrdersByCustomerEmailPaginate(
        theEmail: string,
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseOrderHistoryPage> {
        return this.httpClient.get<GetResponseOrderHistoryPage>(
            environment.rooturl +
                AppConstants.ALL_ORDERS_BY_CUSTOMER_ID +
                `?email=${theEmail}&pageNum=${thePageNum}&pageSize=${thePageSize}`
        );
    }
}

interface GetResponseOrderHistoryPage {
    content: OrderHistory[];
    pageable: {
        sort: {
            empty: boolean;
            sorted: boolean;
            unsorted: boolean;
        };
        offset: number;
        pageNumber: number; // current page number
        pageSize: number; // size of the page
        paged: boolean;
        unpaged: boolean;
    };
    last: boolean;
    totalElements: number; // grand total of all elements
    totalPages: number; // total pages available
    size: number;
    number: number;
    sort: {
        empty: boolean;
        sorted: boolean;
        unsorted: boolean;
    };
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}
