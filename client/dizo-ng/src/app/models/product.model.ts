import { ProductManufacturer } from "./product-manufacturer.model";
import { ProductMedia } from "./product-media.model";

export class Product {
    public id: number;
    public dateCreated: Date;
    public lastUpdated: Date;
    public name: string;
    public description: string;
    public manufacturer: ProductManufacturer;
    public sku: string;
    public unitPrice: number;
    public weight: number;
    public unitsInStock: number;
    public isActive: boolean;
    public productMediaSet: ProductMedia;
    // public barcode: string;
    // public category: string;
    // public variant: string;

    constructor(
        id?: number,
        dateCreated?: Date,
        lastUpdated?: Date,
        name?: string,
        description?: string,
        manufacturer?: ProductManufacturer,
        sku?: string,
        unitPrice?: number,
        weight?: number,
        unitsInStock?: number,
        isActive?: boolean,
        productMediaSet?: ProductMedia
    ) {
        this.id = id || 0;
        this.dateCreated = dateCreated!;
        this.lastUpdated = lastUpdated!;
        this.name = name || "";
        this.description = description || "";
        this.manufacturer = manufacturer || new ProductManufacturer();
        this.sku = sku || "";
        this.unitPrice = unitPrice || 0;
        this.weight = weight || 0;
        this.unitsInStock = unitsInStock || 0;
        this.isActive = isActive!;
        this.productMediaSet = productMediaSet || new ProductMedia();
    }
}
