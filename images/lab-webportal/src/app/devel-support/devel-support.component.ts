import { Component } from '@angular/core';

import { DevelSupportService } from '../devel-support.service';

@Component
({
    selector:    'sde-devel-support',
    templateUrl: './devel-support.component.html',
    styleUrls:   ['./devel-support.component.scss']
})
export class DevelSupportComponent
{
    public reloadTemplatesMessage: string | null;
    public emptyRequestsMessage:   string | null;
    public emptyResponsesMessage:  string | null;
    public reloadProvidersMessage: string | null;
    public isReloadingTemplates:   boolean;
    public isEmptyingRequests:     boolean;
    public isEmptyingResponses:    boolean;
    public isReloadingProviders:   boolean;

    public constructor(private develSupportService: DevelSupportService)
    {
        this.reloadTemplatesMessage = null;
        this.emptyRequestsMessage   = null;
        this.emptyResponsesMessage  = null;
        this.reloadProvidersMessage = null;
        this.isReloadingTemplates   = false;
        this.isEmptyingRequests     = false;
        this.isEmptyingResponses    = false;
        this.isReloadingProviders   = false;
    }

    public doReloadTemplates(): void
    {
        this.isReloadingTemplates = true;
        this.develSupportService.reloadTemplates().subscribe((data: Object) => { this.reloadTemplatesMessage = (data as any).outcome; this.isReloadingTemplates = false });
    }

    public doEmptyRequests(): void
    {
        this.isEmptyingRequests = true;
        this.develSupportService.emptyRequests().subscribe((data: Object) => { this.emptyRequestsMessage = (data as any).outcome; this.isEmptyingRequests = false });
    }

    public doEmptyResponses(): void
    {
        this.isEmptyingResponses = true;
        this.develSupportService.emptyResponses().subscribe((data: Object) => { this.emptyResponsesMessage = (data as any).outcome; this.isEmptyingResponses = false });
    }

    public doReloadProviders(): void
    {
        this.isReloadingProviders = true;
        this.develSupportService.reloadProviders().subscribe((data: Object) => { this.reloadProvidersMessage = (data as any).outcome; this.isReloadingProviders = false });
    }
}
