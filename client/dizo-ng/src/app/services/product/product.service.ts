import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Product } from "app/models/product.model";
import { Observable, forkJoin, of } from "rxjs";
import { catchError, map, switchMap } from "rxjs/operators";
import { environment } from "../../../environments/environment.development";
import { AppConstants } from "../../../app/components/constants/app.constants";
import { ProductCategory } from "app/models/product-category.model";
import { ProductGender } from "app/models/product-gender.model";
import { ProductManufacturer } from "app/models/product-manufacturer.model";
import { ProductColor } from "app/models/product-color.model";
import { ProductSize } from "app/models/product-size";
import { ProductMaterial } from "app/models/product-material";

@Injectable({
    providedIn: "root",
})
export class ProductService {
    constructor(private httpClient: HttpClient) {}

    // get all product categories filters
    getAllProductCategories(): Observable<ProductCategory[]> {
        return this.httpClient.get<ProductCategory[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_CATEGORIES_API_URL
        );
    }

    // get all product colors filters
    getAllProductColors(): Observable<ProductColor[]> {
        return this.httpClient.get<ProductColor[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_COLORS_API_URL
        );
    }

    // get all product genders filters
    getAllProductGenders(): Observable<ProductGender[]> {
        return this.httpClient.get<ProductGender[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_GENDERS_API_URL
        );
    }

    // get all product manufacturers filters
    getAllProductManufacturers(): Observable<ProductManufacturer[]> {
        return this.httpClient.get<ProductManufacturer[]>(
            environment.rooturl +
                AppConstants.ALL_PRODUCT_MANUFACTURER_BRANDS_API_URL
        );
    }

    // get all product sizes filters
    getAllProductSizes(): Observable<ProductSize[]> {
        return this.httpClient.get<ProductSize[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_SIZES_API_URL
        );
    }

    // get all product materials filters
    getAllProductMaterials(): Observable<ProductMaterial[]> {
        return this.httpClient.get<ProductMaterial[]>(
            environment.rooturl + AppConstants.ALL_PRODUCT_MATERIALS_API_URL
        );
    }

    // get all clothing products
    getAllClothingFilteredProductsPaginate(
        thePageNum: number,
        thePageSize: number,
        optionalFilteredListUrlQueryParams: string = ""
    ): Observable<GetResponseProductPage> {
        return this.handleGetProducts(
            AppConstants.ALL_CLOTHING_PRODUCTS_BY_FILTERS_API_URL,
            thePageNum,
            thePageSize,
            optionalFilteredListUrlQueryParams
        );
    }

    // get product details by SKU
    getProductDetailsBySku(theProductSku: string): Observable<Product> {
        return this.httpClient.get<Product>(
            environment.rooturl +
                AppConstants.PRODUCT_DETAILS +
                `/${theProductSku}`
        );
    }

    getProductDetailsByNameAndColorId(
        theProductName: string,
        theColorId: number
    ): Observable<Product> {
        return this.httpClient.get<Product>(
            environment.rooturl +
                AppConstants.PRODUCT_DETAILS +
                `?productName=${theProductName}&colorId=${theColorId}`
        );
    }

    getAllAvailableColorsByProductName(
        theProductName: string
    ): Observable<ProductColor[]> {
        return this.httpClient.get<ProductColor[]>(
            environment.rooturl +
                AppConstants.ALL_AVAILABLE_COLORS_BY_PRODUCT_NAME +
                `?productName=${theProductName}`
        );
    }

    getAllAvailableSizesByProductName(
        theProductName: string
    ): Observable<ProductSize[]> {
        return this.httpClient.get<ProductSize[]>(
            environment.rooturl +
                AppConstants.ALL_AVAILABLE_SIZES_BY_PRODUCT_NAME +
                `?productName=${theProductName}`
        );
    }

    getProductInStockSizes(
        theProductName: string,
        theColorId: number
    ): Observable<ProductSize[]> {
        return this.httpClient.get<ProductSize[]>(
            environment.rooturl +
                AppConstants.ALL_PRODUCT_IN_STOCK_SIZES +
                `?productName=${theProductName}&colorId=${theColorId}`
        );
    }

    getProductOutOfStockSizes(
        theProductName: string,
        theColorId: number
    ): Observable<ProductSize[]> {
        return this.httpClient.get<ProductSize[]>(
            environment.rooturl +
                AppConstants.ALL_PRODUCT_OUT_OF_STOCK_SIZES +
                `?productName=${theProductName}&colorId=${theColorId}`
        );
    }

    getProductDetailsAndColorsAndSizes(theProductSku: string): Observable<{
        product: Product;
        colors: ProductColor[];
        sizes: ProductSize[];
        inStockSizes: ProductSize[];
        outOfStockSizes: ProductSize[];
    }> {
        return this.getProductDetailsBySku(theProductSku).pipe(
            switchMap((product: Product) => {
                const productDetails = of(product);

                const colors = this.getAllAvailableColorsByProductName(
                    product.name
                );
                const sizes = this.getAllAvailableSizesByProductName(
                    product.name
                );

                const inStockSizes = this.getProductInStockSizes(
                    product.name,
                    product.color.id
                ).pipe(
                    catchError(() => of([])) // Provide an empty array if an error occurs or if it's empty.
                );

                const outOfStockSizes = this.getProductOutOfStockSizes(
                    product.name,
                    product.color.id
                ).pipe(
                    catchError(() => of([])) // Provide an empty array if an error occurs or if it's empty.
                );

                return forkJoin([
                    productDetails,
                    colors,
                    sizes,
                    inStockSizes,
                    outOfStockSizes,
                ]).pipe(
                    map(
                        ([
                            product,
                            colors,
                            sizes,
                            inStockSizes,
                            outOfStockSizes,
                        ]) => ({
                            product,
                            colors,
                            sizes,
                            inStockSizes,
                            outOfStockSizes,
                        })
                    )
                );
            })
        );
    }

    // get all products helper method
    private handleGetProducts(
        url: string,
        thePageNum: number,
        thePageSize: number,
        optionalFilteredListUrlQueryParams?: string
    ): Observable<GetResponseProductPage> {
        return this.httpClient
            .get<GetResponseProductPage>(
                environment.rooturl +
                    `${url}/filteredList?${optionalFilteredListUrlQueryParams}&pageNum=${thePageNum}&pageSize=${thePageSize}`
            )
            .pipe(
                map((response) => {
                    console.log(
                        "URL: " +
                            environment.rooturl +
                            `${url}/filteredList?${optionalFilteredListUrlQueryParams}&pageNum=${thePageNum}&pageSize=${thePageSize}`
                    );

                    console.log(response);
                    return response;
                })
            );
    }

    /*** TODO - To be Deleted **********************************/

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
