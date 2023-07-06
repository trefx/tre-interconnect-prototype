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
    private servicePath = "/service/request_form";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public listRequestSummaries(): Observable<RequestFormSummary[]>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

//        const outcomeObservable = this.httpClient.get<Object>(configService.serverURL + this.service, { headers: headers, params: params });

        const requestFormSummaries: RequestFormSummary[] = [];
        requestFormSummaries.push(new RequestFormSummary("0001", "A", "Summany of A", "Description A"));
        requestFormSummaries.push(new RequestFormSummary("0002", "B", "Summany of B", "Description B"));
        requestFormSummaries.push(new RequestFormSummary("0003", "C", "Summany of C", "Description C"));
        requestFormSummaries.push(new RequestFormSummary("0004", "D", "Summany of D", "Description D"));

        const outcomeObservable = of(requestFormSummaries);

        return outcomeObservable;
    }

    public getRequestTemplate(requestTemplateID: string): Observable<RequestFormTemplate>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

//        const outcomeObservable = this.httpClient.get<Object>(configService.serverURL + this.service, { headers: headers, params: params });

        const requestTemplate: RequestFormTemplate = new RequestFormTemplate(requestTemplateID, "@Template@");

        const outcomeObservable = of(requestTemplate);

        return outcomeObservable;
    }
}
