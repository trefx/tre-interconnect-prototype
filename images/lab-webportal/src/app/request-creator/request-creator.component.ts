import { Component } from '@angular/core';

import { RequestCreatorService } from '../request-creator.service';

@Component
({
    selector:    'sde-request-creator',
    templateUrl: './request-creator.component.html',
    styleUrls:   ['./request-creator.component.scss']
})
export class RequestCreatorComponent
{
    public dataSHIELDPlatformName:    string;
    public dataSHIELDProfileName:     string;
    public dataSHIELDSymbolNamesList: string;
    public dataSHIELDTableNamesList:  string;
    public dataSHIELDWorkspaceName:   string;
    public dataSHIELDRScript:         string;

    public constructor(private requestCreatorService: RequestCreatorService)
    {
        this.dataSHIELDPlatformName    = "caravan";
        this.dataSHIELDProfileName     = "default";
        this.dataSHIELDSymbolNamesList = "D";
        this.dataSHIELDTableNamesList  = "datashield/cnsim/CNSIM1";
        this.dataSHIELDWorkspaceName   = "workspace-1";
        this.dataSHIELDRScript         = "lsDS(NULL,1L)";
    }

    public doCreateRequest(): void
    {
        console.log("###### " + "doCreateRequest" + " ######");
        let outcome = this.requestCreatorService.createRequest(this.dataSHIELDPlatformName, this.dataSHIELDProfileName, this.dataSHIELDSymbolNamesList, this.dataSHIELDTableNamesList, this.dataSHIELDWorkspaceName, this.dataSHIELDRScript);
    }
}
