import { Component, EventEmitter, Output } from "@angular/core";
import { PageSizeEvent } from "app/models/interfaces/page-size-event";

@Component({
    selector: "app-product-result-filter",
    templateUrl: "./product-result-filter.component.html",
    styleUrls: ["./product-result-filter.component.scss"],
})
export class ProductResultFilterComponent {
    // pageable event emitter
    @Output() public newPageSizeEvent = new EventEmitter<PageSizeEvent>();

    // update page size
    updatePageSize(size: string) {
        // emit then convert the size val to a number & refresh the page # back to 1
        this.newPageSizeEvent.emit({ size: +size, page: 1 });
    }
}
