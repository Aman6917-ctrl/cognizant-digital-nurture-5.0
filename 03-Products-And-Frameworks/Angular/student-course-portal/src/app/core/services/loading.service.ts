import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LoadingService {
  private readonly activeRequests = signal(0);

  readonly isLoading = signal(false);

  show(): void {
    const next = this.activeRequests() + 1;
    this.activeRequests.set(next);
    this.isLoading.set(true);
  }

  hide(): void {
    const next = Math.max(0, this.activeRequests() - 1);
    this.activeRequests.set(next);
    this.isLoading.set(next > 0);
  }
}
