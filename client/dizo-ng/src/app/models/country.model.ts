export class Country {
    public id: number;
    public code: string;
    public name: string;

    constructor(id?: number, code?: string, name?: string){
        this.id = id || 0;
        this.code = code || "";
        this.name = name || "";
    }
}
