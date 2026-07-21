import { AbstractControl, AsyncValidatorFn, ValidationErrors, ValidatorFn } from '@angular/forms';
import { map, of, switchMap, timer } from 'rxjs';
import { EnrollmentService } from '../services/enrollment.service';

export function creditsRangeValidator(min: number, max: number): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = Number(control.value);
    if (Number.isNaN(value)) {
      return { creditsRange: true };
    }
    return value >= min && value <= max ? null : { creditsRange: { min, max, actual: value } };
  };
}

export function forbiddenTextValidator(forbidden: string[]): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = (control.value as string | null)?.toLowerCase() ?? '';
    const match = forbidden.find((word) => value.includes(word.toLowerCase()));
    return match ? { forbiddenText: { word: match } } : null;
  };
}

export function emailAvailabilityValidator(enrollmentService: EnrollmentService): AsyncValidatorFn {
  return (control: AbstractControl) => {
    const email = (control.value as string | null)?.trim() ?? '';
    if (!email) {
      return of(null);
    }
    return timer(300).pipe(
      switchMap(() => enrollmentService.isEmailAvailable(email)),
      map((available) => (available ? null : { emailTaken: true })),
    );
  };
}
