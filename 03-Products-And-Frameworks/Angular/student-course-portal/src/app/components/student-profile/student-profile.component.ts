import { NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../../models/student.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [NgIf],
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css',
})
export class StudentProfileComponent {
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  readonly student: Student = {
    id: 1001,
    name: 'Aman Verma',
    email: 'aman.verma@example.com',
    program: 'Digital Nurture 5.0 - Java FSE',
    enrolledCredits: 12,
  };

  get username(): string | null {
    return this.auth.currentUsername();
  }

  logout(): void {
    this.auth.logout();
    void this.router.navigate(['/login']);
  }
}
