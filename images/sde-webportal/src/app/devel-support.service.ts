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
    private reloadAgreementDatasServicePath = "/service/devel/reload_agreementdatas";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public reloadAgreementsData(): Observable<Object>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.configService.serverURL + this.reloadAgreementDatasServicePath, body, { headers: headers, params: params });
    }
}
