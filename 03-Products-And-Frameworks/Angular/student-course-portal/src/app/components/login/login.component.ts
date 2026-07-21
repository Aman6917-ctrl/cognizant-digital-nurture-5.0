import { NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgIf, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  username = '';
  password = '';
  errorMessage = '';

  onLogin(): void {
    const success = this.auth.login(this.username, this.password);
    if (success) {
      void this.router.navigate(['/profile']);
      return;
    }
    this.errorMessage = 'Invalid credentials. Use any username and password with 4+ characters.';
  }
}
