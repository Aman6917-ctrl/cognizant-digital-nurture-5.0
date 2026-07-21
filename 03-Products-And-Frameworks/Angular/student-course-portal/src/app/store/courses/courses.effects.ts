import { inject, Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, switchMap, tap } from 'rxjs/operators';
import { of } from 'rxjs';
import { CourseApiService } from '../../services/course-api.service';
import * as CoursesActions from './courses.actions';

@Injectable()
export class CoursesEffects {
  private readonly actions$ = inject(Actions);
  private readonly courseApi = inject(CourseApiService);

  loadCourses$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CoursesActions.loadCourses),
      switchMap(() =>
        this.courseApi.getAll().pipe(
          map((courses) => CoursesActions.loadCoursesSuccess({ courses })),
          catchError((error: Error) =>
            of(CoursesActions.loadCoursesFailure({ error: error.message ?? 'Load failed' })),
          ),
        ),
      ),
    ),
  );

  createCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CoursesActions.createCourse),
      switchMap(({ course }) =>
        this.courseApi.create(course).pipe(
          map((created) => CoursesActions.createCourseSuccess({ course: created })),
          catchError((error: Error) =>
            of(CoursesActions.createCourseFailure({ error: error.message ?? 'Create failed' })),
          ),
        ),
      ),
    ),
  );

  updateCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CoursesActions.updateCourse),
      switchMap(({ course }) =>
        this.courseApi.update(course).pipe(
          map((updated) => CoursesActions.updateCourseSuccess({ course: updated })),
          catchError((error: Error) =>
            of(CoursesActions.updateCourseFailure({ error: error.message ?? 'Update failed' })),
          ),
        ),
      ),
    ),
  );

  deleteCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CoursesActions.deleteCourse),
      switchMap(({ id }) =>
        this.courseApi.delete(id).pipe(
          map(() => CoursesActions.deleteCourseSuccess({ id })),
          catchError((error: Error) =>
            of(CoursesActions.deleteCourseFailure({ error: error.message ?? 'Delete failed' })),
          ),
        ),
      ),
    ),
  );

  logCrud$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(
          CoursesActions.createCourseSuccess,
          CoursesActions.updateCourseSuccess,
          CoursesActions.deleteCourseSuccess,
        ),
        tap((action) => console.log('[CoursesEffects]', action.type, action)),
      ),
    { dispatch: false },
  );
}
