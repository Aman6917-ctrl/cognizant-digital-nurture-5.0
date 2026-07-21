import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { CourseApiService } from './course-api.service';
import { CourseService } from './course.service';
import { mockCourse, mockCourses } from '../testing/test-data';

describe('CourseService', () => {
  let service: CourseService;
  let apiSpy: jasmine.SpyObj<CourseApiService>;

  beforeEach(() => {
    apiSpy = jasmine.createSpyObj<CourseApiService>('CourseApiService', ['getAll', 'getById']);
    TestBed.configureTestingModule({
      providers: [CourseService, { provide: CourseApiService, useValue: apiSpy }],
    });
    service = TestBed.inject(CourseService);
  });

  it('should delegate getCourses to CourseApiService', (done) => {
    apiSpy.getAll.and.returnValue(of(mockCourses));
    service.getCourses().subscribe((courses) => {
      expect(courses).toEqual(mockCourses);
      expect(apiSpy.getAll).toHaveBeenCalled();
      done();
    });
  });

  it('should delegate getCourseById to CourseApiService', (done) => {
    apiSpy.getById.and.returnValue(of(mockCourse));
    service.getCourseById(1).subscribe((course) => {
      expect(course).toEqual(mockCourse);
      expect(apiSpy.getById).toHaveBeenCalledWith(1);
      done();
    });
  });
});
