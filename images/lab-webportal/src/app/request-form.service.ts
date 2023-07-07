import { Injectable } from '@angular/core';

import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { ConfigService } from './config.service';

import { RequestFormTemplate } from './request-form-template';
import { RequestFormSummary }  from './request-form-summary';

@Injectable
({
    providedIn: 'root'
})
export class RequestFormService
{
    private templateSummariesServicePath = "/service/request_creator/template/summaries";
    private templateServicePath          = "/service/request_creator/template";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public listRequestSummaries(): Observable<RequestFormSummary[]>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

        const outcomeObservable = this.httpClient.get<RequestFormSummary[]>(this.configService.serverURL + this.templateSummariesServicePath, { headers: headers, params: params });

        return outcomeObservable;
    }

    public getRequestTemplate(requestTemplateID: string): Observable<RequestFormTemplate>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

        const outcomeObservable = this.httpClient.get<RequestFormTemplate>(this.configService.serverURL + this.templateServicePath, { headers: headers, params: params });

        return outcomeObservable;
    }
}
