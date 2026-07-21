import { Course } from '../models/course.model';

export const mockCourses: Course[] = [
  { id: 1, title: 'Angular Fundamentals', credits: 3, level: 'Beginner', isActive: true },
  { id: 2, title: 'TypeScript Essentials', credits: 2, level: 'Beginner', isActive: true },
];

export const mockCourse: Course = mockCourses[0];
