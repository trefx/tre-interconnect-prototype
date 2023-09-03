import { Component } from '@angular/core';

import { RequestCheckingControlService } from '../request-checking-control.service';

@Component
({
    selector:    'sde-request-checking-control',
    templateUrl: './request-checking-control.component.html',
    styleUrls:   ['./request-checking-control.component.scss']
})
export class RequestCheckingControlComponent
{
    public requestCheckers:          any;
    public isLoadingRequestCheckers: boolean;

    public constructor(public requestCheckingControlService: RequestCheckingControlService)
    {
        this.requestCheckers          = [];
        this.isLoadingRequestCheckers = false;
    }

    public doLoadRequestCheckerList(): void
    {
        this.isLoadingRequestCheckers = true;
        this.requestCheckingControlService.requestCheckerList().subscribe((data: any) => { this.requestCheckers = data; this.isLoadingRequestCheckers = false });
    }
}
