import { Component } from '@angular/core';

import { AgreementDatasService } from '../agreement-datas.service';

@Component
({
    selector:    'sde-agreement-datas',
    templateUrl: './agreement-datas.component.html',
    styleUrls:   ['./agreement-datas.component.scss']
})
export class AgreementDatasComponent
{
    public agreementDatas:                  any;
    public selectedAgreementData:           any;
    public isLoadingAgreementDataSummaries: boolean;
    public isLoadingAgreementData:          boolean;

    public constructor(public agreementDatasService: AgreementDatasService)
    {
        this.agreementDatas                  = null;
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
        this.agreementDatas = response;
        if (this.agreementDatas != null)
            this.selectedAgreementData = this.agreementDatas[0];
        else
            this.selectedAgreementData = null;
        this.doReloadAgreementData();
    }

    public doSelectAgreementData(agreementDataIndex: any)
    {
        this.selectedAgreementData = this.agreementDatas[agreementDataIndex];
        this.doReloadAgreementData()
    }

    public doReloadAgreementData(): void
    {
        this.isLoadingAgreementData = true;
        if (this.selectedAgreementData.name != null)
            this.agreementDatasService.getAgreementData(this.selectedAgreementData.name).subscribe((response: any) => { this.processAgreementData(response); this.isLoadingAgreementData = false });
        else
        {
            this.agreementDatas         = null;
            this.selectedAgreementData  = null;
            this.isLoadingAgreementData = false;
        }           
    }

    private processAgreementData(response: any)
    {
        this.selectedAgreementData = response;
    }
}
