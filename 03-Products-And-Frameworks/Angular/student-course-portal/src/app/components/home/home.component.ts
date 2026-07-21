import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  readonly welcomeMessage = 'Welcome to the Student Course Portal';
  readonly description =
    'Browse courses, manage CRUD via JSON Server, and use NgRx for state (Hands-On 1–9).';
}
