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
import { PaymentInfo } from "app/models/payment-info.model";
import { Purchase } from "app/models/purchase.model";
import { State } from "app/models/state.model";
import { CartService } from "app/services/cart/cart.service";
import { CheckoutService } from "app/services/checkout/checkout.service";
import { StoreFormService } from "app/services/form/store-form.service";
import { StoreFormValidators } from "app/validators/store-form-validators";
import { environment } from "environments/environment.development";

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

    // init Stripe API
    stripe = Stripe(environment.stripePublishableKey);

    paymentInfo: PaymentInfo = new PaymentInfo();
    cardElement: any;
    displayError: any = "";

    isDisabled: boolean = false;

    constructor(
        private formBuilder: FormBuilder,
        private storeFormService: StoreFormService,
        private cartService: CartService,
        private checkoutService: CheckoutService,
        private router: Router
    ) {}

    ngOnInit(): void {
        // setup the Stripe payment form
        this.setupStripePaymentForm();

        // update cart details
        this.reviewCartDetails();

        // TODO: read the customer's email from browser storage
        // refactor, and create a Customer model outside and get all values.
        // then prepopulate emaill on checkout submit and names on init !!
        // email: new FormControl(theEmail,
        //     [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])
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
                // this form group will hold stripe elements on the fly
            }),
            sameAsShipping: [false], // Set the default value to true
        });

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

    setupStripePaymentForm() {
        // handle stripe elements
        var elements = this.stripe.elements();

        // create card elements
        this.cardElement = elements.create("card", { hidePostalCode: true });

        // inject the elements into the view element
        this.cardElement.mount("#card-element");

        // add change event binding
        this.cardElement.on("change", (event: any) => {
            // handle stripe error elements
            this.displayError = document.getElementById("card-errors");

            if (event.complete) {
                this.displayError.textContent = "";
            } else if (event.error) {
                // show validation error
                this.displayError.textContent = event.error.message;
            }
        });
    }

    reviewCartDetails(): void {
        this.cartService.totalQuantity.subscribe(
            (data) => (this.totalQuantity = data)
        );
        this.cartService.totalPrice.subscribe(
            (data) => (this.totalPrice = data)
        );
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

        // compute payment & set email recipient
        this.paymentInfo.amount = Math.round(this.totalPrice * 100);
        this.paymentInfo.currency = "USD";
        this.paymentInfo.emailReceipt = purchase.customer.email;

        /**
         * validate form
         * then create a payment intent,
         * confirm card payment
         * & place order
         **/

        if (
            !this.checkoutFormGroup.invalid &&
            this.displayError.textContent === ""
        ) {
            this.isDisabled = true;

            this.checkoutService
                .createPaymentIntent(this.paymentInfo)
                .subscribe((paymentIntentResponse) => {
                    this.stripe
                        .confirmCardPayment(
                            paymentIntentResponse.client_secret,
                            {
                                payment_method: {
                                    card: this.cardElement,
                                    billing_details: {
                                        email: purchase.customer.email,
                                        name: `${purchase.customer.firstName} ${purchase.customer.lastName}`,
                                        address: {
                                            line1: purchase.billingAddress
                                                .street,
                                            line2: purchase.billingAddress
                                                .line2,
                                            city: purchase.billingAddress.city,
                                            state: purchase.billingAddress
                                                .state,
                                            postal_code:
                                                purchase.billingAddress.zipCode,
                                            country:
                                                this.billingAddressCountry.value
                                                    .code,
                                        },
                                    },
                                },
                            },
                            { handleActions: false }
                        )
                        .then(
                            function (result) {
                                if (result.error) {
                                    // inform the customer there was an error
                                    alert(
                                        `There was an error: ${result.error.message}`
                                    );
                                    this.isDisabled = false;
                                } else {
                                    // call REST API via the CheckoutService
                                    this.checkoutService
                                        .placeOrder(purchase)
                                        .subscribe({
                                            next: (response) => {
                                                alert(
                                                    `Your order has been received.\nOrder tracking number: ${response.orderTrackingNumber}`
                                                );

                                                // reset cart
                                                this.resetCart();
                                                this.isDisabled = false;
                                                this.showTrackingNumberPage(
                                                    response.orderTrackingNumber
                                                );
                                            },
                                            error: (err) => {
                                                alert(
                                                    `There was an error: ${err.message}`
                                                );
                                                this.isDisabled = false;
                                            },
                                        });
                                }
                            }.bind(this)
                        );
                });
        } else {
            this.checkoutFormGroup.markAllAsTouched();
            return;
        }
    }

    resetCart(): void {
        // reset cart data
        this.cartService.cartItems = [];
        this.cartService.totalPrice.next(0);
        this.cartService.totalQuantity.next(0);
        this.cartService.persistCartItems();

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
