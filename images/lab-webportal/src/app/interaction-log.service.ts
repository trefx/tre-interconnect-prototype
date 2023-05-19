import { Injectable }  from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable
({
    providedIn: 'root'
})
export class InteractionLogService
{
    private requestsServiceURL  = "http://localhost:8080/service/response_processor/requests";
    private requestServiceURL   = "http://localhost:8080/service/response_processor/request";
    private responsesServiceURL = "http://localhost:8080/service/response_processor/responses";
    private responseServiceURL  = "http://localhost:8080/service/response_processor/response";

    public constructor(private httpClient: HttpClient)
    {
    }

    public listRequests(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.requestsServiceURL, { headers: headers, params: params });
    }

    public getRequest(requestId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "text/plain"});
        let params  = new HttpParams();
        params.set("requestid", requestId)

        return this.httpClient.get<string>(this.requestServiceURL, { headers: headers, params: params });
    }

    public listResponses(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.responsesServiceURL, { headers: headers, params: params });
    }

    public getResponse(responseId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "text/plain"});
        let params  = new HttpParams();
        params.set("responseid", responseId)

        return this.httpClient.get<string>(this.responseServiceURL, { headers: headers, params: params });
    }
}
