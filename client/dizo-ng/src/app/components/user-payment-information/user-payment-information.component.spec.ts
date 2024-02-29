import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPaymentInformationComponent } from './user-payment-information.component';

describe('UserPaymentInformationComponent', () => {
  let component: UserPaymentInformationComponent;
  let fixture: ComponentFixture<UserPaymentInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPaymentInformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserPaymentInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
