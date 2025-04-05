import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PutnikComponent } from './putnik.component';

describe('PutnikComponent', () => {
  let component: PutnikComponent;
  let fixture: ComponentFixture<PutnikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PutnikComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PutnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
