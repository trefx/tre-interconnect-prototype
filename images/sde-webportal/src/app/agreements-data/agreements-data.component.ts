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
    public summaries:        any;
    public data:             any;
    public displayedColumns: string[];

    public constructor(public agreementsDataService: AgreementsDataService)
    {
        this.summaries        = [];
        this.data             = [];
        this.displayedColumns = ["Id", "Id", "Person"];
    }

    public doReloadAgreementsDataList(): void
    {

    }
}
