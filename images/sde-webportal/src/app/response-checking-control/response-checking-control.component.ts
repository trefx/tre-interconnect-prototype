import { Component } from '@angular/core';

import { ResponseCheckingControlService } from '../response-checking-control.service';

@Component
({
    selector:    'sde-response-checking-control',
    templateUrl: './response-checking-control.component.html',
    styleUrls:   ['./response-checking-control.component.scss']
})
export class ResponseCheckingControlComponent
{
    public responseCheckers:          any;
    public isLoadingResponseCheckers: boolean;

    public constructor(public responseCheckingControlService: ResponseCheckingControlService)
    {
        this.responseCheckers          = [];
        this.isLoadingResponseCheckers = false;
    }

    public doLoadResponseCheckerList(): void
    {
        this.isLoadingResponseCheckers = true;
        this.responseCheckingControlService.responseCheckerList().subscribe((data: any) => { this.responseCheckers = data; this.isLoadingResponseCheckers = false });
    }
}
