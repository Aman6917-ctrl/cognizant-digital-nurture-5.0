import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class HttpErrorService {
  private readonly lastError = signal<string | null>(null);

  readonly errorMessage = this.lastError.asReadonly();

  setError(message: string): void {
    this.lastError.set(message);
  }

  clearError(): void {
    this.lastError.set(null);
  }
}
