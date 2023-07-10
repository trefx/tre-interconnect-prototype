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
export class DevelSupportService
{
    private reloadTemplatesServicePath = "/service/devel/reload_templates";
    private emptyRequestsServicePath   = "/service/devel/empty_requests";
    private emptyResponsesServicePath  = "/service/devel/empty_responses";
    private reloadProvidersServicePath = "/service/devel/reload_providers";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public reloadTemplates(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.configService.serverURL + this.reloadTemplatesServicePath, body, { headers: headers, params: params });
    }

    public emptyRequests(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.configService.serverURL + this.emptyRequestsServicePath, body, { headers: headers, params: params });
    }

    public emptyResponses(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.configService.serverURL + this.emptyResponsesServicePath, body, { headers: headers, params: params });
    }

    public reloadProviders(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.configService.serverURL + this.reloadProvidersServicePath, body, { headers: headers, params: params });
    }
}
