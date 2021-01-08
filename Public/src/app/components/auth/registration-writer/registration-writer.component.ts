import { Component, OnInit } from '@angular/core';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';
import { Login } from 'src/app/model/login';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-registration-writer',
  templateUrl: './registration-writer.component.html',
  styleUrls: ['./registration-writer.component.scss']
})
export class RegistrationWriterComponent implements OnInit {

  registrationForms: Login;
  newGenres: any;
  constructor(
    private registration : RegistrationWriterService, 
    private router: Router,
    private registrationService: RegistrationService
    ) { 
    this.registrationForms = new Login();
  }

  ngOnInit(): void {
    this._getForms();
  }

  _getForms() {
    this.registration.getForms()
      .subscribe(res => {
        this.registrationForms.processInstanceId = res['processInstanceId'];
        this.registrationForms.taskId = res['taskId']
        this.registrationForms.formFields = res['formFields']
        this._parserForms();
        console.log(this.registrationForms.formFields)
      })
  }

  _parserForms() {
    this.registrationForms.formFields.map((field) => {
      if (field.typeName === 'multipleEnum_genres') {
        let test: string = field.type.values.all;
        field.type.values = JSON.parse(test);
      }
      if (field.typeName === 'boolean') {
        field.type['values'] = [];
        let test = JSON.stringify(field.properties);
        let test1 = test.split('{')[1];
        let testYES = test1.split('}')[0].split(',')[0].split(':');
        field.type['values'].push({id: JSON.parse(testYES[0]), type: JSON.parse(JSON.parse(testYES[1]))})
        let testNO = test1.split('}')[0].split(',')[1].split(':');
        field.type['values'].push({id: JSON.parse(testNO[0]), type: JSON.parse(JSON.parse(testNO[1]))})
      }
    })
  }

  ngRegistration() {
    let createResponse = [];
    
    this.registrationForms.formFields.forEach(event => {
      let key = event.id;
      let value = event.value.value;
      createResponse.push({fieldId: key, fieldValue: value});
    })

    createResponse.push({fieldId: 'writer', fieldValue: true});

    // console.log(createResponse)
    this.registration.save(createResponse, this.registrationForms.taskId, this.registrationForms.processInstanceId, 'basic')
      .subscribe(res => {
        if (res !== null && res !== true) {
          this.registrationForms.processInstanceId = res['processInstanceId'];
          this.registrationForms.taskId = res['taskId']
          res['formFields'].forEach(element => {
            this.registrationForms.formFields.push(element);
          });
          console.log(this.registrationForms.formFields)
          this._parserForms();
        }
        if (res === true) {
          window.alert('Uspjesno ste sacuvali')
          this.router.navigate(['/']);
        }
      })
  }

  ngSaveGenre() {
    this.registrationService.saveGenre(this.newGenres)
      .subscribe(res => {
        this.registrationForms.formFields.map((field) => {
          if (field.typeName === 'multipleEnum_genres') {
            field.type.values.push(res);
          }
        })
      })
  }
}