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

import { AppComponent }                from './app.component';
import { AgreementDataComponent }      from './agreement-data/agreement-data.component';
import { InteractionHistoryComponent } from './interaction-history/interaction-history.component';
import { RequestCheckerComponent }     from './request-checker/request-checker.component';
import { ResponseCheckerComponent }    from './response-checker/response-checker.component';

@NgModule
({
    declarations:
    [
        AppComponent,
        AgreementDataComponent,
        InteractionHistoryComponent,
        RequestCheckerComponent,
        ResponseCheckerComponent
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
    ],
    bootstrap:
    [
        AppComponent
    ]
})
export class AppModule
{
}
