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
    public storesResetMessage: string | null;
    public isResettingStorage: boolean;

    public constructor(private develSupportService: DevelSupportService)
    {
        this.storesResetMessage = null;
        this.isResettingStorage = false;
    }

    public doResetStorage(): void
    {
        this.isResettingStorage = true;
        this.develSupportService.storesReset().subscribe((data: Object) => { this.storesResetMessage = (data as any).outcome; this.isResettingStorage = false });
    }
}
