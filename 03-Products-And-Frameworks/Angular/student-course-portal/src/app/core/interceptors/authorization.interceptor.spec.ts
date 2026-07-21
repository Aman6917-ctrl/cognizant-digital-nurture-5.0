import { TestBed } from '@angular/core/testing';
import { HttpClient, provideHttpClient, withInterceptors } from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { authorizationInterceptor } from './authorization.interceptor';
import { AuthService } from '../../services/auth.service';

describe('authorizationInterceptor', () => {
  let http: HttpClient;
  let httpMock: HttpTestingController;
  let auth: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(withInterceptors([authorizationInterceptor])),
        provideHttpClientTesting(),
        AuthService,
      ],
    });
    http = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
    auth = TestBed.inject(AuthService);
  });

  afterEach(() => httpMock.verify());

  it('should attach Authorization header when token exists', () => {
    auth.login('tester', 'pass1234');
    http.get('/api/courses').subscribe();
    const req = httpMock.expectOne('/api/courses');
    expect(req.request.headers.get('Authorization')).toBe('Bearer demo-token-tester');
    req.flush([]);
  });

  it('should not attach Authorization header when logged out', () => {
    http.get('/api/courses').subscribe();
    const req = httpMock.expectOne('/api/courses');
    expect(req.request.headers.has('Authorization')).toBeFalse();
    req.flush([]);
  });
});
