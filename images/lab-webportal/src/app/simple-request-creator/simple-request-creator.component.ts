import { Component } from '@angular/core';

import { of, delay } from 'rxjs';

import { SimpleRequestSubmitterService } from '../simple-request-submitter.service';

@Component
({
    selector:    'sde-simple-request-creator',
    templateUrl: './simple-request-creator.component.html',
    styleUrls:   ['./simple-request-creator.component.scss']
})
export class SimpleRequestCreatorComponent
{
    public isSubmitting:      boolean;
    public submissionOutcome: string | null;

    public constructor(private simpleRequestSubmitterService: SimpleRequestSubmitterService)
    {
        this.isSubmitting      = false;
        this.submissionOutcome = null;
    }

    public doCreateRequest(): void
    {
        this.isSubmitting = true;
        let outcome = this.simpleRequestSubmitterService.createRequest();
        outcome.subscribe((data: Object) => { this.submissionOutcome = (data as any).outcome; of([1]).pipe(delay(6000)).subscribe((data: Object) => { this.submissionOutcome = null }); this.isSubmitting = false });
    }
}
