import { Injectable } from '@angular/core';
import {  HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegistrationWriterService {

  host: String;
  taskId: any;
  constructor(private http : HttpClient) {
    this.host = 'http://localhost:8080/api/';
    this.taskId = JSON.parse(localStorage.getItem('userConfiguration'))['processInstanceId'];
  }

  getForms() {
    return this.http.get(this.host + 'registrationWriter/')
      .pipe(map(res => {
        console.log(res)
        if (res['status'] === 200) {

          const uc = {
            processInstanceId: res['formFieldsDto']['processInstanceId'],
            taskId: res['formFieldsDto']['taskId']
          }
          localStorage.setItem('userConfiguration', JSON.stringify(uc));
          return res['formFieldsDto'];
        } else {
          console.error(res['status']);
          return null;
        }
      }))
  }

  getFormsFile(taskId: String) {
    return this.http.get(this.host + 'registrationWriter/fileForms/' + taskId)
      .pipe(map(res => {
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
        if (res['type'] === 'errorUsername') {
          return 'nevalja';
        }
        return null;
      }))
  }

  handleError(error) {

  }

  uploadFile(file: any, user: any) {
    
    return this.http.post(this.host + 'files/' + this.taskId + '/' + user.username, file, { observe: 'response' })
      .pipe(map(res => {
        return res;
      }))
  }

  saveUpload() {
    return this.http.put(this.host + 'registrationWriter/' + this.taskId, {})
      .pipe(map(res => {
        return res;
      }))
  }
}
