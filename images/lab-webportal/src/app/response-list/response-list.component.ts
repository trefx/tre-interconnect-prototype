import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { InteractionLogService } from '../interaction-log.service';

@Component
({
    selector:    'sde-response-list',
    templateUrl: './response-list.component.html',
    styleUrls:   ['./response-list.component.scss']
})
export class ResponseListComponent implements OnInit
{
    public selectedResponseId: string | null;

    public responseIds: string[];
    public responseText: string;

    public isLoadingResponseIds:  boolean;
    public isLoadingResponseText: boolean;

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.selectedResponseId = null;

        this.responseIds  = [];
        this.responseText = "";

        this.isLoadingResponseIds  = false;
        this.isLoadingResponseText = false;
    }

    public ngOnInit(): void
    {
        this.doReloadList();
    }

    public doReloadList(): void
    {
        this.isLoadingResponseIds = true;
        this.interactionLogService.listResponses().subscribe((data: any) => { this.responseIds = data; this.isLoadingResponseIds = false });
    }

    public doSelectResponse(selectedResponseId: string): void
    {
        this.selectedResponseId = selectedResponseId;

        this.isLoadingResponseText = true;
        this.interactionLogService.getResponse(selectedResponseId).subscribe((data: any) => { this.responseText = data; this.isLoadingResponseText = false });
    }
}
