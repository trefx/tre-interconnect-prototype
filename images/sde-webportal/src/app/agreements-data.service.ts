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
export class AgreementsDataService
{
    private getAgreementsDataSummariesServicePath = "/control/agreementsdata/summaries";
    private getAgreementsDataDataServicePath      = "/control/agreementsdata/data";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public getAgreementsDataSummaries(): Observable<any>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();

        return this.httpClient.get<any>(this.configService.serverURL + this.getAgreementsDataSummariesServicePath, { headers: headers, params: params });
    }

    public getAgreementsDataData(name: string): Observable<any>
    {
        let headers = new HttpHeaders();
        headers = headers.append("Accept", "application/json");
        headers = headers.append("Content-Type", "application/json");
        let params  = new HttpParams();
        params = params.append("agreements_data_name", name);

        return this.httpClient.get<any>(this.configService.serverURL + this.getAgreementsDataDataServicePath, { headers: headers, params: params });
    }
}
