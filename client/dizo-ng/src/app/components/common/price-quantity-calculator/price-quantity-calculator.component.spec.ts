import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceQuantityCalculatorComponent } from './price-quantity-calculator.component';

describe('PriceQuantityCalculatorComponent', () => {
  let component: PriceQuantityCalculatorComponent;
  let fixture: ComponentFixture<PriceQuantityCalculatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceQuantityCalculatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PriceQuantityCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
