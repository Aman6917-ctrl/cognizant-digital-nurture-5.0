import * as CoursesActions from './courses.actions';
import { coursesReducer, initialCoursesState } from './courses.reducer';
import { mockCourse, mockCourses } from '../../testing/test-data';

describe('coursesReducer', () => {
  it('should return initial state for unknown action', () => {
    const state = coursesReducer(undefined, { type: 'Unknown' } as never);
    expect(state).toEqual(initialCoursesState);
  });

  it('should set loading on loadCourses', () => {
    const state = coursesReducer(initialCoursesState, CoursesActions.loadCourses());
    expect(state.loading).toBeTrue();
    expect(state.error).toBeNull();
  });

  it('should store courses on loadCoursesSuccess', () => {
    const state = coursesReducer(
      { ...initialCoursesState, loading: true },
      CoursesActions.loadCoursesSuccess({ courses: mockCourses }),
    );
    expect(state.loading).toBeFalse();
    expect(state.courses).toEqual(mockCourses);
  });

  it('should store error on loadCoursesFailure', () => {
    const state = coursesReducer(
      initialCoursesState,
      CoursesActions.loadCoursesFailure({ error: 'Failed' }),
    );
    expect(state.loading).toBeFalse();
    expect(state.error).toBe('Failed');
  });

  it('should append course on createCourseSuccess', () => {
    const state = coursesReducer(
      { ...initialCoursesState, courses: mockCourses },
      CoursesActions.createCourseSuccess({ course: { ...mockCourse, id: 99 } }),
    );
    expect(state.courses.length).toBe(3);
  });

  it('should remove course on deleteCourseSuccess', () => {
    const state = coursesReducer(
      { ...initialCoursesState, courses: mockCourses },
      CoursesActions.deleteCourseSuccess({ id: 1 }),
    );
    expect(state.courses.length).toBe(1);
    expect(state.courses[0].id).toBe(2);
  });
});
