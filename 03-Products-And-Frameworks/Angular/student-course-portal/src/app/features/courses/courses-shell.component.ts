import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-courses-shell',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <section class="courses-shell">
      <h2>Courses Area (Nested Routing)</h2>
      <nav class="subnav">
        <a routerLink="/courses" routerLinkActive="active" [routerLinkActiveOptions]="{ exact: true }">Catalog</a>
      </nav>
      <router-outlet />
    </section>
  `,
  styles: [
    `
      .courses-shell {
        padding: 1rem 1.5rem 0;
      }
      .subnav {
        margin-bottom: 0.5rem;
      }
      .subnav a.active {
        font-weight: 700;
      }
    `,
  ],
})
export class CoursesShellComponent {}
