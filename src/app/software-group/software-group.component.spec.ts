import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SoftwareGroupComponent } from './software-group.component';

describe('SoftwareGroupComponent', () => {
  let component: SoftwareGroupComponent;
  let fixture: ComponentFixture<SoftwareGroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SoftwareGroupComponent]
    });
    fixture = TestBed.createComponent(SoftwareGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
