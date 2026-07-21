import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { CourseApiService } from './course-api.service';
import { mockCourse, mockCourses } from '../testing/test-data';
import { environment } from '../../environments/environment';

describe('CourseApiService', () => {
  let service: CourseApiService;
  let httpMock: HttpTestingController;
  const baseUrl = `${environment.apiBaseUrl}/courses`;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CourseApiService, provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(CourseApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch all courses', () => {
    let result: typeof mockCourses | undefined;
    service.getAll().subscribe((courses) => (result = courses));

    const req = httpMock.expectOne(baseUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);

    expect(result).toEqual(mockCourses);
  });

  it('should get course by id via switchMap on getAll', () => {
    let result: typeof mockCourse | undefined;
    service.getById(1).subscribe((course) => (result = course));

    const req = httpMock.expectOne(baseUrl);
    req.flush(mockCourses);

    expect(result).toEqual(mockCourse);
  });

  it('should create a course', () => {
    const payload = {
      title: 'New Course',
      credits: 3,
      level: 'Beginner' as const,
      isActive: true,
    };
    let created: typeof mockCourse | undefined;

    service.create(payload).subscribe((course) => (created = course));
    const req = httpMock.expectOne(baseUrl);
    expect(req.request.method).toBe('POST');
    req.flush({ id: 99, ...payload });

    expect(created?.id).toBe(99);
    expect(created?.title).toBe('New Course');
  });

  it('should update a course', () => {
    const updated = { ...mockCourse, title: 'Updated Title' };
    let result: typeof updated | undefined;

    service.update(updated).subscribe((course) => (result = course));
    const req = httpMock.expectOne(`${baseUrl}/${updated.id}`);
    expect(req.request.method).toBe('PUT');
    req.flush(updated);

    expect(result?.title).toBe('Updated Title');
  });

  it('should delete a course', () => {
    let completed = false;
    service.delete(1).subscribe(() => (completed = true));

    const req = httpMock.expectOne(`${baseUrl}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);

    expect(completed).toBeTrue();
  });
});
