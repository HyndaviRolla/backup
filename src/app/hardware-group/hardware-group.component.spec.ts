import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HardwareGroupComponent } from './hardware-group.component';

describe('HardwareGroupComponent', () => {
  let component: HardwareGroupComponent;
  let fixture: ComponentFixture<HardwareGroupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HardwareGroupComponent]
    });
    fixture = TestBed.createComponent(HardwareGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
