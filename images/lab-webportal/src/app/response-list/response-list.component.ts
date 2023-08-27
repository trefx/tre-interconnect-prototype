import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

import { PageEvent } from '@angular/material/paginator';

import { ResponseMetadata }      from '../response-metadata';
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

    public responseMetadatas:          ResponseMetadata[];
    public displayedResponseMetadatas: ResponseMetadata[];
    public displayedPageIndex:         number;
    public displayedPageSize:          number;
    public responseText:               string;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingResponseMetadatas: boolean;
    public isLoadingResponseText:      boolean;

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.selectedResponseId = null;

        this.responseMetadatas          = [];
        this.displayedResponseMetadatas = [];
        this.displayedPageIndex         = 0;
        this.displayedPageSize          = 10;
        this.responseText               = "";

        this.isLoadingResponseMetadatas = false;
        this.isLoadingResponseText      = false;
    }

    public ngOnInit(): void
    {
        this.doReloadList();
    }

    public doReloadList(): void
    {
        this.responseText               = "";
        this.isLoadingResponseMetadatas = true;
        this.interactionLogService.listResponses().subscribe((data: any) => { this.responseMetadatas = this.extractResponseMetadatas(data); this.displayedResponseMetadatas = this.responseMetadatas.slice(this.displayedPageIndex * this.displayedPageSize, (this.displayedPageIndex + 1) * this.displayedPageSize); this.isLoadingResponseMetadatas = false });
    }

    public doSelectResponse(selectedResponse: any): void
    {
        this.selectedResponseId = selectedResponse.id;

        this.isLoadingResponseText = true;
        if (this.selectedResponseId != null)
            this.interactionLogService.getResponse(this.selectedResponseId).subscribe((data: any) => { this.responseText = data; this.isLoadingResponseText = false });
    }

    public handleResponseMetadatas(event: PageEvent): void
    {
        this.displayedPageIndex         = event.pageIndex;
        this.displayedPageSize          = event.pageSize;
        this.displayedResponseMetadatas = this.responseMetadatas.slice(event.pageIndex * event.pageSize, (event.pageIndex + 1) * event.pageSize);
    }

    private extractResponseMetadatas(data: any): ResponseMetadata[]
    {
        var responseMetadatas: ResponseMetadata[] = [];

        for (let id of data)
           responseMetadatas.push(new ResponseMetadata(id));

        return responseMetadatas;
    }
}
