import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-meni',
  templateUrl: './meni.component.html',
  styleUrls: ['./meni.component.scss']
})
export class MeniComponent implements OnInit {
  @Input() activeTub: String;
  @Output() emit = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  openTab(type: String) {
    this.emit.emit(type);
  }

  
}
