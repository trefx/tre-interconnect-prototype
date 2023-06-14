import { Injectable }  from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable
({
    providedIn: 'root'
})
export class UncheckedInteractionLogService
{
    private uncheckedRequestsServiceURL  = "http://localhost:8090/service/request_checker/unchecked_requests";
    private uncheckedRequestServiceURL   = "http://localhost:8090/service/request_checker/unchecked_request";
    private uncheckedResponsesServiceURL = "http://localhost:8090/service/response_checker/unchecked_responses";
    private uncheckedResponseServiceURL  = "http://localhost:8090/service/response_checker/unchecked_response";

    public constructor(private httpClient: HttpClient)
    {
    }

    public listUncheckedRequests(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.uncheckedRequestsServiceURL, { headers: headers, params: params });
    }

    public getUncheckedRequest(requestId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        params = params.append("requestid", requestId)

        return this.httpClient.get<string>(this.uncheckedRequestServiceURL, { headers: headers, params: params });
    }

    public listUncheckedResponses(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.uncheckedResponsesServiceURL, { headers: headers, params: params });
    }

    public getUncheckedResponse(responseId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        params = params.append("responseid", responseId)

        return this.httpClient.get<string>(this.uncheckedResponseServiceURL, { headers: headers, params: params });
    }
}
