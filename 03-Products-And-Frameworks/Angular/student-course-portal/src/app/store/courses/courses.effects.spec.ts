import { TestBed } from '@angular/core/testing';
import { provideMockActions } from '@ngrx/effects/testing';
import { Observable, of, throwError } from 'rxjs';
import { CourseApiService } from '../../services/course-api.service';
import { mockCourse, mockCourses } from '../../testing/test-data';
import * as CoursesActions from './courses.actions';
import { CoursesEffects } from './courses.effects';

describe('CoursesEffects', () => {
  let actions$: Observable<unknown>;
  let effects: CoursesEffects;
  let apiSpy: jasmine.SpyObj<CourseApiService>;

  beforeEach(() => {
    apiSpy = jasmine.createSpyObj<CourseApiService>('CourseApiService', [
      'getAll',
      'create',
      'update',
      'delete',
    ]);

    TestBed.configureTestingModule({
      providers: [
        CoursesEffects,
        provideMockActions(() => actions$),
        { provide: CourseApiService, useValue: apiSpy },
      ],
    });

    effects = TestBed.inject(CoursesEffects);
  });

  it('loadCourses$ should emit success action', (done) => {
    apiSpy.getAll.and.returnValue(of(mockCourses));
    actions$ = of(CoursesActions.loadCourses());

    effects.loadCourses$.subscribe((action) => {
      expect(action).toEqual(CoursesActions.loadCoursesSuccess({ courses: mockCourses }));
      done();
    });
  });

  it('loadCourses$ should emit failure action on error', (done) => {
    apiSpy.getAll.and.returnValue(throwError(() => new Error('Network error')));
    actions$ = of(CoursesActions.loadCourses());

    effects.loadCourses$.subscribe((action) => {
      expect(action).toEqual(CoursesActions.loadCoursesFailure({ error: 'Network error' }));
      done();
    });
  });

  it('createCourse$ should emit createCourseSuccess', (done) => {
    const payload = {
      title: 'New',
      credits: 3,
      level: 'Beginner' as const,
      isActive: true,
    };
    apiSpy.create.and.returnValue(of({ id: 10, ...payload }));
    actions$ = of(CoursesActions.createCourse({ course: payload }));

    effects.createCourse$.subscribe((action) => {
      expect(action).toEqual(
        CoursesActions.createCourseSuccess({ course: { id: 10, ...payload } }),
      );
      done();
    });
  });

  it('deleteCourse$ should emit deleteCourseSuccess', (done) => {
    apiSpy.delete.and.returnValue(of(void 0));
    actions$ = of(CoursesActions.deleteCourse({ id: mockCourse.id }));

    effects.deleteCourse$.subscribe((action) => {
      expect(action).toEqual(CoursesActions.deleteCourseSuccess({ id: mockCourse.id }));
      done();
    });
  });
});
