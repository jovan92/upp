import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.scss']
})
export class VerifyComponent implements OnInit {

  isStatusVerify: Boolean;
  constructor(
    private activatedRoute: ActivatedRoute,
    private registrationService: RegistrationService) { 
      this.isStatusVerify = false;
    }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(res =>{
      this._checkLink(res);
    });
  }

  _checkLink(res: any) {
    console.log(res)
    this.registrationService.checkToken(res)
      .subscribe(res => {
        if (res['type'] === 'success') {
          this.isStatusVerify = true;
        }
      })
  }
}
