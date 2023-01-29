package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;

public interface CourseService {
  List<Student> listStudentsByCourse(Long courseId);

  void addStudentInCourse(String username, Long courseId);

  List<Course> listAllCourses();

  void removeCourseById(Long courseId);

  void updateCourse(String courseName, String description, Long teacherId, Long id);

  void addCourse(String courseName, String description, List<Student> students, Teacher teacher);

  Course findCourseByCourseId(Long courseId);
}