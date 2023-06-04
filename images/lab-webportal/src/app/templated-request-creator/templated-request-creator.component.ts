import { Component } from '@angular/core';

import { of, delay } from 'rxjs';

import { TemplatedRequestSubmitterService } from '../templated-request-submitter.service';

@Component
({
    selector:    'sde-templated-request-creator',
    templateUrl: './templated-request-creator.component.html',
    styleUrls:   ['./templated-request-creator.component.scss']
})
export class TemplatedRequestCreatorComponent
{
    public templateID: string;

    public isSubmitting:      boolean;
    public submissionOutcome: string | null;

    public constructor(private templatedRequestSubmitterService: TemplatedRequestSubmitterService)
    {
        this.templateID = "";

        this.isSubmitting      = false;
        this.submissionOutcome = null;
    }

    public doCreateRequest(): void
    {
        this.isSubmitting = true;
        let outcome = this.templatedRequestSubmitterService.createRequest(this.templateID);
        outcome.subscribe((data: Object) => { this.submissionOutcome = (data as any).outcome; of([1]).pipe(delay(6000)).subscribe((data: Object) => { this.submissionOutcome = null }); this.isSubmitting = false });
    }
}
