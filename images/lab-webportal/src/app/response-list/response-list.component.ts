import { Component } from '@angular/core';
import { OnInit }    from '@angular/core';

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

    public responseMetadatas: ResponseMetadata[];
    public responseText:      string;

    public displayedColumns: string[] = [ 'id' ];

    public isLoadingResponseMetadatas: boolean;
    public isLoadingResponseText:      boolean;

    public constructor(private interactionLogService: InteractionLogService)
    {
        this.selectedResponseId = null;

        this.responseMetadatas = [];
        this.responseText      = "";

        this.isLoadingResponseMetadatas = false;
        this.isLoadingResponseText      = false;
    }

    public ngOnInit(): void
    {
        this.doReloadList();
    }

    public doReloadList(): void
    {
        this.isLoadingResponseMetadatas = true;
        this.interactionLogService.listResponses().subscribe((data: any) => { this.responseMetadatas = this.extractResponseMetadatas(data); this.isLoadingResponseMetadatas = false });
    }

    public doSelectResponse(selectedResponse: any): void
    {
        this.selectedResponseId = selectedResponse.id;

        this.isLoadingResponseText = true;
        if (this.selectedResponseId != null)
            this.interactionLogService.getResponse(this.selectedResponseId).subscribe((data: any) => { this.responseText = data; this.isLoadingResponseText = false });
    }

    private extractResponseMetadatas(data: any): ResponseMetadata[]
    {
        var responseMetadatas: ResponseMetadata[] = [];

        for (let id of data)
           responseMetadatas.push(new ResponseMetadata(id));

        return responseMetadatas;
    }
}
