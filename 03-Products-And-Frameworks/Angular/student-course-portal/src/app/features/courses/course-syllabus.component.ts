import { NgFor, NgIf } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course-syllabus',
  standalone: true,
  imports: [NgIf, NgFor],
  template: `
    <section class="syllabus" *ngIf="syllabus.length > 0">
      <h3>Syllabus</h3>
      <ul>
        <li *ngFor="let topic of syllabus">{{ topic }}</li>
      </ul>
    </section>
  `,
})
export class CourseSyllabusComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly courseService = inject(CourseService);

  syllabus: string[] = [];

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const id = Number(params.get('courseId'));
      this.courseService.getCourseById(id).subscribe((course) => {
        this.syllabus = course
          ? [`Introduction to ${course.title}`, 'Guided labs', 'Assessment and recap']
          : [];
      });
    });
  }
}
