import { ContactUsCategory } from "./contact-us-category.model";

export class ContactUs {
    public firstName: string;
    public lastName: string;
    public email: string;
    public phone: string;
    public contactUsCategory: ContactUsCategory;
    public subject: string;
    public message: string;
    public dateCreated: Date;
    public lastUpdated: Date;

    constructor(
        firstName?: string,
        lastName?: string,
        email?: string,
        phone?: string,
        contactUsCategory?: ContactUsCategory,
        subject?: string,
        message?: string,
    ) {
        this.firstName = firstName || "";
        this.lastName = lastName || "";
        this.email = email || "";
        this.phone = phone || "";
        this.contactUsCategory = contactUsCategory || new ContactUsCategory();
        this.subject = subject || "";
        this.message = message || "";
    }
}
