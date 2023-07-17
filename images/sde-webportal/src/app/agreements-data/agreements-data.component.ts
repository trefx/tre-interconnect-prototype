import { Component } from '@angular/core';

import { AgreementsDataService } from '../agreements-data.service';

@Component
({
    selector:    'sde-agreements-data',
    templateUrl: './agreements-data.component.html',
    styleUrls:   ['./agreements-data.component.scss']
})
export class AgreementsDataComponent
{
    public agreementsDatas:        any;
    public selectedIndex:          number | null;
    public selectedAgreementsData: any;
    public data:                   any;
    public isLoadingTables:        boolean;
    public isLoadingTableData:     boolean;

    public constructor(public agreementsDataService: AgreementsDataService)
    {
        this.agreementsDatas        = [];
        this.selectedIndex          = null;
        this.selectedAgreementsData = null;
        this.data                   = [];
        this.isLoadingTables        = false;
        this.isLoadingTableData     = false;
    }

    public doReloadAgreementsDataSummaries(): void
    {
        this.isLoadingTables = true;
        this.agreementsDataService.getAgreementsDataSummaries().subscribe((data: any) => { this.processAgreementDataSummaries(data); this.isLoadingTables = false });
    }

    private processAgreementDataSummaries(agreementsDataSummaries: any)
    {
        this.agreementsDatas        = agreementsDataSummaries;
        this.selectedAgreementsData = this.agreementsDatas[0];
        this.doReloadAgreementsDataData();        
    }

    public doSelectAgreementsDataIndex(agreementsDataIndex: any)
    {
        this.selectedAgreementsData = this.agreementsDatas[agreementsDataIndex];
        this.doReloadAgreementsDataData()
    }

    public doReloadAgreementsDataData(): void
    {
        this.isLoadingTableData = true;
        this.agreementsDataService.getAgreementsDataData(this.selectedAgreementsData.name).subscribe((data: any) => { this.processAgreementDataData(data); this.isLoadingTableData = false });
    }

    private processAgreementDataData(agreementsDataData: any)
    {
        this.data = agreementsDataData;
    }
}
