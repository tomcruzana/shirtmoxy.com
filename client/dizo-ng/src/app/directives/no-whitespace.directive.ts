import { Directive, HostListener } from "@angular/core";

@Directive({
    selector: "[appNoWhitespace]",
})
export class NoWhitespaceDirective {
    @HostListener("input", ["$event"]) onInput(event: Event): void {
        const inputElement = event.target as HTMLInputElement;
        const inputValue = inputElement.value;

        // Remove leading whitespace but allow trailing whitespace
        const trimmedValue = inputValue.replace(/^\s+/, "");

        // Update the input field with the trimmed value
        if (inputValue !== trimmedValue) {
            inputElement.value = trimmedValue;
            inputElement.dispatchEvent(new Event("input"));
        }
    }
}
