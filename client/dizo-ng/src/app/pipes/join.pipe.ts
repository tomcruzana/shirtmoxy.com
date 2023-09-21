import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: "join",
})
export class JoinPipe implements PipeTransform {
    /**
     * Transforms an array of objects into a comma-separated or custom-separated list based on a specified field.
     *
     * @param inputArray - The input array of objects.
     * @param field - The field from each object to include in the list.
     * @param separator - (Optional) The separator character(s) to use between list elements (default is ', ').
     *
     * @example
     * Usage examples:
     * - {{ yourArray | join:'type' }} // Uses the default separator (', ') to join the 'type' field.
     * - {{ yourArray | join:'type':' | ' }} // Uses a custom separator (' | ') to join the 'type' field.
     * - {{ yourArray | join:'length':' - ' }} // Joins the 'length' field with a custom separator (' - ').
     */

    transform(
        inputArray: any[],
        field: string,
        separator: string = ", "
    ): string {
        if (!inputArray || inputArray.length === 0) {
            return "";
        }

        const list = inputArray.map((item) => item[field]);

        return list.join(separator).toUpperCase();
    }
}
