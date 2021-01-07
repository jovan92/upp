import { Component, OnInit } from '@angular/core';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';
import { LoginWriter } from 'src/app/model/login-writer';

@Component({
  selector: 'app-registration-writer',
  templateUrl: './registration-writer.component.html',
  styleUrls: ['./registration-writer.component.scss']
})
export class RegistrationWriterComponent implements OnInit {

  registrationForms: LoginWriter;
  constructor(private registration : RegistrationWriterService) { 
    this.registrationForms = new LoginWriter();
  }

  ngOnInit(): void {
    this._getForms();
  }

  _getForms() {
    this.registration.getForms()
      .subscribe(res => {
        console.log(res);
      })
  }
}
