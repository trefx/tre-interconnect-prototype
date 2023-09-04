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
export class ResponseCheckingControlService
{
    private listResponseCheckersServicePath = "/service/response_checker/list_response_checkers";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public responseCheckerList(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.listResponseCheckersServicePath, { headers: headers, params: params });
    }
}
