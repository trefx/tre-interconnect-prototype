import { Injectable }  from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable
({
    providedIn: 'root'
})
export class RequestSubmitterService
{
    private serviceURL = "http://localhost:8080/service/request_submitter";

    public constructor(private httpClient: HttpClient)
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

        const outcomeObservable = this.httpClient.post<Object>(this.serviceURL, body, { headers: headers, params: params });

        outcomeObservable.subscribe(data => { console.log("** " + data + " **") });

        return outcomeObservable;
    }
}
