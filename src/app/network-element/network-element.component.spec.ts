import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NetworkElementComponent } from './network-element.component';

describe('NetworkElementComponent', () => {
  let component: NetworkElementComponent;
  let fixture: ComponentFixture<NetworkElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NetworkElementComponent]
    });
    fixture = TestBed.createComponent(NetworkElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
