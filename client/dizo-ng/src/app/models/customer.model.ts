export class Customer {
    public id: number;
    public firstName: string;
    public lastName: string;
    public company: string;
    public email: string;
    public phoneNumber: string;
    public password: string;
    public role: string;
    public statusCd: string;
    public statusMsg: string;
    public authStatus: string;

    constructor(
        id?: number,
        firstName?: string,
        lastName?: string,
        company?: string,
        email?: string,
        phoneNumber?: string,
        password?: string,
        role?: string,
        statusCd?: string,
        statusMsg?: string,
        authStatus?: string
    ) {
        this.id = id || 0;
        this.firstName = firstName || "";
        this.lastName = lastName || "";
        this.company = company || "";
        this.email = email || "";
        this.phoneNumber = phoneNumber || "";
        this.password = password || "";
        this.role = role || "";
        this.statusCd = statusCd || "";
        this.statusMsg = statusMsg || "";
        this.authStatus = authStatus || "";
    }
}
