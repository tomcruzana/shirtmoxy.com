import { Product } from "./product.model";

export class CartItem {
    public id: number;
    public manufacturer: string;
    public name: string;
    public imageUrl: string;
    public unitPrice: number;
    public quantity: number;

    constructor(product?: Product) {
        this.id = product.id || 0;
        this.manufacturer = product.manufacturer.name || "";
        this.name = product.name || "";
        this.imageUrl = product.productMediaSet[0] || "";
        this.unitPrice = product.unitPrice || 0;
        this.quantity = 1;
    }
}
