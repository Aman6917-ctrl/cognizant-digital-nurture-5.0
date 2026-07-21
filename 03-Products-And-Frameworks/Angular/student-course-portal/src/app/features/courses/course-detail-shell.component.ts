import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-course-detail-shell',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <section class="detail-shell">
      <nav class="tabs">
        <a [routerLink]="['details']" routerLinkActive="active">Details</a>
        <a [routerLink]="['syllabus']" routerLinkActive="active">Syllabus</a>
      </nav>
      <router-outlet />
    </section>
  `,
  styles: [
    `
      .tabs {
        display: flex;
        gap: 1rem;
        margin: 0.5rem 0 1rem;
      }
      .tabs a.active {
        font-weight: 700;
        text-decoration: underline;
      }
    `,
  ],
})
export class CourseDetailShellComponent {}
