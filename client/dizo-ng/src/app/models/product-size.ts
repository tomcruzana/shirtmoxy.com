export class ProductSize {
    public id: number;
    public type: string;
    public length: string;
    public width: string;

    constructor(id?: number, type?: string, length?: string, width?: string) {
        this.id = id || 0;
        this.type = type || "";
        this.length = length || "";
        this.width = width || "";
    }
}
