import { Component, OnInit } from "@angular/core";
import {
    FormBuilder,
    FormControl,
    FormGroup,
    Validators,
} from "@angular/forms";
import { Router } from "@angular/router";
import { Country } from "app/models/country.model";
import { Customer } from "app/models/customer.model";
import { OrderItem } from "app/models/order-item.model";
import { Order } from "app/models/order.model";
import { Purchase } from "app/models/purchase.model";
import { State } from "app/models/state.model";
import { CartService } from "app/services/cart/cart.service";
import { CheckoutService } from "app/services/checkout/checkout.service";
import { StoreFormService } from "app/services/form/store-form.service";
import { StoreFormValidators } from "app/validators/store-form-validators";

@Component({
    selector: "app-checkout",
    templateUrl: "./checkout.component.html",
    styleUrls: ["./checkout.component.scss"],
})
export class CheckoutComponent implements OnInit {
    checkoutFormGroup: FormGroup;

    totalPrice: number = 0;
    totalQuantity: number = 0;

    creditCardMonths: number[] = [];
    creditCardYears: number[] = [];

    countries: Country[] = [];

    shippingAddressStates: State[] = [];
    billingAddressStates: State[] = [];

    storage: Storage = sessionStorage;

    constructor(
        private formBuilder: FormBuilder,
        private storeFormService: StoreFormService,
        private cartService: CartService,
        private checkoutService: CheckoutService,
        private router: Router
    ) {}

    ngOnInit(): void {
        // update cart details
        this.reviewCartDetails();

        // TODO: read the customer's email from browser storage
        // refactor, and create a Customer model outside and get all values.
        // then prepopulate emaill on checkout submit and names on init !!
        // const theEmail = JSON.parse(this.storage.getItem("userEmail"));

        // check out reactive formgroup
        this.checkoutFormGroup = this.formBuilder.group({
            shippingAddress: this.formBuilder.group({
                fullName: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                street: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                line2: [""],
                city: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                state: new FormControl("", [Validators.required]),
                country: new FormControl("", [Validators.required]),
                zipCode: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
            }),
            billingAddress: this.formBuilder.group({
                fullName: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                street: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                line2: [""],
                city: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                state: new FormControl("", [Validators.required]),
                country: new FormControl("", [Validators.required]),
                zipCode: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
            }),
            paymentCard: this.formBuilder.group({
                cardType: new FormControl("", [Validators.required]),
                nameOnCard: new FormControl("", [
                    Validators.required,
                    Validators.minLength(2),
                    StoreFormValidators.notOnlyWhiteSpace,
                ]),
                cardNumber: new FormControl("", [
                    Validators.required,
                    Validators.pattern("[0-9]{16}"),
                ]),
                securityCode: new FormControl("", [
                    Validators.required,
                    Validators.pattern("[0-9]{3}"),
                ]),
                expirationMonth: [""],
                expirationYear: [""],
            }),
            sameAsShipping: [false], // Set the default value to true
        });

        // populate credit card months and years
        const startMonth: number = new Date().getMonth() + 1;
        this.storeFormService
            .getCreditCardMonths(startMonth)
            .subscribe((data) => (this.creditCardMonths = data));

        this.storeFormService
            .getCreditCardYears()
            .subscribe((data) => (this.creditCardYears = data));

        // populate countries
        this.storeFormService
            .getAllCountries()
            .subscribe((data) => (this.countries = data));
    }

    // getters & setters
    get shippingAddressFullName() {
        return this.checkoutFormGroup.get("shippingAddress.fullName");
    }
    get shippingAddressStreet() {
        return this.checkoutFormGroup.get("shippingAddress.street");
    }
    get shippingAddressCity() {
        return this.checkoutFormGroup.get("shippingAddress.city");
    }
    get shippingAddressState() {
        return this.checkoutFormGroup.get("shippingAddress.state");
    }
    get shippingAddressCountry() {
        return this.checkoutFormGroup.get("shippingAddress.country");
    }
    get shippingAddressZipCode() {
        return this.checkoutFormGroup.get("shippingAddress.zipCode");
    }

    get billingAddressFullName() {
        return this.checkoutFormGroup.get("billingAddress.fullName");
    }
    get billingAddressStreet() {
        return this.checkoutFormGroup.get("billingAddress.street");
    }
    get billingAddressCity() {
        return this.checkoutFormGroup.get("billingAddress.city");
    }
    get billingAddressState() {
        return this.checkoutFormGroup.get("billingAddress.state");
    }
    get billingAddressCountry() {
        return this.checkoutFormGroup.get("billingAddress.country");
    }
    get billingAddressZipCode() {
        return this.checkoutFormGroup.get("billingAddress.zipCode");
    }

    get paymentCardType() {
        return this.checkoutFormGroup.get("paymentCard.cardType");
    }
    get paymentCardNameOnCard() {
        return this.checkoutFormGroup.get("paymentCard.nameOnCard");
    }
    get paymentCardNumber() {
        return this.checkoutFormGroup.get("paymentCard.cardNumber");
    }
    get paymentCardSecurityCode() {
        return this.checkoutFormGroup.get("paymentCard.securityCode");
    }

    // copy shipping address values to billing address
    copyShippingAddressToBillingAddress(event: any): void {
        if (event.target.checked) {
            // copy data to billing address input forms
            this.checkoutFormGroup.controls.billingAddress.setValue(
                this.checkoutFormGroup.controls.shippingAddress.value
            );

            this.billingAddressStates = this.shippingAddressStates;
        } else {
            // reset dropdown menus
            this.checkoutFormGroup.controls.billingAddress.reset();
            this.billingAddressStates = [];
        }
    }

    reviewCartDetails(): void {
        this.cartService.totalQuantity.subscribe(
            (data) => (this.totalQuantity = data)
        );
        this.cartService.totalPrice.subscribe(
            (data) => (this.totalPrice = data)
        );
    }

    handleMonthsAndYears(): void {
        const paymentCardFormGroup = this.checkoutFormGroup.get("paymentCard");

        const currentYear: number = new Date().getFullYear();
        const selectedYear: number = Number(
            paymentCardFormGroup.value.expirationYear
        );

        // if the current year equals the selected year, then only begin with the current month
        // else load all months
        let startMonth: number;

        if (currentYear === selectedYear) {
            startMonth = new Date().getMonth() + 1;
        } else {
            startMonth = 1;
        }

        this.storeFormService
            .getCreditCardMonths(startMonth)
            .subscribe((data) => (this.creditCardMonths = data));
    }

    getStates(formGroupName: string): void {
        // get the form group name
        const formGroup = this.checkoutFormGroup.get(formGroupName);

        // extract the country code and name of the form group
        const countryCode = formGroup.value.country.code;
        const countryName = formGroup.value.country.name;

        console.log(`${formGroupName} country code: ${countryCode}`);
        console.log(`${formGroupName} country name: ${countryName}`);

        this.storeFormService.getAllStates(countryCode).subscribe((data) => {
            if (formGroupName === "shippingAddress") {
                this.shippingAddressStates = data;
            } else {
                this.billingAddressStates = data;
            }

            // select the first state option by default
            formGroup.get("state").setValue(data[0]);
        });
    }

    onSubmit(): void {
        console.log("Handling the submit button");

        // form validation logic
        if (this.checkoutFormGroup.invalid) {
            this.checkoutFormGroup.markAllAsTouched();
            return;
        }

        // setup order
        let order = new Order();
        order.totalPrice = this.totalPrice;
        order.totalQuantity = this.totalQuantity;

        // get cart items
        const cartItems = this.cartService.cartItems;

        // create orderItems from cartItems
        let orderItems: OrderItem[] = cartItems.map(
            (tempCartItem) => new OrderItem(tempCartItem)
        );

        // setup purchase
        let purchase = new Purchase();

        // populate purchase - customer
        //@TODO
        // purchase.customer = this.checkoutFormGroup.controls["customer"].value;

        // TEMP CODE START
        let customer = new Customer();
        customer.firstName = "Donald";
        customer.lastName = "Trump";
        customer.company = "";
        customer.email = "test_user@shirtmoxy.com";
        purchase.customer = customer;
        // TEMP CODE END

        // populate purchase - shipping
        purchase.shippingAddress =
            this.checkoutFormGroup.controls["shippingAddress"].value;
        const shippingState: State = JSON.parse(
            JSON.stringify(purchase.shippingAddress.state)
        );
        const shippingCountry: Country = JSON.parse(
            JSON.stringify(purchase.shippingAddress.country)
        );
        purchase.shippingAddress.state = shippingState.name;
        purchase.shippingAddress.country = shippingCountry.name;

        // populate purchase - billing
        purchase.billingAddress =
            this.checkoutFormGroup.controls["billingAddress"].value;
        const billingState: State = JSON.parse(
            JSON.stringify(purchase.billingAddress.state)
        );
        const billingCountry: Country = JSON.parse(
            JSON.stringify(purchase.billingAddress.country)
        );
        purchase.billingAddress.state = billingState.name;
        purchase.billingAddress.country = billingCountry.name;

        // populate purchase - order & orderItems
        purchase.order = order;
        purchase.orderItems = orderItems;

        //TEMP LOG
        console.log(JSON.stringify(purchase));

        // invoke REST api from checkoutService
        this.checkoutService.placeOrder(purchase).subscribe({
            next: (response) => {
                const trackingNumber = String(response.orderTrackingNumber);

                // reset cart
                this.resetCart();

                // show tracking number on order confirmation page
                this.showTrackingNumberPage(trackingNumber);
            },
            error: (err) => {
                alert(`There was an error: ${err.message}`);
            },
        });
    }

    resetCart(): void {
        // reset cart data
        this.cartService.cartItems = [];
        this.cartService.totalPrice.next(0);
        this.cartService.totalQuantity.next(0);

        // reset the form
        this.checkoutFormGroup.reset();
    }

    showTrackingNumberPage(orderTrackingNumber: string) {
        // navigate to the order confirmation page
        this.router.navigate(["/order-confirmation"], {
            queryParams: { orderTrackingNumber: orderTrackingNumber },
        });
    }
}
