import { createReducer, on } from '@ngrx/store';
import { Course } from '../../models/course.model';
import * as CoursesActions from './courses.actions';

export interface CoursesState {
  courses: Course[];
  loading: boolean;
  error: string | null;
}

export const initialCoursesState: CoursesState = {
  courses: [],
  loading: false,
  error: null,
};

export const coursesReducer = createReducer(
  initialCoursesState,
  on(CoursesActions.loadCourses, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),
  on(CoursesActions.loadCoursesSuccess, (state, { courses }) => ({
    ...state,
    courses,
    loading: false,
    error: null,
  })),
  on(CoursesActions.loadCoursesFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error,
  })),
  on(CoursesActions.createCourse, CoursesActions.updateCourse, CoursesActions.deleteCourse, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),
  on(CoursesActions.createCourseSuccess, (state, { course }) => ({
    ...state,
    courses: [...state.courses, course],
    loading: false,
  })),
  on(CoursesActions.updateCourseSuccess, (state, { course }) => ({
    ...state,
    courses: state.courses.map((item) => (item.id === course.id ? course : item)),
    loading: false,
  })),
  on(CoursesActions.deleteCourseSuccess, (state, { id }) => ({
    ...state,
    courses: state.courses.filter((course) => course.id !== id),
    loading: false,
  })),
  on(
    CoursesActions.createCourseFailure,
    CoursesActions.updateCourseFailure,
    CoursesActions.deleteCourseFailure,
    (state, { error }) => ({
      ...state,
      loading: false,
      error,
    }),
  ),
);
