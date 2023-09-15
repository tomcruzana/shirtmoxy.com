import { Directive, ElementRef, HostListener } from "@angular/core";

@Directive({
    selector: "[appNumericInputOnly]",
})
export class NumericInputOnlyDirective {
    constructor(private el: ElementRef) {}

    @HostListener("input", ["$event"])
    onInputChange(event: Event) {
        const input = this.el.nativeElement;
        const originalValue = input.value;

        // Remove leading zeros and non-numeric characters
        const newValue = originalValue.replace(/^0+/, "").replace(/\D/g, "");

        // Set a default value of "1" if the input becomes empty
        const finalValue = newValue === "" ? "1" : newValue;

        // Update the input's value and trigger an "input" event
        if (originalValue !== finalValue) {
            input.value = finalValue;
            input.dispatchEvent(new Event("input"));
        }
    }
}
