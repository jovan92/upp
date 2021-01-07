import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationWriterComponent } from './registration-writer.component';

describe('RegistrationWriterComponent', () => {
  let component: RegistrationWriterComponent;
  let fixture: ComponentFixture<RegistrationWriterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationWriterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationWriterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
