import { Injectable }  from '@angular/core';
import { HttpClient }  from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams }  from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable
({
    providedIn: 'root'
})
export class DevelSupportService
{
    private develServiceURL = "http://localhost:8080/service/devel/stores_reset";

    public constructor(private httpClient: HttpClient)
    {
    }

    public storesReset(): Observable<Object>
    {
        let headers = new HttpHeaders({"Accept": "application/json"});
        let params  = new HttpParams();
        let body    = { };

        return this.httpClient.post<Object>(this.develServiceURL, body, { headers: headers, params: params });
    }
}
