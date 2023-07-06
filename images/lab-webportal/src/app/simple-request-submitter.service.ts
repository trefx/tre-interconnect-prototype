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
export class SimpleRequestSubmitterService
{
    private servicePath = "/service/request_creator/simple_request_submitter";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public createRequest(): Observable<Object>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

        var body =
        {
        }

        return this.httpClient.post<Object>(this.configService.serverURL + this.servicePath, body, { headers: headers, params: params });
    }
}
