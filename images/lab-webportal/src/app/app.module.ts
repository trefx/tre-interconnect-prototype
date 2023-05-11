/*
 *  Copyright (c) 2023, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
 */

import { NgModule }                from '@angular/core';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule }        from '@angular/common/http';

import { MatToolbarModule }   from '@angular/material/toolbar';
import { MatGridListModule }  from '@angular/material/grid-list'; 
import { MatTabsModule }      from '@angular/material/tabs';
import { MatCardModule }      from '@angular/material/card';
import { MatListModule }      from '@angular/material/list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule }     from '@angular/material/input';
import { MatButtonModule }    from '@angular/material/button';
import { MatIconModule }      from '@angular/material/icon';
import { MatBadgeModule }     from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }            from './app.component';
import { ROCrateComponent }        from './rocrate/rocrate.component';
import { ROCrateListComponent }    from './rocrate-list/rocrate-list.component';
import { RequestCreatorComponent } from './request-creator/request-creator.component';
import { RequestListComponent }    from './request-list/request-list.component';
import { RequestComponent }        from './request/request.component';

import { RequestCreatorService } from './request-creator.service';
import { RequestFormService }    from './request-form.service';

@NgModule
({
    declarations:
    [
        AppComponent,
        ROCrateComponent,
        ROCrateListComponent,
        RequestCreatorComponent,
        RequestListComponent,
        RequestComponent
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
        MatButtonModule,
        MatIconModule,
        MatBadgeModule,
        AppRoutingModule
    ],
    providers:
    [
        RequestCreatorService,
        RequestFormService
    ],
    bootstrap:
    [
        AppComponent
    ]
})
export class AppModule
{
}
