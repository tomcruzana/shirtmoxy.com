export class PaymentInfo {
    public amount: number;
    public currency: string;
    public emailReceipt: string;

    constructor(amount?: number, currency?: string, emailReceipt?: string) {
        this.amount = amount || 0;
        this.currency = currency || "";
        this.emailReceipt = emailReceipt || "";
    }
}
