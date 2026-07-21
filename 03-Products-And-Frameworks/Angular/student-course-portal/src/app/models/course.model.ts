export type CourseLevel = 'Beginner' | 'Intermediate' | 'Advanced';

export interface Course {
  id: number;
  title: string;
  credits: number;
  level: CourseLevel;
  isActive: boolean;
}
