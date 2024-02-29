import { EventEmitter, Injectable } from "@angular/core";
import { ActivatedRoute, NavigationExtras, Router } from "@angular/router";

@Injectable({
    providedIn: "root",
})
export class ProductFilterService {
    constructor(private route: ActivatedRoute, private router: Router) {}

    addOrRemove(
        name: string,
        theFilter: string[] | string,
        paramName: string
    ): void {
        if (Array.isArray(theFilter)) {
            const index = theFilter.indexOf(name);

            if (index !== -1) {
                // If the name is already in the array, remove it
                theFilter.splice(index, 1);
            } else {
                // If the name is not in the array, add it
                theFilter.push(name);
            }
        } else if (typeof theFilter === "string") {
            // Handle the case when theFilter is a string
            // Simply set it to the new name
            theFilter = name;
        }

        // Construct the updated query parameters
        const queryParams: NavigationExtras = {
            relativeTo: this.route,
            queryParams: {
                [paramName]: Array.isArray(theFilter)
                    ? theFilter.join(",")
                    : theFilter,
            },
            queryParamsHandling: "merge", // Merge the new parameters with existing ones
        };

        // Navigate to the updated URL with the new query parameters
        this.router.navigate([], queryParams);
    }
}
