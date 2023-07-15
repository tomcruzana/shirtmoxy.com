import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomePageTwoComponent } from "./components/pages/home-page-two/home-page-two.component";
import { AboutComponent } from "./components/pages/about/about.component";
import { FaqComponent } from "./components/pages/faq/faq.component";
import { ErrorComponent } from "./components/pages/error/error.component";
import { SignInComponent } from "./components/pages/sign-in/sign-in.component";
import { SignUpComponent } from "./components/pages/sign-up/sign-up.component";
import { RecoverPasswordComponent } from "./components/pages/recover-password/recover-password.component";
import { TermsConditionsComponent } from "./components/pages/terms-conditions/terms-conditions.component";
import { PrivacyPolicyComponent } from "./components/pages/privacy-policy/privacy-policy.component";
import { ComingSoonComponent } from "./components/pages/coming-soon/coming-soon.component";
import { ProductsComponent } from "./components/pages/products/products.component";
import { CartComponent } from "./components/pages/cart/cart.component";
import { CheckoutComponent } from "./components/pages/checkout/checkout.component";
import { ProductsDetailsComponent } from "./components/pages/products-details/products-details.component";
import { ContactComponent } from "./components/pages/contact/contact.component";
import { UserProfileComponent } from "./components/pages/user-profile/user-profile.component";
import { ChangePasswordComponent } from "./components/pages/change-password/change-password.component";
import { ManageAccountComponent } from "./components/pages/manage-account/manage-account.component";
import { TrackOrderComponent } from "./components/pages/track-order/track-order.component";
import { UserProjectsComponent } from "./components/pages/user-projects/user-projects.component";
import { SignOutComponent } from "./components/pages/sign-out/sign-out.component";
import { AuthKeyClockGuard } from "./routeguards/auth.guard";

const routes: Routes = [
    { path: "", redirectTo: "/home", pathMatch: "full" },
    { path: "home", component: HomePageTwoComponent },
    { path: "product", component: ProductsComponent },
    { path: "product-details", component: ProductsDetailsComponent },
    { path: "about", component: AboutComponent },
    { path: "contact", component: ContactComponent },
    { path: "faq", component: FaqComponent },
    { path: "sign-in", component: SignInComponent },
    { path: "sign-out", component: SignOutComponent },
    { path: "sign-up", component: SignUpComponent },
    { path: "recover-password", component: RecoverPasswordComponent },
    { path: "track", component: TrackOrderComponent },
    {
        path: "user/projects",
        component: UserProjectsComponent,
        canActivate: [AuthKeyClockGuard],
        data: { roles: ["USER"] },
    },
    {
        path: "user/profile",
        component: UserProfileComponent,
        canActivate: [AuthKeyClockGuard],
        data: { roles: ["USER"] },
    },
    {
        path: "user/password-reset",
        component: ChangePasswordComponent,
        canActivate: [AuthKeyClockGuard],
        data: { roles: ["USER"] },
    },
    {
        path: "user/manage-account",
        component: ManageAccountComponent,
        canActivate: [AuthKeyClockGuard],
        data: { roles: ["USER"] },
    },
    { path: "terms-condition", component: TermsConditionsComponent },
    { path: "privacy-policy", component: PrivacyPolicyComponent },
    { path: "coming-soon", component: ComingSoonComponent },
    { path: "cart", component: CartComponent },
    { path: "checkout", component: CheckoutComponent },
    { path: "error", component: ErrorComponent },
    { path: "**", component: ErrorComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {})],
    exports: [RouterModule],
})
export class AppRoutingModule {}
