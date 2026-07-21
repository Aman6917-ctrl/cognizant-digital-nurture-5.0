import { NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { HttpErrorService } from './core/services/http-error.service';
import { LoadingService } from './core/services/loading.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, NgIf],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  readonly appName = 'student-course-portal';
  protected readonly loadingService = inject(LoadingService);
  protected readonly httpErrorService = inject(HttpErrorService);

  clearHttpError(): void {
    this.httpErrorService.clearError();
  }
}
