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
export class InteractionLogService
{
    private requestsServicePath  = "/service/response_processor/requests";
    private requestServicePath   = "/service/response_processor/request";
    private responsesServicePath = "/service/response_processor/responses";
    private responseServicePath  = "/service/response_processor/response";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public listRequests(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.requestsServicePath, { headers: headers, params: params });
    }

    public getRequest(requestId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        params = params.append("requestid", requestId)

        return this.httpClient.get<string>(this.configService.serverURL + this.requestServicePath, { headers: headers, params: params });
    }

    public listResponses(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.responsesServicePath, { headers: headers, params: params });
    }

    public getResponse(responseId: string): Observable<string>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        params = params.append("responseid", responseId)

        return this.httpClient.get<string>(this.configService.serverURL + this.responseServicePath, { headers: headers, params: params });
    }
}
