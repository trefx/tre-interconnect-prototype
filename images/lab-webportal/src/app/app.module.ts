/*
 *  Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
 */

import { NgModule }                from '@angular/core';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule }        from '@angular/common/http';

import { MatToolbarModule }         from '@angular/material/toolbar';
import { MatTabsModule }            from '@angular/material/tabs';
import { MatTableModule }           from '@angular/material/table';
import { MatPaginatorModule }       from '@angular/material/paginator';
import { MatGridListModule }        from '@angular/material/grid-list';
import { MatCardModule }            from '@angular/material/card';
import { MatListModule }            from '@angular/material/list';
import { MatFormFieldModule }       from '@angular/material/form-field';
import { MatInputModule }           from '@angular/material/input';
import { MatProgressBarModule }     from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule }          from '@angular/material/button';
import { MatIconModule }            from '@angular/material/icon';
import { MatBadgeModule }           from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }                      from './app.component';
import { ROCrateComponent }                  from './rocrate/rocrate.component';
import { ROCrateListComponent }              from './rocrate-list/rocrate-list.component';
import { TemplatedRequestCreatorComponent }  from './templated-request-creator/templated-request-creator.component';
import { DataSHIELDRequestCreatorComponent } from './datashield-request-creator/datashield-request-creator.component';
import { ProviderListComponent }             from './provider-list/provider-list.component';
import { RequestListComponent }              from './request-list/request-list.component';
import { RequestComponent }                  from './request/request.component';
import { ResponseListComponent }             from './response-list/response-list.component';
import { DevelSupportComponent }             from './devel-support/devel-support.component';

import { ConfigService }                     from './config.service';
import { RequestFormService }                from './request-form.service';
import { TemplatedRequestSubmitterService }  from './templated-request-submitter.service';
import { DataSHIELDRequestSubmitterService } from './datashield-request-submitter.service';
import { InteractionLogService }             from './interaction-log.service';
import { DevelSupportService }               from './devel-support.service';

@NgModule
({
    declarations:
    [
        AppComponent,
        ROCrateComponent,
        ROCrateListComponent,
        TemplatedRequestCreatorComponent,
        DataSHIELDRequestCreatorComponent,
        ProviderListComponent,
        RequestListComponent,
        RequestComponent,
        ResponseListComponent,
        DevelSupportComponent
    ],
    imports:
    [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MatToolbarModule,
        MatTabsModule,
        MatTableModule,
        MatPaginatorModule,
        MatGridListModule,
        MatCardModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatButtonModule,
        MatIconModule,
        MatBadgeModule,
        AppRoutingModule
    ],
    providers:
    [
        ConfigService,
        TemplatedRequestSubmitterService,
        DataSHIELDRequestSubmitterService,
        InteractionLogService,
        DevelSupportService
    ],
    bootstrap:
    [
        AppComponent
    ]
})
export class AppModule
{
}
