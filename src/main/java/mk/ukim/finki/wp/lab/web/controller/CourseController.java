package mk.ukim.finki.wp.lab.web.controller;


import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNameAlreadyExists;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;
  private final TeacherService teacherService;

  public CourseController(CourseService courseService, TeacherService teacherService) {

    this.courseService = courseService;
    this.teacherService = teacherService;
  }

  @GetMapping
  public String getCoursesPage(@RequestParam(required = false) String error, Model model) {

    this.courseService.listAllCourses().sort(Comparator.comparing(i -> i.getName()));
    model.addAttribute("courses", this.courseService.listAllCourses());

    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("this is the call to getCoursesPage at time: " + localDateTime);

    return "listCourses";
  }


  @GetMapping("/delete/{id}")
  @Transactional
  public String deleteCourse(@PathVariable Long id, Model model) {

    this.courseService.removeCourseById(id);

    model.addAttribute("courses", this.courseService.listAllCourses());

    LocalDateTime localDateTime = LocalDateTime.now();
    log.warn("this is the call to deleteCourse at time: " + localDateTime);

    return "listCourses";
  }


  @GetMapping("add-form")
  public String newCourse(Model model) {

    model.addAttribute("teachers", this.teacherService.findAll());

    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("this is the call to newCourse at time: " + localDateTime);

    return "add-course";
  }

  @PostMapping("/add/{id}")
  @Transactional
  public String saveCourse(@RequestParam String courseName, @RequestParam String description, @RequestParam Long teacherId, @PathVariable Long id, Model model) {


    try {

      courseService.updateCourse(courseName, description, teacherId, id);
    } catch (CourseNameAlreadyExists ex) {

      model.addAttribute("hasError", true);
      model.addAttribute("error", ex.getMessage());
      model.addAttribute("teachers", this.teacherService.findAll());
      model.addAttribute("courseId", id);
      model.addAttribute("courseName", courseName);
      model.addAttribute("description", description);

      return "add-course";
    }

    this.courseService.listAllCourses().sort(Comparator.comparing(i -> i.getName()));
    model.addAttribute("courses", this.courseService.listAllCourses());

    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("this is the call to saveCourse at time: " + localDateTime);

    return "listCourses";
  }


  @PostMapping("/add")
  @Transactional
  public String saveNewCourse(@RequestParam String courseName, @RequestParam String description, @RequestParam Long teacherId, Model model) {

    List<Student> students = new ArrayList<>();
    try {

      courseService.addCourse(courseName, description, students, this.teacherService.findById(teacherId));
    } catch (CourseNameAlreadyExists ex) {

      model.addAttribute("hasError", true);
      model.addAttribute("error", ex.getMessage());
      model.addAttribute("teachers", this.teacherService.findAll());
      model.addAttribute("courseName", courseName);
      model.addAttribute("description", description);

      return "add-course";
    }

    this.courseService.listAllCourses().sort(Comparator.comparing(i -> i.getName()));
    model.addAttribute("courses", this.courseService.listAllCourses());

    LocalDateTime localDateTime = LocalDateTime.now();
    log.warn("this is the call to saveNewCourse at time: " + localDateTime);

    return "listCourses";
  }


  @GetMapping("/edit-form/{id}")
  public String editCourse(@PathVariable Long id, Model model) {

    Course c = this.courseService.findCourseByCourseId(id);

    model.addAttribute("courseId", id);
    model.addAttribute("courseName", c.getName());
    model.addAttribute("description", c.getDescription());
    model.addAttribute("teacherName", c.getTeacher().getName());
    model.addAttribute("teachers", this.teacherService.findAll());

    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("this is the call to editCourse at time: " + localDateTime);

    return "add-course";
  }

  @PostMapping("/add-teacher")
  @Transactional
  public String saveNewTeacher(@RequestParam String name, @RequestParam String surname) {

    teacherService.addTeacher(name, surname);

    return "listCourses";
  }

  @GetMapping("/add-teacher-form")
  public String newTeacher(Model model){

    return "add-teacher";
  }
}

