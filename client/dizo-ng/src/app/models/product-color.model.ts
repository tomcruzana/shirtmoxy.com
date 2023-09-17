export class ProductColor {
    public id: number;
    public name: string;
    public red: string;
    public green: string;
    public blue: string;

    constructor(
        id?: number,
        name?: string,
        red?: string,
        green?: string,
        blue?: string
    ) {
        this.id = id || 0;
        this.name = name || "";
        this.red = red || "";
        this.green = green || "";
        this.blue = blue || "";
    }
}
