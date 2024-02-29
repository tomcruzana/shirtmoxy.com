import { Component } from "@angular/core";
import {
    FormBuilder,
    FormControl,
    FormGroup,
    Validators,
} from "@angular/forms";
import { Country } from "app/models/country.model";
import { State } from "app/models/state.model";
import { StoreFormService } from "app/services/form/store-form.service";
import { StoreFormValidators } from "app/validators/store-form-validators";

@Component({
    selector: "app-user-address",
    templateUrl: "./user-address.component.html",
    styleUrls: ["./user-address.component.scss"],
})
export class UserAddressComponent {
    checkoutFormGroup: FormGroup;

    countries: Country[] = [];

    shippingAddressStates: State[] = [];
    billingAddressStates: State[] = [];

    isDisabled: boolean = false;

    constructor(
        private formBuilder: FormBuilder,
        private storeFormService: StoreFormService
    ) {}

    ngOnInit(): void {
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
        });

        // populate countries
        this.storeFormService
            .getAllCountries()
            .subscribe((data) => (this.countries = data));
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

    onSubmit(): void {
      
    }
}
