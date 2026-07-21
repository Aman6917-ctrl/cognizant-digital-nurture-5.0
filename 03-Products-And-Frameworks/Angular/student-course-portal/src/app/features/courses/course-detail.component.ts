import { NgIf } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [NgIf, RouterLink, CreditLabelPipe],
  template: `
    <section *ngIf="course as c" class="detail">
      <h3>{{ c.title }}</h3>
      <p>Route parameter courseId: <strong>{{ courseId }}</strong></p>
      <p>Credits: {{ c.credits | creditLabel }}</p>
      <p>Level: {{ c.level }}</p>
      <p>Status: {{ c.isActive ? 'Active' : 'Inactive' }}</p>
      <a [routerLink]="['/courses']" [queryParams]="{ search: c.title }">Back to catalog with search query</a>
    </section>
    <p *ngIf="loadError" class="error">{{ loadError }}</p>
    <p *ngIf="!course && !loadError">Loading course…</p>
  `,
  styles: ['.error { color: #b91c1c; }'],
})
export class CourseDetailComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly courseService = inject(CourseService);

  courseId = '';
  course?: Course;
  loadError = '';

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.courseId = params.get('courseId') ?? '';
      const id = Number(this.courseId);
      if (Number.isNaN(id)) {
        this.loadError = 'Invalid course id';
        return;
      }
      this.course = undefined;
      this.loadError = '';
      this.courseService.getCourseById(id).subscribe({
        next: (course) => {
          this.course = course;
        },
        error: (error: Error) => {
          this.loadError = error.message;
        },
      });
    });
  }
}
