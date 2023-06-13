import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { UncheckedRequestMetadata }       from '../unchecked-request-metadata';
import { UncheckedInteractionLogService } from '../unchecked-interaction-log.service';

@Component
({
    selector:    'sde-unchecked-request-list',
    templateUrl: './unckecked-request-list.component.html',
    styleUrls:   ['./unchecked-request-list.component.scss']
})
export class UncheckedRequestListComponent implements OnInit
{
    public selectedUncheckedRequestId: string | null;

    public uncheckedRequestMetadatas: UncheckedRequestMetadata[];
    public uncheckedRequestText:      string;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingUncheckedRequestMetadatas: boolean;
    public isLoadingUncheckedRequestText:      boolean;

    public constructor(private uncheckedInteractionLogService: UncheckedInteractionLogService)
    {
        this.selectedUncheckedRequestId = null;

        this.uncheckedRequestMetadatas = [];
        this.uncheckedRequestText      = "";

        this.isLoadingUncheckedRequestMetadatas = false;
        this.isLoadingUncheckedRequestText      = false;
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
        this.selectedUncheckedRequestId = selectedUncheckedRequest.id;

        this.isLoadingUncheckedRequestText = true;
        if (this.selectedUncheckedRequestId != null)
            this.uncheckedInteractionLogService.getUncheckedRequest(this.selectedUncheckedRequestId).subscribe((data: any) => { this.uncheckedRequestText = data; this.isLoadingUncheckedRequestText = false });
    }

    private extractUncheckedRequestMetadatas(data: any): UncheckedRequestMetadata[]
    {
        var uncheckedRequestMetadatas: UncheckedRequestMetadata[] = [];

        for (let id of data)
           uncheckedRequestMetadatas.push(new UncheckedRequestMetadata(id));

        return uncheckedRequestMetadatas;
    }
}
