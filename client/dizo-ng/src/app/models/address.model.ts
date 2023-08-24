export class Address {
    public street: string;
    public line2: string;
    public state: string;
    public city: string;
    public country: string;
    public zipCode: string;

    constructor(
        street?: string,
        line2?: string,
        state?: string,
        city?: string,
        country?: string,
        zipCode?: string
    ) {
        this.street = street || "";
        this.line2 = line2 || "";
        this.state = state || "";
        this.city = city || "";
        this.country = country || "";
        this.zipCode = zipCode || "";
    }
}
