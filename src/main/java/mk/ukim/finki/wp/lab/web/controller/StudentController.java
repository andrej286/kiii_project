package mk.ukim.finki.wp.lab.web.controller;


import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;
  private final CourseService courseService;

  public StudentController(StudentService studentService, CourseService courseService) {

    this.studentService = studentService;
    this.courseService = courseService;
  }


  @GetMapping
  public String getStudentPage(@RequestParam(required = false) String error, Model model) {
    model.addAttribute("courses", this.courseService.listAllCourses());
    return "listCourses";
  }


  @PostMapping("/list")
  public String listStudents(@RequestParam Long courseId, HttpServletRequest req, Model model) {

    model.addAttribute("students", this.studentService.listAll());
    model.addAttribute("courseId", courseId);

    req.getSession().setAttribute("courseId", courseId);

    return "listStudents";
  }


  @PostMapping("/add")
  public String addStudent(@RequestParam String studentName, HttpServletRequest req, Model model) {

    Long courseId = (Long) req.getSession().getAttribute("courseId");

    this.courseService.addStudentInCourse(studentName, courseId);

    model.addAttribute("students", this.courseService.listStudentsByCourse(courseId));
    model.addAttribute("courseName", this.courseService.findCourseByCourseId(courseId).getName());

    return "studentsInCourse";
  }

  @GetMapping("/add-form/{id}")
  public String CreateStudent(@PathVariable Long id, Model model) {

    model.addAttribute("courseId", id);

    return "addStudent";
  }

  @PostMapping("/add-created")
  public String ListStudentsAdded(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String surname, HttpServletRequest req, Model model) {

    Long courseId = (Long) req.getSession().getAttribute("courseId");

    this.studentService.save(username, password, name, surname);
    this.courseService.addStudentInCourse(username, courseId);

    model.addAttribute("students", this.courseService.listStudentsByCourse(courseId));
    model.addAttribute("courseName", this.courseService.findCourseByCourseId(courseId).getName());

    return "studentsInCourse";
  }
}
