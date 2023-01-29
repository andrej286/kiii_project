package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;

import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final StudentRepository studentRepository;

  public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {

    this.courseRepository = courseRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> listStudentsByCourse(Long courseId) {

    return courseRepository.findCourseByCourseId(courseId).getStudents();
  }

  @Override
  public Course findCourseByCourseId(Long courseId) {
    return courseRepository.findCourseByCourseId(courseId);
  }

  @Override
  public void addStudentInCourse(String username, Long courseId) {

    Student s = studentRepository.findStudentByUsername(username);
    Course c = courseRepository.findCourseByCourseId(courseId);

    c.getStudents().add(s);

    this.courseRepository.save(c);

  }

  @Override
  public List<Course> listAllCourses() {
    return courseRepository.findAll();
  }

  @Override
  public void removeCourseById(Long courseId) {
    this.courseRepository.deleteCourseByCourseId(courseId);
  }

  @Override
  public void updateCourse(String courseName, String description, Long teacherId, Long id) {

    Teacher teacher = courseRepository.findCourseByCourseId(id).getTeacher();

    courseRepository.findCourseByCourseId(id).setDescription(description);
    courseRepository.findCourseByCourseId(id).setName(courseName);
    courseRepository.findCourseByCourseId(id).setTeacher(teacher);
  }

  @Override
  public void addCourse(String courseName, String description, List<Student> students, Teacher teacher) {

    courseRepository.save(new Course(courseName, description, students, teacher));

  }
}
