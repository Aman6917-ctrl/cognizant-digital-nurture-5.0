import { JsonPipe, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-student-feedback',
  standalone: true,
  imports: [FormsModule, NgIf, JsonPipe],
  templateUrl: './student-feedback.component.html',
  styleUrl: './student-feedback.component.css',
})
export class StudentFeedbackComponent {
  submittedPayload: Record<string, string | number> | null = null;

  onSubmit(form: NgForm): void {
    if (!form.valid) {
      return;
    }
    this.submittedPayload = { ...(form.value as Record<string, string | number>) };
  }
}
