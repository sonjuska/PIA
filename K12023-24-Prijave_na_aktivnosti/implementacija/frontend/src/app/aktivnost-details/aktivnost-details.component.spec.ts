import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AktivnostDetailsComponent } from './aktivnost-details.component';

describe('AktivnostDetailsComponent', () => {
  let component: AktivnostDetailsComponent;
  let fixture: ComponentFixture<AktivnostDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AktivnostDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AktivnostDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
