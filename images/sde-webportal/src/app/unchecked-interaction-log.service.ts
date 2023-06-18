import { Injectable }  from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

import { ConfigService } from './config.service';

@Injectable
({
    providedIn: 'root'
})
export class UncheckedInteractionLogService
{
    private uncheckedRequestsServicePath  = "/service/request_checker/unchecked_requests";
    private uncheckedRequestServicePath   = "/service/request_checker/unchecked_request";
    private blockRequestServicePath       = "/service/request_checker/block_request";
    private permitRequestServicePath      = "/service/request_checker/permit_request";
    private uncheckedResponsesServicePath = "/service/response_checker/unchecked_responses";
    private uncheckedResponseServicePath  = "/service/response_checker/unchecked_response";
    private blockResponseServicePath      = "/service/response_checker/block_response";
    private permitResponseServicePath     = "/service/response_checker/permit_response";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public listUncheckedRequests(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.uncheckedRequestsServicePath, { headers: headers, params: params });
    }

    public getUncheckedRequest(requestId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        params = params.append("requestid", requestId)

        return this.httpClient.get<string>(this.configService.serverURL + this.uncheckedRequestServicePath, { headers: headers, params: params });
    }

    public blockRequest(requestId: string): Observable<string>
    {
        let headers   = new HttpHeaders({"Accept": "application/json"});
        let params    = new HttpParams();
        let body: any = {};
        params = params.append("requestid", requestId)

        return this.httpClient.post<string>(this.configService.serverURL + this.blockRequestServicePath, body, { headers: headers, params: params });
    }

    public permitRequest(requestId: string): Observable<string>
    {
        let headers   = new HttpHeaders({"Accept": "application/json"});
        let params    = new HttpParams();
        let body: any = {};
        params = params.append("requestid", requestId)

        return this.httpClient.post<string>(this.configService.serverURL + this.permitRequestServicePath, body, { headers: headers, params: params });
    }

    public listUncheckedResponses(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.uncheckedResponsesServicePath, { headers: headers, params: params });
    }

    public getUncheckedResponse(responseId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        params = params.append("responseid", responseId)

        return this.httpClient.get<string>(this.configService.serverURL + this.uncheckedResponseServicePath, { headers: headers, params: params });
    }

    public blockResponse(responseId: string): Observable<string>
    {
        let headers   = new HttpHeaders({"Accept": "application/json"});
        let params    = new HttpParams();
        let body: any = {};
        params = params.append("responseid", responseId)

        return this.httpClient.post<string>(this.configService.serverURL + this.blockResponseServicePath, body, { headers: headers, params: params });
    }

    public permitResponse(responseId: string): Observable<string>
    {
        let headers   = new HttpHeaders({"Accept": "application/json"});
        let params    = new HttpParams();
        let body: any = {};
        params = params.append("responseid", responseId)

        return this.httpClient.post<string>(this.configService.serverURL + this.permitResponseServicePath, body, { headers: headers, params: params });
    }
}
