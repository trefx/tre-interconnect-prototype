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
    public displayedColumns:       string[];
    public isLoadingTables:        boolean;
    public isLoadingTableData:     boolean;

    public constructor(public agreementsDataService: AgreementsDataService)
    {
        this.agreementsDatas        = [];
        this.selectedIndex          = null;
        this.selectedAgreementsData = null;
        this.data                   = [];
        this.displayedColumns       = [];
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
        this.agreementsDatas = agreementsDataSummaries;
        this.selectedAgreementsData = this.agreementsDatas[0];
    }

    public doSelectAgreementsDataIndex(agreementsDataIndex: any)
    {
        console.log(agreementsDataIndex);
//        this.selectedAgreementsData = agreementsData;
    }

    public doSelectAgreementsData(agreementsData: any)
    {
        console.log(agreementsData);
//        this.selectedAgreementsData = agreementsData;
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
