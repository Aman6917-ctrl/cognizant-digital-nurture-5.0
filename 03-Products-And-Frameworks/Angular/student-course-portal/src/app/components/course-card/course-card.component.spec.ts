import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseCardComponent } from './course-card.component';
import { mockCourse } from '../../testing/test-data';

describe('CourseCardComponent', () => {
  let fixture: ComponentFixture<CourseCardComponent>;
  let component: CourseCardComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;
    component.course = mockCourse;
    fixture.detectChanges();
  });

  it('should create and render course title', () => {
    expect(component).toBeTruthy();
    expect(fixture.nativeElement.textContent).toContain('Angular Fundamentals');
  });

  it('should emit courseSelected when clicked', () => {
    spyOn(component.courseSelected, 'emit');
    component.onSelectCourse();
    expect(component.courseSelected.emit).toHaveBeenCalledWith(mockCourse);
  });

  it('should emit enrollClicked without bubbling click handler', () => {
    spyOn(component.enrollClicked, 'emit');
    const event = new Event('click');
    spyOn(event, 'stopPropagation');
    component.onEnroll(event);
    expect(event.stopPropagation).toHaveBeenCalled();
    expect(component.enrollClicked.emit).toHaveBeenCalledWith(mockCourse);
  });
});
