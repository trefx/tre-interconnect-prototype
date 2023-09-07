import { Injectable } from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

import { ConfigService } from './config.service';

@Injectable
({
    providedIn: 'root'
})
export class AgreementDatasService
{
    private getAgreementDataSummariesServicePath = "/service/control/agreementdatas/summaries";
    private getAgreementDataServicePath          = "/service/control/agreementdatas/data";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public getAgreementDataSummaries(): Observable<any>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();

        return this.httpClient.get<any>(this.configService.serverURL + this.getAgreementDataSummariesServicePath, { headers: headers, params: params });
    }

    public getAgreementData(name: string): Observable<any>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        params = params.append("agreement_data_name", name);

        return this.httpClient.get<any>(this.configService.serverURL + this.getAgreementDataServicePath, { headers: headers, params: params });
    }
}
