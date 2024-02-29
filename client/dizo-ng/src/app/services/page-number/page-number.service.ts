import { EventEmitter, Injectable } from "@angular/core";

@Injectable({
    providedIn: "root",
})
export class PageNumberService {
    resetPageNumberEvent = new EventEmitter<number>();

    constructor() {}

    emitResetPageNumberEvent(pageNumber: number) {
        this.resetPageNumberEvent.emit(pageNumber);
    }
}
