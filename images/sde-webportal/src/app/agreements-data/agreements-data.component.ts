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
    public summaries:          any;
    public data:               any;
    public displayedColumns:   string[];
    public isLoadingTables:    boolean;
    public isLoadingTableData: boolean;

    public constructor(public agreementsDataService: AgreementsDataService)
    {
        this.summaries          = [];
        this.data               = [];
        this.displayedColumns   = ["Id", "Id", "Person"];
        this.isLoadingTables    = false;
        this.isLoadingTableData = false;
    }

    public doReloadAgreementsDataSummaries(): void
    {
        this.isLoadingTables = true;
        let outcome = this.agreementsDataService.getAgreementsDataSummaries().subscribe((data: Object) => { this.summaries = (data as any).outcome; this.isLoadingTables = false });
    }
}
