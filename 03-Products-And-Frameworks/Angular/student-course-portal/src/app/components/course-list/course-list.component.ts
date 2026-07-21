import { AsyncPipe, NgFor, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault } from '@angular/common';
import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { Course } from '../../models/course.model';
import { loadCourses } from '../../store/courses/courses.actions';
import {
  selectAllCourses,
  selectCoursesError,
  selectCoursesLoading,
} from '../../store/courses/courses.selectors';
import { CourseCardComponent } from '../course-card/course-card.component';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgFor,
    NgSwitch,
    NgSwitchCase,
    NgSwitchDefault,
    CourseCardComponent,
    AsyncPipe,
  ],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css',
})
export class CourseListComponent implements OnInit, OnDestroy {
  private readonly store = inject(Store);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private querySub?: Subscription;

  readonly courses$ = this.store.select(selectAllCourses);
  readonly loading$ = this.store.select(selectCoursesLoading);
  readonly storeError$ = this.store.select(selectCoursesError);

  searchTerm = '';
  showInactiveCourses = true;
  selectedCourseId: number | null = null;
  lastActionMessage = 'Select a course to view details.';
  courses: Course[] = [];
  private coursesSub?: Subscription;

  ngOnInit(): void {
    this.store.dispatch(loadCourses());

    this.coursesSub = this.courses$.subscribe((courses) => {
      this.courses = courses;
    });

    this.querySub = this.route.queryParamMap.subscribe((params) => {
      this.searchTerm = params.get('search') ?? '';
      this.showInactiveCourses = params.get('activeOnly') !== 'true';
    });
  }

  ngOnDestroy(): void {
    this.querySub?.unsubscribe();
    this.coursesSub?.unsubscribe();
  }

  get filteredCourses(): Course[] {
    const term = this.searchTerm.trim().toLowerCase();
    return this.courses.filter((course) => {
      const matchesSearch = !term || course.title.toLowerCase().includes(term);
      const matchesActiveFilter = this.showInactiveCourses || course.isActive;
      return matchesSearch && matchesActiveFilter;
    });
  }

  onCourseSelected(course: Course): void {
    this.selectedCourseId = course.id;
    this.lastActionMessage = `Selected course: ${course.title}`;
    void this.router.navigate(['/courses', course.id, 'details']);
  }

  onEnroll(course: Course): void {
    this.lastActionMessage = `Enroll clicked for: ${course.title}`;
    void this.router.navigate(['/enrollment'], { queryParams: { courseId: course.id } });
  }

  trackByCourseId(_index: number, course: Course): number {
    return course.id;
  }

  clearSearch(): void {
    this.updateQueryParams('', this.showInactiveCourses);
    this.lastActionMessage = 'Search cleared.';
  }

  onSearchTermChange(value: string): void {
    this.updateQueryParams(value, this.showInactiveCourses);
  }

  onActiveFilterChange(showInactive: boolean): void {
    this.updateQueryParams(this.searchTerm, showInactive);
  }

  private updateQueryParams(search: string, showInactive: boolean): void {
    void this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        search: search.trim() || null,
        activeOnly: showInactive ? null : 'true',
      },
      queryParamsHandling: 'merge',
    });
  }
}
