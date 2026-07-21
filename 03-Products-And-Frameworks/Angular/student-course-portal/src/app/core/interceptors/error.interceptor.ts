import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { HttpErrorService } from '../services/http-error.service';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const httpErrorService = inject(HttpErrorService);
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      const message =
        error.error?.message ??
        error.message ??
        `HTTP ${error.status}: ${error.statusText || 'Request failed'}`;
      httpErrorService.setError(message);
      return throwError(() => error);
    }),
  );
};
