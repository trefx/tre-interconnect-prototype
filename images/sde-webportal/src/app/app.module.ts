/*
 *  Copyright (c) 2023, TRE-FX Consortium Members
 *  Copyright (c) 2023-2024, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
 */

import { NgModule }                from '@angular/core';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule }             from '@angular/forms';
import { HttpClientModule }        from '@angular/common/http';

import { MatToolbarModule }         from '@angular/material/toolbar';
import { MatTabsModule }            from '@angular/material/tabs';
import { MatTableModule }           from '@angular/material/table';
import { MatPaginatorModule }       from '@angular/material/paginator';
import { MatGridListModule }        from '@angular/material/grid-list';
import { MatCardModule }            from '@angular/material/card';
import { MatListModule }            from '@angular/material/list';
import { MatDividerModule }         from '@angular/material/divider';
import { MatFormFieldModule }       from '@angular/material/form-field';
import { MatInputModule }           from '@angular/material/input';
import { MatProgressBarModule }     from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule }          from '@angular/material/button';
import { MatIconModule }            from '@angular/material/icon';
import { MatSlideToggleModule }     from '@angular/material/slide-toggle';

import { MatBadgeModule }           from '@angular/material/badge';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }                     from './app.component';
import { AgreementDatasComponent }          from './agreement-datas/agreement-datas.component';
import { InteractionHistoryComponent }      from './interaction-history/interaction-history.component';
import { UncheckedRequestListComponent }    from './unchecked-request-list/unchecked-request-list.component';
import { UncheckedResponseListComponent }   from './unchecked-response-list/unchecked-response-list.component';
import { RequestCheckingControlComponent }  from './request-checking-control/request-checking-control.component';
import { ResponseCheckingControlComponent } from './response-checking-control/response-checking-control.component';
import { DevelSupportComponent }            from './devel-support/devel-support.component';

import { ConfigService }                  from './config.service';
import { UncheckedInteractionLogService } from './unchecked-interaction-log.service';
import { RequestCheckingControlService }  from './request-checking-control.service';
import { ResponseCheckingControlService } from './response-checking-control.service';
import { AgreementDatasService }          from './agreement-datas.service';

@NgModule
({
    declarations:
    [
        AppComponent,
        AgreementDatasComponent,
        InteractionHistoryComponent,
        UncheckedRequestListComponent,
        UncheckedResponseListComponent,
        DevelSupportComponent,
        RequestCheckingControlComponent,
        ResponseCheckingControlComponent
    ],
    imports:
    [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        MatToolbarModule,
        MatGridListModule,
        MatTabsModule,
        MatTableModule,
        MatPaginatorModule,
        MatCardModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatButtonModule,
        MatIconModule,
        MatBadgeModule,
        MatSlideToggleModule,
        AppRoutingModule
    ],
    providers:
    [
        ConfigService,
        UncheckedInteractionLogService,
        RequestCheckingControlService,
        ResponseCheckingControlService,
        AgreementDatasService
    ],
    bootstrap:
    [
        AppComponent
    ]
})
export class AppModule
{
}
