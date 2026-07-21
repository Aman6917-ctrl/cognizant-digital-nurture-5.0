import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Course, CourseLevel } from '../../models/course.model';
import {
  createCourse,
  deleteCourse,
  loadCourses,
  updateCourse,
} from '../../store/courses/courses.actions';
import {
  selectAllCourses,
  selectCoursesError,
  selectCoursesLoading,
} from '../../store/courses/courses.selectors';

@Component({
  selector: 'app-course-admin',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, NgFor, AsyncPipe],
  templateUrl: './course-admin.component.html',
  styleUrl: './course-admin.component.css',
})
export class CourseAdminComponent implements OnInit {
  private readonly store = inject(Store);
  private readonly fb = inject(FormBuilder);

  readonly courses$ = this.store.select(selectAllCourses);
  readonly loading$ = this.store.select(selectCoursesLoading);
  readonly error$ = this.store.select(selectCoursesError);

  editingCourseId: number | null = null;

  readonly courseForm = this.fb.nonNullable.group({
    title: ['', [Validators.required, Validators.minLength(3)]],
    credits: [3, [Validators.required, Validators.min(1), Validators.max(6)]],
    level: ['Beginner' as CourseLevel, Validators.required],
    isActive: [true],
  });

  ngOnInit(): void {
    this.store.dispatch(loadCourses());
  }

  onSubmit(): void {
    if (this.courseForm.invalid) {
      this.courseForm.markAllAsTouched();
      return;
    }

    const value = this.courseForm.getRawValue();
    if (this.editingCourseId === null) {
      this.store.dispatch(
        createCourse({
          course: {
            title: value.title,
            credits: value.credits,
            level: value.level,
            isActive: value.isActive,
          },
        }),
      );
    } else {
      const course: Course = {
        id: this.editingCourseId,
        title: value.title,
        credits: value.credits,
        level: value.level,
        isActive: value.isActive,
      };
      this.store.dispatch(updateCourse({ course }));
    }

    this.resetForm();
  }

  startEdit(course: Course): void {
    this.editingCourseId = course.id;
    this.courseForm.patchValue({
      title: course.title,
      credits: course.credits,
      level: course.level,
      isActive: course.isActive,
    });
  }

  removeCourse(id: number): void {
    this.store.dispatch(deleteCourse({ id }));
    if (this.editingCourseId === id) {
      this.resetForm();
    }
  }

  resetForm(): void {
    this.editingCourseId = null;
    this.courseForm.reset({
      title: '',
      credits: 3,
      level: 'Beginner',
      isActive: true,
    });
  }
}
