import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/app/model/login';
import { LoginService } from 'src/app/services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: LoginDTO;
  isNotLogin: Boolean;
  constructor(private loginService: LoginService, private router: Router) {
    this.loginForm = new LoginDTO();
    this.isNotLogin = false;
  }

  ngOnInit(): void {
  }

  ngLogin() {
    this.loginService.login(this.loginForm)
      .subscribe((res: any) => {
        if (!res) {
          this.isNotLogin = true;
        } else {
          window.alert('Is login success');
          this.router.navigate(['/home']);
        }
      })
  }

}
