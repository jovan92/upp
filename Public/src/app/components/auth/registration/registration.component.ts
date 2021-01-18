import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { Login, Genres, FormFields } from 'src/app/model/login';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractControl, Form, FormControl, FormGroup, NgForm, ValidatorFn, Validators } from '@angular/forms';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  forms: Login;
  multiple: any;
  genres: any;
  formGenres: any;
  newGenres: any;
  isBasic: Boolean;
  isShow: Boolean;
  isModal: Boolean;
  isSuccessSave: Boolean;
  isSpiner: Boolean;
  constructor(private registrationService : RegistrationService, private router : Router) {
    this.isModal = false;
    this.isBasic = true;
    this.isSuccessSave = false;
    this.isSpiner = true;
  }

  ngOnInit(): void {
    this._getForms();
    this.forms = new Login();
  }

  _getForms() {
    this.registrationService.getForms().subscribe(res => {
      this.forms.processInstanceId = res['processInstanceId'];
      this.forms.taskId = res['taskId']
      this.forms.formFields = res['formFields']
      console.log(this.forms.formFields)
      this._parserForms();
    })
  }

  _parserForms() {
    this.forms.formFields.map((field) => {
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
    
    this.forms.formFields.forEach(event => {
      let key = event.id;
      let value = event.value.value;
      createResponse.push({fieldId: key, fieldValue: value});
    })

    this.isSuccessSave = true;
    this.registrationService.save(createResponse, this.forms.taskId, this.forms.processInstanceId, 'basic')
      .subscribe(res => {
        if (res !== null && res !== true) {
          this.forms.processInstanceId = res['processInstanceId'];
          this.forms.taskId = res['taskId']
          res['formFields'].forEach(element => {
            this.forms.formFields.push(element);
          });
          console.log(this.forms.formFields)
          this._parserForms();
          this.isBasic = false;
        }
        if (res === true) {
          window.alert('Uspjesno ste se registrovali! Provjerite vas mejl!!!')
          this.router.navigate(['/']);
        }
      })
  }

  ngGenresRegistration() {
    let createResponse = [];

    this.forms.formFields.forEach(event => {
      let key = event.id;
      let value = event.value.value;
      createResponse.push({fieldId: key, fieldValue: value});
    })

    this.registrationService.save(createResponse, this.forms.taskId, this.forms.processInstanceId, 'genres')
      .subscribe(res => {
        console.log(res)
      })
  }
  
  ngSaveGenre() {
    this.registrationService.saveGenre(this.newGenres)
      .subscribe(res => {
        this.forms.formFields.map((field) => {
          if (field.typeName === 'multipleEnum_genres') {
            field.type.values.push(res);
          }
        })
      })
  }
}
