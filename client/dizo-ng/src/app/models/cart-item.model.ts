import { Product } from "./product.model";

export class CartItem {
    public id: number;
    public name: string;
    public imageUrl: string;
    public unitPrice: number;
    public quantity: number;

    constructor(product?: Product) {
        this.id = product.id || 0;
        this.name = product.name || "";
        this.imageUrl = product.productMediaSet || "";
        this.unitPrice = product.unitPrice || 0;
        this.quantity = 1;
    }
}
