import { Routes } from '@angular/router';

export const COURSES_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./courses-shell.component').then((m) => m.CoursesShellComponent),
    children: [
      {
        path: '',
        loadComponent: () =>
          import('../../components/course-list/course-list.component').then((m) => m.CourseListComponent),
        title: 'Course Catalog',
      },
      {
        path: ':courseId',
        loadComponent: () =>
          import('./course-detail-shell.component').then((m) => m.CourseDetailShellComponent),
        children: [
          { path: '', redirectTo: 'details', pathMatch: 'full' },
          {
            path: 'details',
            loadComponent: () =>
              import('./course-detail.component').then((m) => m.CourseDetailComponent),
            title: 'Course Details',
          },
          {
            path: 'syllabus',
            loadComponent: () =>
              import('./course-syllabus.component').then((m) => m.CourseSyllabusComponent),
            title: 'Course Syllabus',
          },
        ],
      },
    ],
  },
];
