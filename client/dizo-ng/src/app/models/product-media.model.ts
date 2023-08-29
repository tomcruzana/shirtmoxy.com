export class ProductMedia {
    public id: number;
    public name: string;
    public url: string;

    constructor(id?: number, name?: string, url?: string) {
        this.id = id || 0;
        this.name = name || "";
        this.url = url || "";
    }
}
