import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
import { CourseApiService } from './course-api.service';

@Injectable({ providedIn: 'root' })
export class CourseService {
  private readonly courseApi = inject(CourseApiService);

  getCourses(): Observable<Course[]> {
    return this.courseApi.getAll();
  }

  getCourseById(id: number): Observable<Course> {
    return this.courseApi.getById(id);
  }
}
