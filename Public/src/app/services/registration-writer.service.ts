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
        console.log(res)
        if (res['status'] === 200) {
          return res['formFieldsDto'];
        } else {
          console.error(res['status']);
          return null;
        }
      }))
  }

  save(value: any, taskId: String, processInstanceId: String, type: String) {
    return this.http.post(this.host + 'registrationWriter/' + taskId, value)
      .pipe(map(res => {
        console.log(res);
        if (res['type'] === 'newForm') {
          return res['formFieldsDto'];
        }
        if (res['type'] === 'success') {
          return true;
        }
        return null;
      }))
  }
}
