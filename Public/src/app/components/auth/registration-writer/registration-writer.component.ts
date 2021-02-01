import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';
import { Login } from 'src/app/model/login';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { Global } from 'src/app/global/global';

@Component({
  selector: 'app-registration-writer',
  templateUrl: './registration-writer.component.html',
  styleUrls: ['./registration-writer.component.scss']
})
export class RegistrationWriterComponent implements OnInit {

  @ViewChild('forms') forms : ElementRef;

  isSpiner: any;
  registrationForms: Login;
  newGenres: any;
  isStyle: Boolean;
  isStyleUsername: Boolean;
  constructor(
    private registration : RegistrationWriterService, 
    private router: Router,
    private registrationService: RegistrationService,
    private global: Global
    ) { 
    this.registrationForms = new Login();
    this.isSpiner = false;
    this.isStyle = false;
    this.isStyleUsername = false;
  }

  ngOnInit(): void {
    this._getForms();
  }

  _getForms() {
    this.registration.getForms()
      .subscribe(res => {
        console.log(res);
        this.registrationForms.processInstanceId = res['processInstanceId'];
        this.registrationForms.taskId = res['taskId']
        this.registrationForms.formFields = this.global._parserForms(res['formFields']);
        console.log(this.registrationForms.formFields)
      })
  }

  ngRegistration() {
    this.isSpiner = true;

    let createResponse = [];
    
    this.registrationForms.formFields.forEach(event => {
      let key = event.id;
      let value = event.value.value;
      createResponse.push({fieldId: key, fieldValue: value});
    })

    createResponse.push({fieldId: 'writer', fieldValue: true});

    this.registration.save(createResponse, this.registrationForms.taskId, this.registrationForms.processInstanceId, 'basic')
      .subscribe(res => {
        console.log(res)
        if (res !== null && res !== true) {
          this.registrationForms.processInstanceId = res['processInstanceId'];
          this.registrationForms.taskId = res['taskId']
          res['formFields'].forEach(element => {
            this.registrationForms.formFields.push(element);
          });
          console.log(this.registrationForms.formFields)
        }
        if (res === true) {
          window.alert('Uspjesno ste sacuvali')
          this.router.navigate(['/']);
        }

        // TODO ispraviti kada je user zauzet
        if (res === 'nevalja') {
          // this.registrationForms.formFields.forEach(item => {
          //   let value = document.getElementById('username')['value'];
          //   this._setColor(item, value, 'username');
          // })
          window.alert('Korisniko ime ne valja ispraviti posle prika');
          this.isSpiner = false;
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

  _setColor(item: any, value: any, type: any) {
    item.validationConstraints.forEach(validationItem => {
      if (validationItem.configuration !== null) {
        if (parseInt(validationItem.configuration) > parseInt(value.length)) {
          this.forms.nativeElement.querySelector('#' + type.toString()).classList.add('red-green')
        } else {
          this.forms.nativeElement.querySelector('#' + type.toString()).classList.remove('red-green')
        }
      }
    })
  }

  checkInput(type: any) {
    this.registrationForms.formFields.forEach(item => {
      if (type.toString() === 'password' && item.id === 'password') {
        let value = document.getElementById('password')['value'];
        this._setColor(item, value, type);
      }

      if (type.toString() === 'username' && item.id === 'username') {
        let value = document.getElementById('username')['value'];
        this._setColor(item, value, type);
      }
      
      if (type.toString() === 'email' && item.id === 'email') {
        let value = document.getElementById('email')['value'];
        const re = /^\S+@\S+\.\S+$/

        if (!re.test(String(value).toLowerCase())) {
          this.forms.nativeElement.querySelector('#email').classList.add('red-green')
        } else {
          this.forms.nativeElement.querySelector('#email').classList.remove('red-green')
        }
      }
    })
  }
}
