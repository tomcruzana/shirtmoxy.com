import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { environment } from "./../environments/environment.development";
import { SweetAlert2Module } from "@sweetalert2/ngx-sweetalert2";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { PreloaderComponent } from "./components/common/preloader/preloader.component";
import { HomePageOneComponent } from "./components/pages/home-page-one/home-page-one.component";
import { HomePageTwoComponent } from "./components/pages/home-page-two/home-page-two.component";
import { FooterStyleOneComponent } from "./components/common/footer-style-one/footer-style-one.component";
import { NavbarStyleOneComponent } from "./components/common/navbar-style-one/navbar-style-one.component";
import { FooterStyleTwoComponent } from "./components/common/footer-style-two/footer-style-two.component";
import { AboutComponent } from "./components/pages/about/about.component";
import { PricingComponent } from "./components/pages/pricing/pricing.component";
import { FaqComponent } from "./components/pages/faq/faq.component";
import { DesignerComponent } from "./components/pages/designer/designer.component";
import { TestimonialsComponent } from "./components/pages/testimonials/testimonials.component";
import { ErrorComponent } from "./components/pages/error/error.component";
import { SignInComponent } from "./components/pages/sign-in/sign-in.component";
import { SignUpComponent } from "./components/pages/sign-up/sign-up.component";
import { RecoverPasswordComponent } from "./components/pages/recover-password/recover-password.component";
import { TermsConditionsComponent } from "./components/pages/terms-conditions/terms-conditions.component";
import { PrivacyPolicyComponent } from "./components/pages/privacy-policy/privacy-policy.component";
import { ComingSoonComponent } from "./components/pages/coming-soon/coming-soon.component";
import { ServicesComponent } from "./components/pages/services/services.component";
import { ServicesDetailsComponent } from "./components/pages/services-details/services-details.component";
import { ProductsComponent } from "./components/pages/products/products.component";
import { CartComponent } from "./components/pages/cart/cart.component";
import { CheckoutComponent } from "./components/pages/checkout/checkout.component";
import { ProductsDetailsComponent } from "./components/pages/products-details/products-details.component";
import { BlogComponent } from "./components/pages/blog/blog.component";
import { BlogDetailsComponent } from "./components/pages/blog-details/blog-details.component";
import { ContactComponent } from "./components/pages/contact/contact.component";
import { MatchPasswordDirective } from "./directives/match-password.directive";
import { UserProfileComponent } from "./components/pages/user-profile/user-profile.component";
import { ProfileNavbarComponent } from "./components/common/profile-navbar/profile-navbar.component";
import { ChangePasswordComponent } from "./components/pages/change-password/change-password.component";
import { ManageAccountComponent } from "./components/pages/manage-account/manage-account.component";
import { TrackOrderComponent } from "./components/pages/track-order/track-order.component";
import { UserProjectsComponent } from "./components/pages/user-projects/user-projects.component";
import { SignOutComponent } from "./components/pages/sign-out/sign-out.component";
import {
    HttpClientModule,
    HttpClientXsrfModule,
    HTTP_INTERCEPTORS,
} from "@angular/common/http";
import { ProductSearchBarComponent } from "./components/pages/product-search-bar/product-search-bar.component";
import { ProductResultFilterComponent } from "./components/pages/product-result-filter/product-result-filter.component";
import { ProductSearchFilterComponent } from "./components/pages/product-search-filter/product-search-filter.component";
import { CartStatusComponent } from "./components/common/navbar/navbar-items/cart-status/cart-status.component";
import { XhrInterceptor } from "./interceptors/app.request.interceptor";
import { AuthActivateRouteGuard } from "./routeguards/auth.guard";
import { OrderDetailsComponent } from "./components/pages/orders/order-details/order-details.component";
import { OrdersComponent } from "./components/pages/orders/orders/orders.component";
import { OrderConfirmationComponent } from "./components/pages/orders/order-confirmation/order-confirmation.component";
import { ProductEditorComponent } from "./components/pages/product-editor/product-editor.component";
import { ShippingAndReturnsComponent } from "./components/pages/shipping-and-returns/shipping-and-returns.component";
import { PriceQuantityCalculatorComponent } from "./components/common/price-quantity-calculator/price-quantity-calculator.component";
import { NumericInputOnlyDirective } from "./directives/numeric-input-only.directive";
import { JoinPipe } from "./pipes/join.pipe";
import { ProductFilterPillsComponent } from "./components/common/product-filter-pills/product-filter-pills.component";
import { NoWhitespaceDirective } from "./directives/no-whitespace.directive";
import { Event, Router, NavigationEnd, Scroll } from "@angular/router";
import { ViewportScroller } from "@angular/common";
import { filter, pairwise } from "rxjs/operators";
import { UserAddressComponent } from './components/user-address/user-address.component';
import { UserPaymentInformationComponent } from './components/user-payment-information/user-payment-information.component';

@NgModule({
    declarations: [
        AppComponent,
        PreloaderComponent,
        HomePageOneComponent,
        HomePageTwoComponent,
        FooterStyleOneComponent,
        NavbarStyleOneComponent,
        FooterStyleTwoComponent,
        AboutComponent,
        PricingComponent,
        FaqComponent,
        DesignerComponent,
        TestimonialsComponent,
        ErrorComponent,
        SignInComponent,
        SignUpComponent,
        RecoverPasswordComponent,
        TermsConditionsComponent,
        PrivacyPolicyComponent,
        ComingSoonComponent,
        ServicesComponent,
        ServicesDetailsComponent,
        ProductsComponent,
        CartComponent,
        CheckoutComponent,
        ProductsDetailsComponent,
        BlogComponent,
        BlogDetailsComponent,
        ContactComponent,
        MatchPasswordDirective,
        UserProfileComponent,
        ProfileNavbarComponent,
        ChangePasswordComponent,
        ManageAccountComponent,
        TrackOrderComponent,
        UserProjectsComponent,
        SignOutComponent,
        ProductSearchBarComponent,
        CartStatusComponent,
        OrderDetailsComponent,
        OrdersComponent,
        OrderConfirmationComponent,
        ProductEditorComponent,
        ProductResultFilterComponent,
        ProductSearchFilterComponent,
        ShippingAndReturnsComponent,
        PriceQuantityCalculatorComponent,
        NumericInputOnlyDirective,
        JoinPipe,
        ProductFilterPillsComponent,
        NoWhitespaceDirective,
        UserAddressComponent,
        UserPaymentInformationComponent,
    ],
    imports: [
        SweetAlert2Module,
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientXsrfModule.withOptions({
            cookieName: "XSRF-TOKEN",
            headerName: "X-XSRF-TOKEN",
        }),
        AppRoutingModule,
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: XhrInterceptor,
            multi: true,
        },
        AuthActivateRouteGuard,
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
