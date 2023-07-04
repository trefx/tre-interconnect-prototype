import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

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

    public requestMetadatas:          RequestMetadata[];
    public displayedRequestMetadatas: RequestMetadata[];
    public displayedPageIndex:        number;
    public displayedPageSize:         number;
    public requestText:               string;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingRequestMetadatas: boolean;
    public isLoadingRequestText:      boolean;

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.selectedRequestId = null;

        this.requestMetadatas          = [];
        this.displayedRequestMetadatas = [];
        this.displayedPageIndex        = 0;
        this.displayedPageSize         = 10;
        this.requestText               = "";

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
        this.interactionLogService.listRequests().subscribe((data: any) => { this.requestMetadatas = this.extractRequestMetadatas(data); this.displayedRequestMetadatas = this.requestMetadatas.slice(this.displayedPageIndex * this.displayedPageSize, (this.displayedPageIndex + 1) * this.displayedPageSize); this.isLoadingRequestMetadatas = false });
    }

    public doSelectRequest(selectedRequest: any): void
    {
        this.selectedRequestId = selectedRequest.id;

        this.isLoadingRequestText = true;
        if (this.selectedRequestId != null)
            this.interactionLogService.getRequest(this.selectedRequestId).subscribe((data: any) => { this.requestText = data; this.isLoadingRequestText = false });
    }

    public handleRequestMetadatas(event: PageEvent): void
    {
        this.displayedPageIndex        = event.pageIndex;
        this.displayedPageSize         = event.pageSize;
        this.displayedRequestMetadatas = this.requestMetadatas.slice(event.pageIndex * event.pageSize, (event.pageIndex + 1) * event.pageSize);
    }

    private extractRequestMetadatas(data: any): RequestMetadata[]
    {
        var requestMetadatas: RequestMetadata[] = [];

        for (let id of data)
           requestMetadatas.push(new RequestMetadata(id));

        return requestMetadatas;
    }
}
