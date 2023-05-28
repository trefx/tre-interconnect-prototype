import { Injectable }  from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable
({
    providedIn: 'root'
})
export class TemplatedRequestSubmitterService
{
    private serviceURL = "http://localhost:8080/service/request-creator/templated_request_submitter";

    public constructor(private httpClient: HttpClient)
    {
    }

    public createRequest(templateID: string): Observable<Object>
    {
        let headers = new HttpHeaders({"Content-Type": "application/json"});
        let params  = new HttpParams();

        var body =
        {
            "templateID": templateID
        }

        return this.httpClient.post<Object>(this.serviceURL, body, { headers: headers, params: params });
    }
}
