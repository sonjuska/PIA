import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UmetninaComponent } from './umetnina.component';

describe('UmetninaComponent', () => {
  let component: UmetninaComponent;
  let fixture: ComponentFixture<UmetninaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UmetninaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UmetninaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
