import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { MockStore, provideMockStore } from '@ngrx/store/testing';
import { of } from 'rxjs';
import { CourseListComponent } from './course-list.component';
import { loadCourses } from '../../store/courses/courses.actions';
import {
  selectAllCourses,
  selectCoursesError,
  selectCoursesLoading,
} from '../../store/courses/courses.selectors';
import { mockCourses } from '../../testing/test-data';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

describe('CourseListComponent', () => {
  let fixture: ComponentFixture<CourseListComponent>;
  let component: CourseListComponent;
  let store: MockStore;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseListComponent],
      providers: [
        provideRouter([]),
        provideMockStore({
          selectors: [
            { selector: selectAllCourses, value: mockCourses },
            { selector: selectCoursesLoading, value: false },
            { selector: selectCoursesError, value: null },
          ],
        }),
        {
          provide: ActivatedRoute,
          useValue: { queryParamMap: of(convertToParamMap({})) },
        },
      ],
    }).compileComponents();

    store = TestBed.inject(MockStore);
    spyOn(store, 'dispatch');

    fixture = TestBed.createComponent(CourseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterEach(() => {
    store.resetSelectors();
  });

  it('should create and dispatch loadCourses', () => {
    expect(component).toBeTruthy();
    expect(store.dispatch).toHaveBeenCalledWith(loadCourses());
  });

  it('should filter courses by search term', () => {
    component.courses = mockCourses;
    component.searchTerm = 'TypeScript';
    expect(component.filteredCourses.length).toBe(1);
    expect(component.filteredCourses[0].title).toContain('TypeScript');
  });

  it('should render course cards', () => {
    component.courses = mockCourses;
    fixture.detectChanges();
    const cards = fixture.nativeElement.querySelectorAll('app-course-card');
    expect(cards.length).toBe(2);
  });
});
