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
export class RequestCheckingControlService
{
    private listRequestCheckersServicePath = "/service/request_checker/list_request_checkers";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public requestCheckerList(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.listRequestCheckersServicePath, { headers: headers, params: params });
    }
}
