import { Component } from '@angular/core';

import { ResponseCheckingControlService } from '../response-checking-control.service';

@Component
({
    selector: 'sde-response-checking-control',
    templateUrl: './response-checking-control.component.html',
    styleUrls: ['./response-checking-control.component.scss'],
    standalone: false
})
export class ResponseCheckingControlComponent
{
    public responseCheckers:          any;
    public status:                    boolean;
    public outcome:                   any;
    public isLoadingResponseCheckers: boolean;

    public constructor(public responseCheckingControlService: ResponseCheckingControlService)
    {
        this.responseCheckers          = [];
        this.status                   = true;
        this.outcome                   = "";
        this.isLoadingResponseCheckers = false;
    }

    public doLoadResponseCheckerList(): void
    {
        this.responseCheckers          = [];
        this.outcome                   = "";
        this.isLoadingResponseCheckers = true;
        this.responseCheckingControlService.responseCheckerList().subscribe((data: any) => { this.responseCheckers = data.checkers; this.outcome = data.outcome; this.isLoadingResponseCheckers = false });
    }

    public doSaveResponseCheckerList(): void
    {
        this.isLoadingResponseCheckers = true;
        this.responseCheckingControlService.saveResponseCheckerList(this.responseCheckers).subscribe((data: any) => { this.outcome = data.outcome; this.isLoadingResponseCheckers = false });
    }
}
