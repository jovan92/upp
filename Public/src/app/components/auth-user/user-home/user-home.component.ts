import { Component, OnInit } from '@angular/core';
import { RegistrationWriterService } from 'src/app/services/registration-writer.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.scss']
})
export class UserHomeComponent implements OnInit {

  activeTub: String;
  user: any;
  constructor(private registrationWriterService: RegistrationWriterService) {
    this.activeTub = 'allBooks';
  }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
    console.log(this.user)

    this._getForm(this.user);
  }

  _getForm(user) {
    this.registrationWriterService.getFormsFile()
      .subscribe(res => {
        console.log(res)
      })
  }

  onEmitMenu(type: any) {
    console.log(type)
    this.activeTub = type;
  }
}
