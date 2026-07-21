import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="not-found">
      <h2>404 — Page Not Found</h2>
      <p>The route you requested does not exist in the Student Course Portal.</p>
      <a routerLink="/home">Return to Home</a>
    </section>
  `,
  styles: [
    `
      .not-found {
        padding: 2rem 1.5rem;
      }
    `,
  ],
})
export class NotFoundComponent {}
