import { Injectable } from '@angular/core';

import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { RequestFormTemplate } from './request-form-template';
import { RequestFormSummary }  from './request-form-summary';

@Injectable
({
    providedIn: 'root'
})
export class RequestFormService
{
    private serviceURL = "http://localhost:8080/service/request_form";

    public constructor(private httpClient: HttpClient)
    {
    }

    public listRequestFormSummarys(): Observable<RequestFormSummary[]>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

//        const outcomeObservable = this.httpClient.get<Object>(this.serviceURL, { headers: headers, params: params });

        const requestFormSummaries: RequestFormSummary[] = [];
        requestFormSummaries.push(new RequestFormSummary("0001", "A", "Summany of A", "Description A"));
        requestFormSummaries.push(new RequestFormSummary("0002", "B", "Summany of B", "Description B"));
        requestFormSummaries.push(new RequestFormSummary("0003", "C", "Summany of C", "Description C"));
        requestFormSummaries.push(new RequestFormSummary("0004", "D", "Summany of D", "Description D"));

        const outcomeObservable = of(requestFormSummaries);

        outcomeObservable.subscribe(data => { console.log("** " + data + " **") });

        return outcomeObservable;
    }

    public readRequestFormTemplate(requestFormTemplateID: string): Observable<RequestFormTemplate>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

//        const outcomeObservable = this.httpClient.get<Object>(this.serviceURL, { headers: headers, params: params });

        const requestFormTemplate: RequestFormTemplate = new RequestFormTemplate(requestFormTemplateID, "@Template@");

        const outcomeObservable = of(requestFormTemplate);

        outcomeObservable.subscribe(data => { console.log("** " + data + " **") });

        return outcomeObservable;
    }
}
