import { Routes } from '@angular/router';
import { authGuard } from '../../guards/auth.guard';

export const COURSE_ADMIN_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./course-admin.component').then((m) => m.CourseAdminComponent),
    canActivate: [authGuard],
    title: 'Course Admin',
  },
];
