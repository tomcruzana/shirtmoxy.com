import { ProductCategory } from "./product-category.model";
import { ProductColor } from "./product-color.model";
import { ProductGender } from "./product-gender.model";
import { ProductManufacturer } from "./product-manufacturer.model";
import { ProductMaterial } from "./product-material";
import { ProductMedia } from "./product-media.model";
import { ProductSize } from "./product-size";

export class Product {
    public id: number;
    public dateCreated: Date;
    public lastUpdated: Date;
    public productMediaSet: ProductMedia;
    public sku: string;
    public name: string;
    public description: string;
    public unitPrice: number;
    public weight: number;
    public unitsInStock: number;
    public isActive: boolean;
    public barcode: null;
    public productType: null;
    public category: ProductCategory;
    public color: ProductColor;
    public manufacturer: ProductManufacturer;
    public gender : ProductGender;
    public size : ProductSize;
    public material : ProductMaterial;

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
