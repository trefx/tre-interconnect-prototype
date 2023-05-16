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

    public constructor(private templatedRequestSubmitterService: TemplatedRequestSubmitterService)
    {
        this.templateID = "";
    }

    public doCreateRequest(): void
    {
        let outcome = this.templatedRequestSubmitterService.createRequest(this.templateID);
    }
}
