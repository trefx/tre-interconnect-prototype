import { Component } from '@angular/core';

import { RequestSubmitterService } from '../request-submitter.service';

@Component
({
    selector:    'sde-request-creator',
    templateUrl: './request-creator.component.html',
    styleUrls:   ['./request-creator.component.scss']
})
export class RequestCreatorComponent
{
    public templateID: string;

    public constructor(private requestSubmitterService: RequestSubmitterService)
    {
        this.templateID = "";
    }

    public doCreateRequest(): void
    {
        let outcome = this.requestSubmitterService.createRequest(this.templateID);
    }
}
