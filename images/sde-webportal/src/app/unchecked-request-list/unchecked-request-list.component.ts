import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { UncheckedRequestMetadata }       from '../unchecked-request-metadata';
import { UncheckedInteractionLogService } from '../unchecked-interaction-log.service';

@Component
({
    selector:    'sde-unchecked-request-list',
    templateUrl: './unchecked-request-list.component.html',
    styleUrls:   ['./unchecked-request-list.component.scss']
})
export class UncheckedRequestListComponent implements OnInit
{
    public selectedUncheckedRequestId: string | null;

    public uncheckedRequestMetadatas: UncheckedRequestMetadata[];
    public uncheckedRequestText:      string | null;
    public operationOutcome:          string | null;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingUncheckedRequestMetadatas: boolean;
    public isLoadingUncheckedRequestText:      boolean;
    public isPerformingUncheckedOperation:     boolean;

    public constructor(private uncheckedInteractionLogService: UncheckedInteractionLogService)
    {
        this.selectedUncheckedRequestId = null;

        this.uncheckedRequestMetadatas = [];
        this.uncheckedRequestText      = "";
        this.operationOutcome          = "";

        this.isLoadingUncheckedRequestMetadatas = false;
        this.isLoadingUncheckedRequestText      = false;
        this.isPerformingUncheckedOperation     = false;
    }

    public ngOnInit(): void
    {
        this.doReloadUncheckedList();
    }

    public doReloadUncheckedList(): void
    {
        this.isLoadingUncheckedRequestMetadatas = true;
        this.uncheckedInteractionLogService.listUncheckedRequests().subscribe((data: any) => { this.uncheckedRequestMetadatas = this.extractUncheckedRequestMetadatas(data); this.isLoadingUncheckedRequestMetadatas = false });
    }

    public doSelectUncheckedRequest(selectedUncheckedRequest: any): void
    {
        this.operationOutcome           = "";
        this.selectedUncheckedRequestId = selectedUncheckedRequest.id;

        this.isLoadingUncheckedRequestText = true;
        if (this.selectedUncheckedRequestId != null)
            this.uncheckedInteractionLogService.getUncheckedRequest(this.selectedUncheckedRequestId).subscribe((data: any) => { this.uncheckedRequestText = data; this.isLoadingUncheckedRequestText = false });
    }

    public doBlockRequest(requestId: string): void
    {
        this.isPerformingUncheckedOperation = true
        this.uncheckedInteractionLogService.blockRequest(requestId).subscribe((data: any) => { this.operationOutcome = data; this.selectedUncheckedRequestId = null; this.uncheckedRequestText = ""; this.isPerformingUncheckedOperation = false; this.doReloadUncheckedList() });
    }

    public doPermitRequest(requestId: string): void
    {
        this.isPerformingUncheckedOperation = true
        this.uncheckedInteractionLogService.permitRequest(requestId).subscribe((data: any) => { this.operationOutcome = data; this.selectedUncheckedRequestId = null; this.uncheckedRequestText = ""; this.isPerformingUncheckedOperation = false; this.doReloadUncheckedList() });
    }

    private extractUncheckedRequestMetadatas(data: any): UncheckedRequestMetadata[]
    {
        var uncheckedRequestMetadatas: UncheckedRequestMetadata[] = [];

        for (let id of data)
           uncheckedRequestMetadatas.push(new UncheckedRequestMetadata(id));

        return uncheckedRequestMetadatas;
    }
}
