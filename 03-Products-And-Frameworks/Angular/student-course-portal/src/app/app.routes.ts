import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, title: 'Home' },
  { path: 'login', component: LoginComponent, title: 'Login' },
  {
    path: 'feedback',
    loadComponent: () =>
      import('./components/student-feedback/student-feedback.component').then(
        (m) => m.StudentFeedbackComponent,
      ),
    title: 'Student Feedback',
  },
  {
    path: 'courses',
    loadChildren: () => import('./features/courses/courses.routes').then((m) => m.COURSES_ROUTES),
  },
  {
    path: 'enrollment',
    loadChildren: () =>
      import('./features/enrollment/enrollment.routes').then((m) => m.ENROLLMENT_ROUTES),
  },
  {
    path: 'course-admin',
    loadChildren: () =>
      import('./features/course-admin/course-admin.routes').then((m) => m.COURSE_ADMIN_ROUTES),
  },
  {
    path: 'profile',
    component: StudentProfileComponent,
    canActivate: [authGuard],
    title: 'Student Profile',
  },
  { path: '404', component: NotFoundComponent, title: 'Not Found' },
  { path: '**', component: NotFoundComponent, title: 'Not Found' },
];
