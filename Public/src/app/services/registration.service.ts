import { Injectable } from '@angular/core';
import {  HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { normalizeGenFileSuffix } from '@angular/compiler/src/aot/util';
import { generate } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  host: String;
  constructor(private http : HttpClient) {
    this.host = 'http://localhost:8080/api/'
  }

  getForms() {
    return this.http.get(this.host + 'registration/')
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

  isBeta(forms: any) {
    return this.http.post(this.host + 'registration/validation', forms)
      .pipe(map(res => {
        console.log(res)
      }))
  }

  getGenres(type: String) {
    return this.http.get(this.host + 'registration/genres')
      .pipe(map(res => {
        return res;
      }))
  }

  save(value: any, taskId: String, processInstanceId: String, type: String) {
    return this.http.post(this.host + 'registration/' + taskId, value)
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

  ngOpenModalDialog() {

  }

  saveGenre(genres: String) {
    console.log(genres)
    return this.http.post(this.host + 'registration/newGenre', {name: genres})
      .pipe(map(res => {
        return res;
      }))
  }

  checkToken(token: any) {
    return this.http.post(this.host + 'registration/verify', token)
      .pipe(map(res => {
        return res;
      }))
  }
}
