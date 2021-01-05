import { Injectable } from '@angular/core';
import {  HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { LoginDTO } from 'src/app/model/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  host: String;
  constructor(private http: HttpClient) { 
    this.host = 'http://localhost:8080/api/'
  }

  login(loginForm: LoginDTO) {
    return this.http.post(this.host + 'login/', loginForm)
      .pipe(map(res => {
        if (res['type'] === 'login') {
          let token = res['loginResponseDTO']['token'];
          let user = res['loginResponseDTO']['userDTO'];

          localStorage.setItem('token', JSON.stringify(token));
          localStorage.setItem('user', JSON.stringify(user));
          return true;
        } else {
          return false;
        }
      }))
  }
}
