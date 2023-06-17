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
    private develServicePath = "/service/devel/stores_reset";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public storesReset(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.configService.serverURL + this.develServicePath, body, { headers: headers, params: params });
    }
}
