import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly authenticated = signal(false);
  private readonly username = signal<string | null>(null);
  private readonly accessToken = signal<string | null>(null);

  isAuthenticated(): boolean {
    return this.authenticated();
  }

  currentUsername(): string | null {
    return this.username();
  }

  getAccessToken(): string | null {
    return this.accessToken();
  }

  login(username: string, password: string): boolean {
    const valid = username.trim().length > 0 && password.trim().length >= 4;
    if (valid) {
      this.authenticated.set(true);
      this.username.set(username.trim());
      this.accessToken.set(`demo-token-${username.trim()}`);
    }
    return valid;
  }

  logout(): void {
    this.authenticated.set(false);
    this.username.set(null);
    this.accessToken.set(null);
  }
}
