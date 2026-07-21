import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, retry, switchMap, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Course } from '../models/course.model';

export type CreateCourseRequest = Omit<Course, 'id'>;

@Injectable({ providedIn: 'root' })
export class CourseApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiBaseUrl}/courses`;

  getAll(): Observable<Course[]> {
    return this.http.get<Course[]>(this.baseUrl).pipe(
      retry(2),
      map((courses) => courses.map((course) => this.normalizeCourse(course))),
      tap((courses) => console.log('[CourseApiService] getAll', courses.length)),
      catchError((error) => {
        console.error('[CourseApiService] getAll failed', error);
        return throwError(() => error);
      }),
    );
  }

  getById(id: number): Observable<Course> {
    return this.getAll().pipe(
      switchMap((courses) => {
        const course = courses.find((item) => item.id === id);
        return course ? of(course) : throwError(() => new Error(`Course ${id} not found`));
      }),
      tap((course) => console.log('[CourseApiService] getById', course.id)),
    );
  }

  create(payload: CreateCourseRequest): Observable<Course> {
    return this.http.post<Course>(this.baseUrl, payload).pipe(
      map((course) => this.normalizeCourse(course)),
      tap((course) => console.log('[CourseApiService] create', course.id)),
      catchError((error) => throwError(() => error)),
    );
  }

  update(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.baseUrl}/${course.id}`, course).pipe(
      map((updated) => this.normalizeCourse(updated)),
      tap((updated) => console.log('[CourseApiService] update', updated.id)),
      catchError((error) => throwError(() => error)),
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`).pipe(
      tap(() => console.log('[CourseApiService] delete', id)),
      catchError((error) => throwError(() => error)),
    );
  }

  private normalizeCourse(course: Course): Course {
    return {
      ...course,
      id: Number(course.id),
      credits: Number(course.credits),
      isActive: Boolean(course.isActive),
    };
  }
}
