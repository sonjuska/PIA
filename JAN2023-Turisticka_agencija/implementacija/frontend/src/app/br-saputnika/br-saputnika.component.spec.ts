import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrSaputnikaComponent } from './br-saputnika.component';

describe('BrSaputnikaComponent', () => {
  let component: BrSaputnikaComponent;
  let fixture: ComponentFixture<BrSaputnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrSaputnikaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrSaputnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
