import { Component } from '@angular/core';

import { of, delay } from 'rxjs';

import { RequestFormSummary }               from '../request-form-summary';
import { RequestFormTemplate }              from '../request-form-template';
import { RequestFormService }               from '../request-form.service';
import { TemplatedRequestSubmitterService } from '../templated-request-submitter.service';

@Component
({
    selector:    'sde-templated-request-creator',
    templateUrl: './templated-request-creator.component.html',
    styleUrls:   ['./templated-request-creator.component.scss']
})
export class TemplatedRequestCreatorComponent
{
    public requestSummaries: RequestFormSummary[];
    public templateId:       string;
    public requestTemplate:  any | null;

    public isGettingSummaries: boolean;
    public isGettingTemplate:  boolean;
    public isSubmitting:       boolean;
    public submissionOutcome:  string | null;

    public constructor(private requestService: RequestFormService, private templatedRequestSubmitterService: TemplatedRequestSubmitterService)
    {
        this.requestSummaries = [];
        this.templateId       = "";
        this.requestTemplate  = null;

        this.isGettingSummaries = false;
        this.isGettingTemplate  = false;
        this.isSubmitting       = false;
        this.submissionOutcome  = null;
    }

    public doGetRequestSummaries(): void
    {
        this.isGettingSummaries = true;
        let outcome = this.requestService.listRequestSummaries();
        outcome.subscribe((data: RequestFormSummary[]) => { this.requestSummaries = data; this.isGettingSummaries = false });
    }

    public doSelectTemplate(templateId: string)
    {
        this.templateId = templateId;
    }

    public doGetRequestTemplate(): void
    {
        this.isGettingTemplate = true;
        let outcome = this.requestService.getRequestTemplate(this.templateId);
        outcome.subscribe((data: RequestFormTemplate) => { this.requestTemplate = data; this.isGettingTemplate = false });
    }

    public doCreateRequest(): void
    {
        this.isSubmitting = true;
        let outcome = this.templatedRequestSubmitterService.createRequest(this.templateId);
        outcome.subscribe((data: Object) => { this.submissionOutcome = (data as any).outcome; of([1]).pipe(delay(6000)).subscribe((data: Object) => { this.submissionOutcome = null }); this.isSubmitting = false });
    }
}
