import { Routes } from '@angular/router';
import { authGuard } from '../../guards/auth.guard';
import { canDeactivateGuard } from '../../guards/can-deactivate.guard';

export const ENROLLMENT_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./enrollment-form.component').then((m) => m.EnrollmentFormComponent),
    canActivate: [authGuard],
    canDeactivate: [canDeactivateGuard],
    title: 'Enrollment',
  },
];
