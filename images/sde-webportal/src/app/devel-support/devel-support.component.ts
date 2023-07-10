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
    public reloadAgreementsDataMessage: string | null;
    public isReloadingAgreementsData:   boolean;

    public constructor(private develSupportService: DevelSupportService)
    {
        this.reloadAgreementsDataMessage = null;
        this.isReloadingAgreementsData   = false;
    }

    public doReloadAgreementsData(): void
    {
        this.isReloadingAgreementsData = true;
        this.develSupportService.reloadAgreementsData().subscribe((data: Object) => { this.reloadAgreementsDataMessage = (data as any).outcome; this.isReloadingAgreementsData = false });
    }
}
