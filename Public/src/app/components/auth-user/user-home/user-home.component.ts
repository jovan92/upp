import { Component, OnInit } from '@angular/core';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';
import { Login } from 'src/app/model/login';
import { Global } from 'src/app/global/global';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.scss']
})
export class UserHomeComponent implements OnInit {

  activeTub: String;
  user: any;
  uc: any;
  formsFiles: Login;
  constructor(private registrationWriterService: RegistrationWriterService, private global: Global) {
    this.activeTub = 'allBooks';
    this.uc = JSON.parse(localStorage.getItem('userConfiguration'));
    this.formsFiles = new Login();
    this.formsFiles.formFields = null;
  }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user'));

    this._getForm();
  }

  _getForm() {
    this.registrationWriterService.getFormsFile(this.uc.processInstanceId)
      .subscribe(res => {
        this.formsFiles.processInstanceId = res['processInstanceId'];
        this.formsFiles.taskId = res['taskId']
        this.formsFiles.formFields = this.global._parserForms(res['formFields']);
      })
  }

  onEmitMenu(type: any) {
    console.log(type)
    this.activeTub = type;
  }
}
