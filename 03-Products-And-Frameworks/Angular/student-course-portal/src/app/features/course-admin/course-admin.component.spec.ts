import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { MockStore, provideMockStore } from '@ngrx/store/testing';
import { CourseAdminComponent } from './course-admin.component';
import { createCourse, loadCourses } from '../../store/courses/courses.actions';
import {
  selectAllCourses,
  selectCoursesError,
  selectCoursesLoading,
} from '../../store/courses/courses.selectors';
import { mockCourses } from '../../testing/test-data';

describe('CourseAdminComponent', () => {
  let fixture: ComponentFixture<CourseAdminComponent>;
  let component: CourseAdminComponent;
  let store: MockStore;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseAdminComponent],
      providers: [
        provideRouter([]),
        provideMockStore({
          selectors: [
            { selector: selectAllCourses, value: mockCourses },
            { selector: selectCoursesLoading, value: false },
            { selector: selectCoursesError, value: null },
          ],
        }),
      ],
    }).compileComponents();

    store = TestBed.inject(MockStore);
    spyOn(store, 'dispatch');

    fixture = TestBed.createComponent(CourseAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterEach(() => {
    store.resetSelectors();
  });

  it('should create and dispatch loadCourses on init', () => {
    expect(component).toBeTruthy();
    expect(store.dispatch).toHaveBeenCalledWith(loadCourses());
  });

  it('should dispatch createCourse for valid new form', () => {
    component.courseForm.setValue({
      title: 'NgRx Testing',
      credits: 3,
      level: 'Beginner',
      isActive: true,
    });
    component.onSubmit();
    expect(store.dispatch).toHaveBeenCalledWith(
      createCourse({
        course: {
          title: 'NgRx Testing',
          credits: 3,
          level: 'Beginner',
          isActive: true,
        },
      }),
    );
  });
});
