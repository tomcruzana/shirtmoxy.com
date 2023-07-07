import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomePageOneComponent } from "./components/pages/home-page-one/home-page-one.component";
import { HomePageTwoComponent } from "./components/pages/home-page-two/home-page-two.component";
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
import { UserProfileComponent } from "./components/pages/user-profile/user-profile.component";
import { ChangePasswordComponent } from "./components/pages/change-password/change-password.component";
import { ManageAccountComponent } from "./components/pages/manage-account/manage-account.component";
import { TrackOrderComponent } from "./components/pages/track-order/track-order.component";
import { UserProjectsComponent } from "./components/pages/user-projects/user-projects.component";
import { SignOutComponent } from "./components/pages/sign-out/sign-out.component";

const routes: Routes = [
    { path: "", redirectTo: "/home", pathMatch: "full" },
    { path: "home", component: HomePageTwoComponent },
    { path: "about", component: AboutComponent },
    { path: "pricing", component: PricingComponent },
    { path: "faq", component: FaqComponent },
    { path: "designer", component: DesignerComponent },
    { path: "testimonials", component: TestimonialsComponent },
    { path: "error", component: ErrorComponent },
    { path: "sign-in", component: SignInComponent },
    { path: "sign-out", component: SignOutComponent },
    { path: "sign-up", component: SignUpComponent },
    { path: "track", component: TrackOrderComponent },
    { path: "recover-password", component: RecoverPasswordComponent },
    { path: "user/projects", component: UserProjectsComponent },
    { path: "user/profile", component: UserProfileComponent },
    { path: "user/password-reset", component: ChangePasswordComponent },
    {
        path: "user/manage-account",
        component: ManageAccountComponent,
    },
    { path: "terms-condition", component: TermsConditionsComponent },
    { path: "privacy-policy", component: PrivacyPolicyComponent },
    { path: "coming-soon", component: ComingSoonComponent },
    { path: "services", component: ServicesComponent },
    { path: "service-details", component: ServicesDetailsComponent },
    { path: "product", component: ProductsComponent },
    { path: "cart", component: CartComponent },
    { path: "checkout", component: CheckoutComponent },
    { path: "product-details", component: ProductsDetailsComponent },
    { path: "blog", component: BlogComponent },
    { path: "blog-details", component: BlogDetailsComponent },
    { path: "contact", component: ContactComponent },
    { path: "**", component: ErrorComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {})],
    exports: [RouterModule],
})
export class AppRoutingModule {}
