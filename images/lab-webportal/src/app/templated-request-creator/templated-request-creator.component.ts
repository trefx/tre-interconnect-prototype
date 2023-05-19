import { Component } from '@angular/core';

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

    public isSubmitting: boolean;

    public constructor(private templatedRequestSubmitterService: TemplatedRequestSubmitterService)
    {
        this.templateID = "";

        this.isSubmitting = false;
    }

    public doCreateRequest(): void
    {
        this.isSubmitting = true;
        let outcome = this.templatedRequestSubmitterService.createRequest(this.templateID);
        outcome.subscribe(() => { this.isSubmitting = false });
    }
}
