import { Component } from '@angular/core';

import { InteractionLogService } from '../interaction-log.service';

@Component
({
    selector:    'sde-response-list',
    templateUrl: './response-list.component.html',
    styleUrls:   ['./response-list.component.scss']
})
export class ResponseListComponent
{
    public responseIds: string[];

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.responseIds = [];
    }

    public doReload(): void
    {
        this.interactionLogService.listResponses().subscribe((data: any) => { this.responseIds = data });
    }
}
