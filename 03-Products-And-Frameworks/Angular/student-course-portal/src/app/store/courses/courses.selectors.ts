import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CoursesState } from './courses.reducer';

export const selectCoursesState = createFeatureSelector<CoursesState>('courses');

export const selectAllCourses = createSelector(selectCoursesState, (state) => state.courses);

export const selectCoursesLoading = createSelector(selectCoursesState, (state) => state.loading);

export const selectCoursesError = createSelector(selectCoursesState, (state) => state.error);

export const selectActiveCourses = createSelector(selectAllCourses, (courses) =>
  courses.filter((course) => course.isActive),
);

export const selectCourseById = (courseId: number) =>
  createSelector(selectAllCourses, (courses) => courses.find((course) => course.id === courseId));
