export class OrderHistory {
    public id: number;
    public orderTrackingNumber: string;
    // public invoiceNumber: string @TODO;
    public totalPrice: number;
    public totalQuantity: number;
    public dateCreated: Date;
    public status: string;

    constructor(
        id?: number,
        orderTrackingNumber?: string,
        totalPrice?: number,
        totalQuantity?: number,
        dateCreated?: Date,
        status?: string
    ) {
        this.id = id || 0;
        this.orderTrackingNumber = orderTrackingNumber || "";
        this.totalPrice = totalPrice || 0;
        this.totalQuantity = totalQuantity || 0;
        this.dateCreated = dateCreated!;
        this.status = status || "";
    }
}

// "id": 7,
//             "orderTrackingNumber": "ca0cddbc-506d-43c5-8245-d4395eaf5ded",
//             "totalPrice": 326.98,
//             "totalQuantity": 5,
//             "status": "order processed",
//             "dateCreated": "2023-08-26T17:26:15.660+00:00",
//             "lastUpdated": null,
//             "orderItems": [],
//             "customer": null,
//             "shippingAddress": null,
//             "billingAddress": null
