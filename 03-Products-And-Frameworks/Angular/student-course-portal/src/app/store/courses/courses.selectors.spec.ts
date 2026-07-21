import {
  selectActiveCourses,
  selectAllCourses,
  selectCoursesError,
  selectCoursesLoading,
} from './courses.selectors';
import { CoursesState, initialCoursesState } from './courses.reducer';
import { mockCourses } from '../../testing/test-data';

describe('Courses Selectors', () => {
  const state: { courses: CoursesState } = {
    courses: {
      ...initialCoursesState,
      courses: mockCourses,
      loading: true,
      error: 'Oops',
    },
  };

  it('selectAllCourses should return courses array', () => {
    expect(selectAllCourses(state)).toEqual(mockCourses);
  });

  it('selectCoursesLoading should return loading flag', () => {
    expect(selectCoursesLoading(state)).toBeTrue();
  });

  it('selectCoursesError should return error message', () => {
    expect(selectCoursesError(state)).toBe('Oops');
  });

  it('selectActiveCourses should filter active courses', () => {
    const active = selectActiveCourses(state);
    expect(active.every((course) => course.isActive)).toBeTrue();
  });
});
