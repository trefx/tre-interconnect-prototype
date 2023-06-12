import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { RequestMetadata }       from '../request-metadata';
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

    public requestMetadatas: RequestMetadata[];
    public requestText:      string;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingRequestMetadatas: boolean;
    public isLoadingRequestText:      boolean;

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.selectedRequestId = null;

        this.requestMetadatas = [];
        this.requestText      = "";

        this.isLoadingRequestMetadatas = false;
        this.isLoadingRequestText      = false;
    }

    public ngOnInit(): void
    {
        this.doReloadList();
    }

    public doReloadList(): void
    {
        this.isLoadingRequestMetadatas = true;
        this.interactionLogService.listRequests().subscribe((data: any) => { this.requestMetadatas = this.extractRequestMetadatas(data); this.isLoadingRequestMetadatas = false });
    }

    public doSelectRequest(selectedRequest: any): void
    {
        this.selectedRequestId = selectedRequest.id;

        this.isLoadingRequestText = true;
        if (this.selectedRequestId != null)
            this.interactionLogService.getRequest(this.selectedRequestId).subscribe((data: any) => { this.requestText = data; this.isLoadingRequestText = false });
    }

    private extractRequestMetadatas(data: any): RequestMetadata[]
    {
        var requestMetadatas: RequestMetadata[] = [];

        for (let id of data)
           requestMetadatas.push(new RequestMetadata(id));

        return requestMetadatas;
    }
}
