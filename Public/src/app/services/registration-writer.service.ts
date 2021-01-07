import { Injectable } from '@angular/core';
import {  HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegistrationWriterService {

  host: String;
  constructor(private http : HttpClient) {
    this.host = 'http://localhost:8080/api/';
  }

  getForms() {
    return this.http.get(this.host + 'registrationWriter/')
      .pipe(map(res => {
        console.log(res);
        return null;
      }))
  }
}
