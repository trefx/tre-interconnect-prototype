import { Component } from '@angular/core';

import { RequestCheckingControlService } from '../request-checking-control.service';

@Component
({
    selector: 'sde-request-checking-control',
    templateUrl: './request-checking-control.component.html',
    styleUrls: ['./request-checking-control.component.scss'],
    standalone: false
})
export class RequestCheckingControlComponent
{
    public requestCheckers:          any;
    public status:                   boolean;
    public outcome:                  any;
    public isLoadingRequestCheckers: boolean;

    public constructor(public requestCheckingControlService: RequestCheckingControlService)
    {
        this.requestCheckers          = [];
        this.status                   = true;
        this.outcome                  = "";
        this.isLoadingRequestCheckers = false;
    }

    public doLoadRequestCheckerList(): void
    {
        this.requestCheckers          = [];
        this.outcome                  = "";
        this.isLoadingRequestCheckers = true;
        this.requestCheckingControlService.requestCheckerList().subscribe((data: any) => { this.requestCheckers = data.checkers; this.outcome = data.outcome; this.isLoadingRequestCheckers = false });
    }

    public doSaveRequestCheckerList(): void
    {
        this.isLoadingRequestCheckers = true;
        this.requestCheckingControlService.saveRequestCheckerList(this.requestCheckers).subscribe((data: any) => { this.outcome = data.outcome; this.isLoadingRequestCheckers = false });
    }
}
