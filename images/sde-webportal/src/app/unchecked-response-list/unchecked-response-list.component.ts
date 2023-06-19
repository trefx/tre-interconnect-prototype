import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { UncheckedResponseMetadata }      from '../unchecked-response-metadata';
import { UncheckedInteractionLogService } from '../unchecked-interaction-log.service';

@Component
({
    selector:    'sde-unchecked-response-list',
    templateUrl: './unchecked-response-list.component.html',
    styleUrls:   ['./unchecked-response-list.component.scss']
})
export class UncheckedResponseListComponent implements OnInit
{
    public selectedUncheckedResponseId: string | null;

    public uncheckedResponseMetadatas: UncheckedResponseMetadata[];
    public uncheckedResponseText:      string | null;
    public operationOutcome:           string | null;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingUncheckedResponseMetadatas: boolean;
    public isLoadingUncheckedResponseText:      boolean;
    public isPerformingUncheckedOperation:      boolean;

    public constructor(private uncheckedInteractionLogService: UncheckedInteractionLogService)
    {
        this.selectedUncheckedResponseId = null;

        this.uncheckedResponseMetadatas = [];
        this.uncheckedResponseText      = "";
        this.operationOutcome           = "";

        this.isLoadingUncheckedResponseMetadatas = false;
        this.isLoadingUncheckedResponseText      = false;
        this.isPerformingUncheckedOperation      = false;
    }

    public ngOnInit(): void
    {
        this.doReloadUncheckedList();
    }

    public doReloadUncheckedList(): void
    {
        this.isLoadingUncheckedResponseMetadatas = true;
        this.uncheckedInteractionLogService.listUncheckedResponses().subscribe((data: any) => { this.uncheckedResponseMetadatas = this.extractUncheckedResponseMetadatas(data); this.isLoadingUncheckedResponseMetadatas = false });
    }

    public doSelectUncheckedResponse(selectedUncheckedResponse: any): void
    {
        this.operationOutcome            = "";
        this.selectedUncheckedResponseId = selectedUncheckedResponse.id;

        this.isLoadingUncheckedResponseText = true;
        if (this.selectedUncheckedResponseId != null)
            this.uncheckedInteractionLogService.getUncheckedResponse(this.selectedUncheckedResponseId).subscribe((data: any) => { this.uncheckedResponseText = data; this.isLoadingUncheckedResponseText = false });
    }

    public doBlockResponse(responseId: string): void
    {
        this.isPerformingUncheckedOperation = true
        this.uncheckedInteractionLogService.blockResponse(responseId).subscribe((data: any) => { this.operationOutcome = data; this.selectedUncheckedResponseId = null; this.uncheckedResponseText = ""; this.isPerformingUncheckedOperation = false; this.doReloadUncheckedList() });
    }

    public doPermitResponse(responseId: string): void
    {
        this.isPerformingUncheckedOperation = true
        this.uncheckedInteractionLogService.permitResponse(responseId).subscribe((data: any) => { this.operationOutcome = data; this.selectedUncheckedResponseId = null; this.uncheckedResponseText = ""; this.isPerformingUncheckedOperation = false; this.doReloadUncheckedList() });
    }

    private extractUncheckedResponseMetadatas(data: any): UncheckedResponseMetadata[]
    {
        var uncheckedResponseMetadatas: UncheckedResponseMetadata[] = [];

        for (let id of data)
           uncheckedResponseMetadatas.push(new UncheckedResponseMetadata(id));

        return uncheckedResponseMetadatas;
    }
}
