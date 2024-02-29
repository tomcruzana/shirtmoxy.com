import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductSearchFilterComponent } from './product-search-filter.component';

describe('ProductSearchFilterComponent', () => {
  let component: ProductSearchFilterComponent;
  let fixture: ComponentFixture<ProductSearchFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductSearchFilterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductSearchFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
