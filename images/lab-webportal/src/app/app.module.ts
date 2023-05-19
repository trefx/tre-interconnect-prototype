/*
 *  Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
 */

import { NgModule }                from '@angular/core';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule }        from '@angular/common/http';

import { MatToolbarModule }     from '@angular/material/toolbar';
import { MatGridListModule }    from '@angular/material/grid-list'; 
import { MatTabsModule }        from '@angular/material/tabs';
import { MatCardModule }        from '@angular/material/card';
import { MatListModule }        from '@angular/material/list';
import { MatFormFieldModule }   from '@angular/material/form-field';
import { MatInputModule }       from '@angular/material/input';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatButtonModule }      from '@angular/material/button';
import { MatIconModule }        from '@angular/material/icon';
import { MatBadgeModule }       from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }                      from './app.component';
import { ROCrateComponent }                  from './rocrate/rocrate.component';
import { ROCrateListComponent }              from './rocrate-list/rocrate-list.component';
import { TemplatedRequestCreatorComponent }  from './templated-request-creator/templated-request-creator.component';
import { DataSHIELDRequestCreatorComponent } from './datashield-request-creator/datashield-request-creator.component';
import { RequestListComponent }              from './request-list/request-list.component';
import { RequestComponent }                  from './request/request.component';
import { ResponseListComponent }             from './response-list/response-list.component';

import { RequestFormService }                from './request-form.service';
import { TemplatedRequestSubmitterService }  from './templated-request-submitter.service';
import { DataSHIELDRequestSubmitterService } from './datashield-request-submitter.service';
import { InteractionLogService }             from './interaction-log.service';

@NgModule
({
    declarations:
    [
        AppComponent,
        ROCrateComponent,
        ROCrateListComponent,
        TemplatedRequestCreatorComponent,
        DataSHIELDRequestCreatorComponent,
        RequestListComponent,
        RequestComponent,
        ResponseListComponent
    ],
    imports:
    [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MatToolbarModule,
        MatGridListModule,
        MatTabsModule,
        MatCardModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule,
        MatProgressBarModule,
        MatButtonModule,
        MatIconModule,
        MatBadgeModule,
        AppRoutingModule
    ],
    providers:
    [
        TemplatedRequestSubmitterService,
        DataSHIELDRequestSubmitterService,
        InteractionLogService
    ],
    bootstrap:
    [
        AppComponent
    ]
})
export class AppModule
{
}
