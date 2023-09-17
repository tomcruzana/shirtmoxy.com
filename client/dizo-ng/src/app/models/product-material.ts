export class ProductMaterial {
    public id: number;
    public type: string;

    constructor(id?: number, type?: string) {
        this.id = id || 0;
        this.type = type || "";
    }
}
