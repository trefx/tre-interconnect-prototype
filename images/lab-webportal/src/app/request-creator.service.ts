import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable
({
    providedIn: 'root'
})
export class RequestCreatorService
{
    private serviceURL = "http://localhost:8080/service/request_creator";

    public constructor(private httpClient: HttpClient)
    {
    }

    public createRequest(dataSHIELDPlatformName: string, dataSHIELDProfileName: string, dataSHIELDSymbolNamesList: string, dataSHIELDTableNamesList: string, dataSHIELDWorkspaceName: string, dataSHIELDRScript: string): Observable<string>
    {
        let body   = new HttpParams();
        let params = new HttpParams();

        params = params.append("datashield-platform-name", dataSHIELDPlatformName)
        params = params.append("datashield-profile-name", dataSHIELDProfileName)
        params = params.append("datashield-symbol-names-list", dataSHIELDSymbolNamesList)
        params = params.append("datashield-table-names-list", dataSHIELDTableNamesList)
        params = params.append("datashield-workspace-name", dataSHIELDWorkspaceName)
        params = params.append("datashield-r-script", dataSHIELDRScript)

        const outcomeObservable = this.httpClient.post<string>(this.serviceURL, body, { params: params });

        return outcomeObservable;
    }
}
