import { Component, OnInit } from "@angular/core";
import {
    FormBuilder,
    FormGroup,
    FormControl,
    Validators,
} from "@angular/forms";
import { ContactUsCategory } from "app/models/contact-us-category.model";
import { ContactUs } from "app/models/contact-us.model";
import { ContactUsService } from "app/services/contactus/contactus.service";
import { StoreFormValidators } from "app/validators/store-form-validators";
import Swal from "sweetalert2";

@Component({
    selector: "app-contact",
    templateUrl: "./contact.component.html",
    styleUrls: ["./contact.component.scss"],
})
export class ContactComponent implements OnInit {
    contactUsFormGroup: FormGroup;

    categories: ContactUsCategory[] = [];

    isDisabled: boolean = false;

    constructor(
        private formBuilder: FormBuilder,
        private contactUsService: ContactUsService
    ) {}

    ngOnInit(): void {
        // build reactive contact us form group
        this.contactUsFormGroup = this.formBuilder.group({
            firstName: new FormControl("", [
                Validators.required,
                Validators.minLength(2),
                Validators.maxLength(255),
                StoreFormValidators.notOnlyWhiteSpace,
            ]),
            lastName: new FormControl("", [
                Validators.required,
                Validators.minLength(2),
                Validators.maxLength(255),
                StoreFormValidators.notOnlyWhiteSpace,
            ]),
            email: new FormControl("", [
                Validators.required,
                Validators.maxLength(255),
                Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
                StoreFormValidators.notOnlyWhiteSpace,
            ]),
            phone: new FormControl("", [
                Validators.maxLength(15),
                Validators.pattern(
                    "^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$"
                ),
            ]),
            subject: new FormControl("", [
                Validators.required,
                Validators.minLength(2),
                Validators.maxLength(255),
                StoreFormValidators.notOnlyWhiteSpace,
            ]),
            message: new FormControl("", [
                Validators.required,
                Validators.minLength(2),
                Validators.maxLength(1000),
                StoreFormValidators.notOnlyWhiteSpace,
            ]),
            category: new FormControl("", [Validators.required]),
        });

        // populate contact us categories
        this.contactUsService
            .getAllContactUsCategories()
            .subscribe((data) => (this.categories = data));
    }

    get contactUsFirstName() {
        return this.contactUsFormGroup.get("firstName");
    }
    get contactUsLastName() {
        return this.contactUsFormGroup.get("lastName");
    }
    get contactUsEmail() {
        return this.contactUsFormGroup.get("email");
    }
    get contactUsPhone() {
        return this.contactUsFormGroup.get("phone");
    }
    get contactUsCategory() {
        return this.contactUsFormGroup.get("category");
    }
    get contactUsSubject() {
        return this.contactUsFormGroup.get("subject");
    }
    get contactUsMessage() {
        return this.contactUsFormGroup.get("message");
    }

    onSubmit(): void {
        console.log("Handling the submit button");

        // validate form values
        if (this.contactUsFormGroup.invalid) {
            console.log(
                "contactUsFormGroup is invalid, marking all fields as touched"
            );

            this.contactUsFormGroup.markAllAsTouched();
            return;
        }

        // populate contact us
        let contactUsDetails = new ContactUs();
        contactUsDetails = this.contactUsFormGroup.value;
        console.log(
            "contact us object created: " + JSON.stringify(contactUsDetails)
        );

        // if form is validated
        if (!this.contactUsFormGroup.invalid) {
            console.log("contactUsFormGroup is valid, disabling submit btn");

            // disable the submit btn while data is being processed
            this.isDisabled = true;
            this.contactUsService
                .sendContactUsDetails(contactUsDetails)
                .subscribe({
                    next: (response) => {
                        Swal.fire(
                            `${response}`,
                            "Your message has been successfully received. Our team will review your inquiry and get back to you as soon as possible. \nThank You!",
                            "success"
                        );

                        // reset cart
                        this.resetForm();
                        this.isDisabled = false;
                    },
                    error: (err) => {
                        Swal.fire(
                            `${err.message}`,
                            "An error occured. Please try again later.",
                            "error"
                        );
                        this.isDisabled = false;
                    },
                });
        } else {
            console.log(
                "contactUsFormGroup is invalid, marking all fields as touched"
            );

            this.contactUsFormGroup.markAllAsTouched();
            return;
        }
    }

    resetForm(): void {
        console.log("reseting form");

        // reset the form
        this.contactUsFormGroup.reset();
    }
}
