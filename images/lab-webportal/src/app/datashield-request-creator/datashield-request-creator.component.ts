import { Component } from '@angular/core';

import { of, delay } from 'rxjs';

import { DataSHIELDRequestSubmitterService } from '../datashield-request-submitter.service';

@Component
({
    selector:    'sde-datashield-request-creator',
    templateUrl: './datashield-request-creator.component.html',
    styleUrls:   ['./datashield-request-creator.component.scss']
})
export class DataSHIELDRequestCreatorComponent
{
    public dataSHIELDPlatformName:    string;
    public dataSHIELDProfileName:     string;
    public dataSHIELDSymbolNamesList: string;
    public dataSHIELDTableNamesList:  string;
    public dataSHIELDWorkspaceName:   string;
    public dataSHIELDRScript:         string;

    public isSubmitting: boolean;
    public submissionOutcome: string | null;

    public constructor(private requestDataSHIELDSubmitterService: DataSHIELDRequestSubmitterService)
    {
        this.dataSHIELDPlatformName    = "caravan";
        this.dataSHIELDProfileName     = "default";
        this.dataSHIELDSymbolNamesList = "D";
        this.dataSHIELDTableNamesList  = "datashield/cnsim/CNSIM1";
        this.dataSHIELDWorkspaceName   = "workspace-1";
        this.dataSHIELDRScript         = "lsDS(NULL,1L)";

        this.isSubmitting      = false;
        this.submissionOutcome = null;
    }

    public doCreateRequest(): void
    {
        this.isSubmitting = true;
        let outcome = this.requestDataSHIELDSubmitterService.createRequest(this.dataSHIELDPlatformName, this.dataSHIELDProfileName, this.dataSHIELDSymbolNamesList, this.dataSHIELDTableNamesList, this.dataSHIELDWorkspaceName, this.dataSHIELDRScript);
        outcome.subscribe((data: Object) => { this.submissionOutcome = (data as any).outcome; of([1]).pipe(delay(6000)).subscribe((data: Object) => { this.submissionOutcome = null }); this.isSubmitting = false });
    }
}
