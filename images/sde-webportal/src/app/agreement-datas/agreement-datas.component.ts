import { Component } from '@angular/core';

import { AgreementDatasService } from '../agreement-datas.service';

@Component
({
    selector: 'sde-agreement-datas',
    templateUrl: './agreement-datas.component.html',
    styleUrls: ['./agreement-datas.component.scss'],
    standalone: false
})
export class AgreementDatasComponent
{
    public agreementDataSummaries:          any;
    public selectedAgreementDataSummary:    any;
    public selectedAgreementData:           any;
    public isLoadingAgreementDataSummaries: boolean;
    public isLoadingAgreementData:          boolean;

    public constructor(public agreementDatasService: AgreementDatasService)
    {
        this.agreementDataSummaries          = null;
        this.selectedAgreementDataSummary    = null;
        this.selectedAgreementData           = null;
        this.isLoadingAgreementDataSummaries = false;
        this.isLoadingAgreementData          = false;
    }

    public doReloadAgreementDataSummaries(): void
    {
        this.isLoadingAgreementDataSummaries = true;
        this.agreementDatasService.getAgreementDataSummaries().subscribe((response: any) => { this.processAgreementDataSummaries(response); this.isLoadingAgreementDataSummaries = false });
    }

    private processAgreementDataSummaries(response: any)
    {
        this.agreementDataSummaries = response;
        if (this.agreementDataSummaries != null)
            this.selectedAgreementDataSummary = this.agreementDataSummaries[0];
        else
            this.selectedAgreementDataSummary = null;
        this.doReloadSelectedAgreementData();
    }

    public doSelectAgreementDataSummary(agreementDataSummaryIndex: any)
    {
        this.selectedAgreementDataSummary = this.agreementDataSummaries[agreementDataSummaryIndex];
        this.doReloadSelectedAgreementData()
    }

    public doReloadSelectedAgreementData(): void
    {
        this.isLoadingAgreementData = true;
        console.log(this.selectedAgreementDataSummary.name);
        if (this.selectedAgreementDataSummary.name != null)
            this.agreementDatasService.getAgreementData(this.selectedAgreementDataSummary.name).subscribe((response: any) => { this.processAgreementData(response); this.isLoadingAgreementData = false });
        else
        {
            this.selectedAgreementData  = null;
            this.isLoadingAgreementData = false;
        }           
    }

    private processAgreementData(response: any)
    {
        this.selectedAgreementData = response;
    }
}
