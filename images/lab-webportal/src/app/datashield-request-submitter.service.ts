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
export class DataSHIELDRequestSubmitterService
{
    private servicePath = "/service/request_creator/datashield_request_submitter";

    public constructor(private configService: ConfigService, private httpClient: HttpClient)
    {
    }

    public createRequest(dataSHIELDPlatformName: string, dataSHIELDProfileName: string, dataSHIELDSymbolNamesList: string, dataSHIELDTableNamesList: string, dataSHIELDWorkspaceName: string, dataSHIELDRScript: string): Observable<Object>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

        var body =
        {
            "dataSHIELDPlatformName":    dataSHIELDPlatformName,
            "dataSHIELDProfileName":     dataSHIELDProfileName,
            "dataSHIELDSymbolNamesList": dataSHIELDSymbolNamesList,
            "dataSHIELDTableNamesList":  dataSHIELDTableNamesList,
            "dataSHIELDWorkspaceName":   dataSHIELDWorkspaceName,
            "dataSHIELDRScript":         dataSHIELDRScript
        }

        return this.httpClient.post<Object>(this.configService.serverURL + this.servicePath, body, { headers: headers, params: params });
    }
}
