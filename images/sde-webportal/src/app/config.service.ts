import { Injectable } from '@angular/core';

@Injectable
({
    providedIn: 'root'
})
export class ConfigService
{
    public serverURL: string = "http://trefx:8090";

    public constructor()
    {
    }
}
