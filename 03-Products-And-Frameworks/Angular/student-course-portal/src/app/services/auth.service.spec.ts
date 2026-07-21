import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    service = new AuthService();
  });

  it('should start unauthenticated', () => {
    expect(service.isAuthenticated()).toBeFalse();
    expect(service.getAccessToken()).toBeNull();
  });

  it('should login with valid credentials and expose token', () => {
    const ok = service.login('student1', 'pass1234');
    expect(ok).toBeTrue();
    expect(service.isAuthenticated()).toBeTrue();
    expect(service.currentUsername()).toBe('student1');
    expect(service.getAccessToken()).toContain('demo-token-student1');
  });

  it('should reject invalid login', () => {
    const ok = service.login('', 'x');
    expect(ok).toBeFalse();
    expect(service.isAuthenticated()).toBeFalse();
  });

  it('should logout and clear token', () => {
    service.login('student1', 'pass1234');
    service.logout();
    expect(service.isAuthenticated()).toBeFalse();
    expect(service.getAccessToken()).toBeNull();
  });
});
