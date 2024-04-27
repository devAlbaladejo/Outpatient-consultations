import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListingHUBComponent } from './listing-hub.component';

describe('ListingHUBComponent', () => {
  let component: ListingHUBComponent;
  let fixture: ComponentFixture<ListingHUBComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListingHUBComponent]
    });
    fixture = TestBed.createComponent(ListingHUBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
