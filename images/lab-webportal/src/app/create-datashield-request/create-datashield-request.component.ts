import { Component } from '@angular/core';

import { CreateDataSHIELDRequestService } from '../create-datashield-request.service';

@Component
({
    selector:    'sde-create-datashield-request',
    templateUrl: './create-datashield-request.component.html',
    styleUrls:   ['./create-datashield-request.component.scss']
})
export class CreateDataSHIELDRequestComponent
{
    public dataSHIELDPlatformName:    string;
    public dataSHIELDProfileName:     string;
    public dataSHIELDSymbolNamesList: string;
    public dataSHIELDTableNamesList:  string;
    public dataSHIELDWorkspaceName:   string;
    public dataSHIELDRScript:         string;

    public constructor(private createDataSHIELDRequestService: CreateDataSHIELDRequestService)
    {
        this.dataSHIELDPlatformName    = "caravan";
        this.dataSHIELDProfileName     = "default";
        this.dataSHIELDSymbolNamesList = "D";
        this.dataSHIELDTableNamesList  = "datashield/cnsim/CNSIM1";
        this.dataSHIELDWorkspaceName   = "workspace-1";
        this.dataSHIELDRScript         = "lsDS(NULL,1L)";
    }

    public doCreateDataSHIELDRequest(): void
    {
        let outcome = this.createDataSHIELDRequestService.createDataSHIELDRequest(this.dataSHIELDPlatformName, this.dataSHIELDProfileName, this.dataSHIELDSymbolNamesList, this.dataSHIELDTableNamesList, this.dataSHIELDWorkspaceName, this.dataSHIELDRScript);
    }
}
