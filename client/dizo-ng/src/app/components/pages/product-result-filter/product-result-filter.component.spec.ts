import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductResultFilterComponent } from './product-result-filter.component';

describe('ProductResultFilterComponent', () => {
  let component: ProductResultFilterComponent;
  let fixture: ComponentFixture<ProductResultFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductResultFilterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductResultFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
