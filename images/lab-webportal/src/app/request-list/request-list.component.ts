import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { InteractionLogService } from '../interaction-log.service';

@Component
({
    selector:    'sde-request-list',
    templateUrl: './request-list.component.html',
    styleUrls:   ['./request-list.component.scss']
})
export class RequestListComponent implements OnInit
{
    public selectedRequestId: string | null;

    public requestIds:  string[];
    public requestText: string;

    public isLoadingRequestIds:  boolean;
    public isLoadingRequestText: boolean;

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.selectedRequestId = null;

        this.requestIds  = [];
        this.requestText = "";

        this.isLoadingRequestIds  = false;
        this.isLoadingRequestText = false;
    }

    public ngOnInit(): void
    {
        this.doReloadList();
    }

    public doReloadList(): void
    {
        this.isLoadingRequestIds = true;
        this.interactionLogService.listRequests().subscribe((data: any) => { this.requestIds = data; this.isLoadingRequestIds = false });
    }

    public doSelectRequest(selectedRequestId: string): void
    {
        this.selectedRequestId = selectedRequestId;

        this.isLoadingRequestText = true;
        this.interactionLogService.listRequests().subscribe((data: any) => { this.requestText = data; this.isLoadingRequestText = false });
    }
}
