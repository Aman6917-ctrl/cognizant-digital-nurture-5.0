import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import {
  NgClass,
  NgIf,
  NgStyle,
  NgSwitch,
  NgSwitchCase,
  NgSwitchDefault,
} from '@angular/common';
import { Course } from '../../models/course.model';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { HighlightDirective } from '../../directives/highlight.directive';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CreditLabelPipe, HighlightDirective, NgClass, NgStyle, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css',
})
export class CourseCardComponent implements OnInit, OnDestroy, OnChanges {
  @Input({ required: true }) course!: Course;
  @Input() selected = false;
  @Output() courseSelected = new EventEmitter<Course>();
  @Output() enrollClicked = new EventEmitter<Course>();

  cardBorderColor = '#cbd5e1';

  ngOnInit(): void {
    console.log('[CourseCardComponent] ngOnInit:', this.course.title);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selected']) {
      this.cardBorderColor = this.selected ? '#2563eb' : '#cbd5e1';
      console.log('[CourseCardComponent] ngOnChanges selected:', this.selected);
    }
    if (changes['course']) {
      console.log('[CourseCardComponent] ngOnChanges course:', this.course?.title);
    }
  }

  ngOnDestroy(): void {
    console.log('[CourseCardComponent] ngOnDestroy:', this.course?.title);
  }

  onSelectCourse(): void {
    this.courseSelected.emit(this.course);
  }

  onEnroll(event: Event): void {
    event.stopPropagation();
    this.enrollClicked.emit(this.course);
  }
}
