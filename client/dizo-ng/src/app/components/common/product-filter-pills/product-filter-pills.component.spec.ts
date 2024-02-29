import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductFilterPillsComponent } from './product-filter-pills.component';

describe('ProductFilterPillsComponent', () => {
  let component: ProductFilterPillsComponent;
  let fixture: ComponentFixture<ProductFilterPillsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductFilterPillsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductFilterPillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
