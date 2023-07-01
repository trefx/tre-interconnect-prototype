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
export class ProviderListService
{
    private providersServicePath = "/service/request_creator/providers";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public getProviderList(): Observable<any>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();

        return this.httpClient.get<Object>(this.configService.serverURL + this.providersServicePath, { headers: headers, params: params });
    }
}
