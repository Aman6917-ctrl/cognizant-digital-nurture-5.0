import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { StudentFeedbackComponent } from './student-feedback.component';

describe('StudentFeedbackComponent', () => {
  let fixture: ComponentFixture<StudentFeedbackComponent>;
  let component: StudentFeedbackComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentFeedbackComponent, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(StudentFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create feedback form', () => {
    expect(component).toBeTruthy();
    expect(fixture.nativeElement.querySelector('form')).toBeTruthy();
  });

  it('should not submit invalid form', () => {
    component.onSubmit({ valid: false, value: {} } as never);
    expect(component.submittedPayload).toBeNull();
  });

  it('should capture payload on valid submit', () => {
    component.onSubmit({
      valid: true,
      value: { fullName: 'Aman', email: 'a@b.com', rating: 5, comments: 'Great portal experience' },
    } as never);
    expect(component.submittedPayload?.['fullName']).toBe('Aman');
  });
});
