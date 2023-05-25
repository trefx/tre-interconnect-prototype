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
    public isResettingStorage: boolean;

    public constructor(private develSupportService: DevelSupportService)
    {
        this.isResettingStorage = false;
    }

    public doResetStorage(): void
    {
        this.isResettingStorage = true;
        this.develSupportService.storesReset().subscribe((data: any) => { this.isResettingStorage = false });
    }
}
