import { ProductColor } from "./product-color.model";
import { Product } from "./product.model";

export class ProductOverview {
    public product: Product;
    public availableColors: ProductColor[];

    constructor(product?: Product, availableColors?: ProductColor[]) {
        this.product = product || new Product();
        this.availableColors = availableColors || [];
    }
}
