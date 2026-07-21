import { TestBed } from '@angular/core/testing';
import { HttpClient, provideHttpClient, withInterceptors } from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { errorInterceptor } from './error.interceptor';
import { HttpErrorService } from '../services/http-error.service';

describe('errorInterceptor', () => {
  let http: HttpClient;
  let httpMock: HttpTestingController;
  let errors: HttpErrorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(withInterceptors([errorInterceptor])),
        provideHttpClientTesting(),
        HttpErrorService,
      ],
    });
    http = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
    errors = TestBed.inject(HttpErrorService);
  });

  afterEach(() => httpMock.verify());

  it('should capture HTTP errors in HttpErrorService', () => {
    http.get('/api/fail').subscribe({
      error: () => {
        expect(errors.errorMessage()).toContain('500');
      },
    });

    httpMock.expectOne('/api/fail').flush('Server error', {
      status: 500,
      statusText: 'Server Error',
    });
  });
});
