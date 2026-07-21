import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay, map } from 'rxjs/operators';

export interface EnrollmentRequest {
  studentName: string;
  email: string;
  program: string;
  courseIds: number[];
}

@Injectable({ providedIn: 'root' })
export class EnrollmentService {
  private readonly reservedEmails = new Set(['taken@example.com', 'admin@example.com']);

  isEmailAvailable(email: string): Observable<boolean> {
    const normalized = email.trim().toLowerCase();
    const available = !this.reservedEmails.has(normalized);
    return of(available).pipe(delay(600));
  }

  submitEnrollment(request: EnrollmentRequest): Observable<{ success: boolean; message: string }> {
    return of({
      success: true,
      message: `Enrollment submitted for ${request.studentName} (${request.courseIds.length} course(s)).`,
    }).pipe(delay(400));
  }

  hasPendingEnrollment(email: string): Observable<boolean> {
    return this.isEmailAvailable(email).pipe(map((available) => !available));
  }
}
