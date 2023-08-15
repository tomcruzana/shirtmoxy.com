import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Product } from "app/models/product.model";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from "../../../environments/environment.development";
import { AppConstants } from "../../../app/components/constants/app.constants";
import { ProductCategory } from "app/models/product-category.model";
import { ProductGender } from "app/models/product-gender.model";
import { ProductManufacturer } from "app/models/product-manufacturer.model";

@Injectable({
    providedIn: "root",
})
export class ProductService {
    constructor(private httpClient: HttpClient) {}

    private getProducts(
        url: string,
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseProductPage> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    `${url}?pageNum=${thePageNum}&pageSize=${thePageSize}`
            )
            .pipe(map((response) => response));
    }

    // get all products
    getAllProductsPaginate(
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseProductPage> {
        return this.getProducts(
            AppConstants.ALL_PRODUCTS_API_URL,
            thePageNum,
            thePageSize
        );
    }

    // search for products
    searchProductsPaginate(
        theQuery: string,
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseProductPage> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_SEARCH_API_URL +
                    `?query=${theQuery}&pageNum=${thePageNum}&pageSize=${thePageSize}`
            )
            .pipe(map((response) => response));
    }

    // get all product genders
    getAllProductGenders(): Observable<ProductGender[]> {
        return this.httpClient.get<ProductGender[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_GENDERS_API_URL
        );
    }

    // get all product categories
    getAllProductCategories(): Observable<ProductCategory[]> {
        return this.httpClient.get<ProductCategory[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_CATEGORIES_API_URL
        );
    }

    // get all product manufacturers
    getAllProductManufacturers(): Observable<ProductManufacturer[]> {
        return this.httpClient.get<ProductManufacturer[]>(
            environment.rooturl +
                AppConstants.ALL_PRODUCT_MANUFACTURER_BRANDS_API_URL
        );
    }

    // get all products based on category id
    getProductByCategoryId(
        theCategoryId: number,
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseProductPage> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_BY_CATEGORY_ID_API_URL +
                    `?cId=${theCategoryId}&pageNum=${thePageNum}&pageSize=${thePageSize}`
            )
            .pipe(map((response) => response));
    }

    // get all products based on gender id
    getProductByGenderIdPaginate(
        theGenderId: number,
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseProductPage> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_BY_GENDER_ID_API_URL +
                    `?gId=${theGenderId}&pageNum=${thePageNum}&pageSize=${thePageSize}`
            )
            .pipe(map((response) => response));
    }

    // get all products based on manufacturer id
    getProductByManufacturerIdPaginate(
        theManufacturerId: number,
        thePageNum: number,
        thePageSize: number
    ): Observable<GetResponseProductPage> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_BY_MANUFACTURER_ID_API_URL +
                    `?mId=${theManufacturerId}&pageNum=${thePageNum}&pageSize=${thePageSize}`
            )
            .pipe(map((response) => response));
    }

    getProductDetailsById(theProductId: number): Observable<Product> {
        return this.httpClient.get<Product>(
            environment.rooturl +
                AppConstants.PRODUCT_BY_ID +
                `/${theProductId}`
        );
    }
}

interface GetResponseProductPage {
    content: Product[];
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
