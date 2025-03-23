import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdavacComponent } from './prodavac.component';

describe('ProdavacComponent', () => {
  let component: ProdavacComponent;
  let fixture: ComponentFixture<ProdavacComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProdavacComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProdavacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
