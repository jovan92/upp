import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.scss']
})
export class UserHomeComponent implements OnInit {

  activeTub: String;
  constructor() {
    this.activeTub = 'allBooks';
  }

  ngOnInit(): void {
  }

  onEmitMenu(type: any) {
    console.log(type)
    this.activeTub = type;
  }
}
