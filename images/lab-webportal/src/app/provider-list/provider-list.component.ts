import { Component } from '@angular/core';

import { ProviderListService } from '../provider-list.service';

@Component
({
    selector:    'sde-provider-list',
    templateUrl: './provider-list.component.html',
    styleUrls:   ['./provider-list.component.scss']
})
export class ProviderListComponent
{
    public providers: any;

    public isRequesting: boolean;

    public constructor(private providerListService: ProviderListService)
    {
        this.providers = null;

        this.isRequesting = false;
    }

    public doGetProviders(): void
    {
	this.isRequesting = true;
        let outcome = this.providerListService.getProviderList();
        outcome.subscribe((data: any) => { this.providers = data; this.isRequesting = false });
    }
}
