import { TestBed } from '@angular/core/testing';

import { NetworkElementService } from './network-element.service';

describe('NetworkElementService', () => {
  let service: NetworkElementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NetworkElementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
