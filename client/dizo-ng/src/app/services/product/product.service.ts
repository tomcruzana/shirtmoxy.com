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

    // get all products
    getAllProducts(): Observable<Product[]> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl + AppConstants.ALL_PRODUCTS_API_URL
            )
            .pipe(map((response) => response.content));
    }

    // search for products
    searchProducts(theQuery: string): Observable<Product[]> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_SEARCH_API_URL +
                    `?query=${theQuery}&pageNum=1`
            )
            .pipe(map((response) => response.content));
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
    getProductByCategoryId(theCategoryId: number): Observable<Product[]> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_BY_CATEGORY_ID_API_URL +
                    `?cId=${theCategoryId}&pageNum=1`
            )
            .pipe(map((response) => response.content));
    }

    // get all products based on gender id
    getProductByGenderId(theGenderId: number): Observable<Product[]> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_BY_GENDER_ID_API_URL +
                    `?gId=${theGenderId}&pageNum=1`
            )
            .pipe(map((response) => response.content));
    }

    // get all products based on manufacturer id
    getProductByManufacturerId(
        theManufacturerId: number
    ): Observable<Product[]> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    AppConstants.ALL_PRODUCTS_BY_MANUFACTURER_ID_API_URL +
                    `?mId=${theManufacturerId}&pageNum=1`
            )
            .pipe(map((response) => response.content));
    }

    getProductDetailsById(theProductId: number): Observable<Product> {
        return this.httpClient.get<Product>(
            environment.rooturl + AppConstants.PRODUCT_BY_ID + `/${theProductId}`
        );
    }
}

interface GetResponseProductPage {
    content: Product[];
}
