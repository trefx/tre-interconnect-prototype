import { Component } from '@angular/core';

import { InteractionLogService } from '../interaction-log.service';

@Component
({
    selector:    'sde-request-list',
    templateUrl: './request-list.component.html',
    styleUrls:   ['./request-list.component.scss']
})
export class RequestListComponent
{
    public requestIds: string[];

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.requestIds = [];
    }

    public doReload(): void
    {
        this.interactionLogService.listRequests().subscribe((data: any) => { this.requestIds = data });
    }
}
