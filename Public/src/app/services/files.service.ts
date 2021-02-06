import { Injectable } from '@angular/core';
import {  HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  host: String;
  constructor(private http: HttpClient) {
    this.host = 'http://localhost:8080/api/'
  }

  getAllFilesByUser(user: any) {
    console.log(user.id)
    return this.http.get(this.host + 'files/' + user.username)
      .pipe(map(res => {
        if (res['status'] === 200) {
          return res['filesDTOs']
        }
        console.log('Nesto nije uredu na serveru')
        return []
      }))
  }

  uploadFile(file: any, user: any) {
    const taskId = JSON.parse(localStorage.getItem('userConfiguration'))['processInstanceId'];
    return this.http.post(this.host + 'files/' + taskId + '/' + user.username, file, { observe: 'response' })
      .pipe(map(res => {
        if (res['body']['status'] === 200) {
          return res['body']['filesDTOs']
        };
      }))
  }

  commitedFiles() {
    const taskId = JSON.parse(localStorage.getItem('userConfiguration'))['processInstanceId'];
    const username = JSON.parse(localStorage.getItem('user'))['username']
    return this.http.put(this.host + 'files/' + taskId + '/' + username, {})
      .pipe(map(res => {
        console.log(res)
        return res;
      }))
  }

  removeFiles(item: any) {
    const taskId = JSON.parse(localStorage.getItem('userConfiguration'))['processInstanceId'];
    const username = JSON.parse(localStorage.getItem('user'))['username'];
    return this.http.delete(this.host + 'files/' + taskId + '/' + item.id)
      .pipe(map(res => {
        return true;
      }))
  }
}
