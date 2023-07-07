export class User {
    constructor(
        private _id?: number,
        private _firstName?: string,
        private _lastName?: string,
        private _company?: string,
        private _email?: string,
        private _phoneNumber?: string,
        private _password?: string,
        private _role?: string,
        private _statusCd?: string,
        private _statusMsg?: string,
        private _authStatus?: string
    ) {
        this._id = _id || 0;
        this._firstName = _firstName || "";
        this._lastName = _lastName || "";
        this._company = _company || "";
        this._email = _email || "";
        this._phoneNumber = _phoneNumber || "";
        this._password = _password || "";
        this._role = _role || "";
        this._statusCd = _statusCd || "";
        this._statusMsg = _statusMsg || "";
        this._authStatus = _authStatus || "";
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value || 0;
    }

    get firstName(): string {
        return this._firstName;
    }

    set firstName(value: string) {
        this._firstName = value || "";
    }

    get lastName(): string {
        return this._lastName;
    }

    set lastName(value: string) {
        this._lastName = value || "";
    }

    get company(): string {
        return this._company;
    }

    set company(value: string) {
        this._company = value || "";
    }

    get email(): string {
        return this._email;
    }

    set email(value: string) {
        this._email = value || "";
    }

    get phoneNumber(): string {
        return this._phoneNumber;
    }

    set phoneNumber(value: string) {
        this._phoneNumber = value || "";
    }

    get password(): string {
        return this._password;
    }

    set password(value: string) {
        this._password = value || "";
    }

    get role(): string {
        return this._role;
    }

    set role(value: string) {
        this._role = value || "";
    }

    get statusCd(): string {
        return this._statusCd;
    }

    set statusCd(value: string) {
        this._statusCd = value || "";
    }

    get statusMsg(): string {
        return this._statusMsg;
    }

    set statusMsg(value: string) {
        this._statusMsg = value || "";
    }

    get authStatus(): string {
        return this._authStatus;
    }

    set authStatus(value: string) {
        this._authStatus = value || "";
    }
}
