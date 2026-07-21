import { NgFor, NgIf } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CanComponentDeactivate } from '../../guards/can-deactivate.guard';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import {
  creditsRangeValidator,
  emailAvailabilityValidator,
  forbiddenTextValidator,
} from '../../validators/custom.validators';

@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, NgFor, RouterLink],
  templateUrl: './enrollment-form.component.html',
  styleUrl: './enrollment-form.component.css',
})
export class EnrollmentFormComponent implements OnInit, CanComponentDeactivate {
  private readonly fb = inject(FormBuilder);
  private readonly courseService = inject(CourseService);
  private readonly enrollmentService = inject(EnrollmentService);
  private readonly route = inject(ActivatedRoute);

  enrollmentForm!: FormGroup;
  submitMessage = '';
  availableCourses: { id: number; title: string }[] = [];

  ngOnInit(): void {
    this.enrollmentForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3), forbiddenTextValidator(['test', 'dummy'])]],
      email: [
        '',
        [Validators.required, Validators.email],
        [emailAvailabilityValidator(this.enrollmentService)],
      ],
      program: ['Digital Nurture 5.0', Validators.required],
      coursePreferences: this.fb.array([this.createCoursePreferenceGroup()]),
    });

    this.courseService.getCourses().subscribe((courses) => {
      this.availableCourses = courses.filter((c) => c.isActive).map((c) => ({ id: c.id, title: c.title }));
    });

    this.route.queryParamMap.subscribe((params) => {
      const courseId = Number(params.get('courseId'));
      if (!Number.isNaN(courseId)) {
        this.coursePreferences.at(0)?.patchValue({ courseId });
      }
    });
  }

  get coursePreferences(): FormArray {
    return this.enrollmentForm.get('coursePreferences') as FormArray;
  }

  createCoursePreferenceGroup(): FormGroup {
    return this.fb.group({
      courseId: [null, Validators.required],
      expectedCredits: [3, [Validators.required, creditsRangeValidator(1, 6)]],
    });
  }

  addCoursePreference(): void {
    this.coursePreferences.push(this.createCoursePreferenceGroup());
  }

  removeCoursePreference(index: number): void {
    if (this.coursePreferences.length > 1) {
      this.coursePreferences.removeAt(index);
    }
  }

  onSubmit(): void {
    if (this.enrollmentForm.invalid) {
      this.enrollmentForm.markAllAsTouched();
      return;
    }

    const value = this.enrollmentForm.getRawValue() as {
      studentName: string;
      email: string;
      program: string;
      coursePreferences: { courseId: number; expectedCredits: number }[];
    };

    this.enrollmentService
      .submitEnrollment({
        studentName: value.studentName,
        email: value.email,
        program: value.program,
        courseIds: value.coursePreferences.map((row) => Number(row.courseId)),
      })
      .subscribe((result) => {
        this.submitMessage = result.message;
        this.enrollmentForm.markAsPristine();
      });
  }

  canDeactivate(): boolean {
    if (this.enrollmentForm.dirty) {
      return confirm('You have unsaved enrollment changes. Leave this page?');
    }
    return true;
  }

  showError(controlName: string): boolean {
    const control = this.enrollmentForm.get(controlName);
    return !!control && control.invalid && control.touched;
  }
}
