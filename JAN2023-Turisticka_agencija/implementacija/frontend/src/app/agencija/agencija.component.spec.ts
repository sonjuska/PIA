import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgencijaComponent } from './agencija.component';

describe('AgencijaComponent', () => {
  let component: AgencijaComponent;
  let fixture: ComponentFixture<AgencijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgencijaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgencijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
