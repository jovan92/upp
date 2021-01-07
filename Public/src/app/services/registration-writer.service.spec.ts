import { TestBed } from '@angular/core/testing';

import { RegistrationWriterService } from './registration-writer.service';

describe('RegistrationWriterService', () => {
  let service: RegistrationWriterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrationWriterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
