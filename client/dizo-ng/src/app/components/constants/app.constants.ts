export const AppConstants = {
    // user profile
    SIGNIN_API_URL: "/user",

    // orders
    ALL_ORDERS_BY_CUSTOMER_ID: "/user/orders/all",

    // product details
    PRODUCT_DETAILS: "/product/details",
    PRODUCT_SKU_DETAILS: "/product/sku-details",
    ALL_AVAILABLE_COLORS_BY_PRODUCT_NAME: "/product/details/available-colors",
    ALL_AVAILABLE_SIZES_BY_PRODUCT_NAME: "/product/details/available-sizes",
    ALL_PRODUCT_IN_STOCK_SIZES: "/product/details/in-stock-sizes",
    ALL_PRODUCT_OUT_OF_STOCK_SIZES: "/product/details/out-of-stock-sizes",

    // product filters
    ALL_PRODUCT_GENDERS_API_URL: "/product/gender/all",
    ALL_PRODUCT_CATEGORIES_API_URL: "/product/category/all",
    ALL_PRODUCT_COLORS_API_URL: "/product/color/all",
    ALL_PRODUCT_MANUFACTURER_BRANDS_API_URL: "/product/manufacturer/all",
    ALL_PRODUCT_SIZES_API_URL: "/product/size/all",
    ALL_PRODUCT_MATERIALS_API_URL: "/product/material/all",

    // clothing product type
    ALL_CLOTHING_PRODUCTS_BY_FILTERS_API_URL: "/product/clothing",

    // contact us
    ALL_CONTACT_US_CATEGORIES: "/contact/category/all",
    CONTACT_US_DETAILS_URL: "/contact/submit-message",

    // checkout
    ALL_COUNTRIES: "/storeform/country/all",
    ALL_STATES_BY_COUNTRY_CODE: "/storeform/state/all",
    CHECKOUT_PURCHASE_URL: "/checkout/purchase",
    CHECKOUT_PAYMENT_INTENT_URL: "/checkout/payment-intent",
};
