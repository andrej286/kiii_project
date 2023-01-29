package mk.ukim.finki.wp.lab.web.servlet;


import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="labs4-servlet",urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final StudentService studentService;
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;


    public StudentEnrollmentSummary(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long ID = (Long) req.getSession().getAttribute("courseId");
        context.setVariable("students", this.courseService.listStudentsByCourse(ID));
        context.setVariable("courseName", this.courseService.listAllCourses().stream()
                .filter(i->i.getCourseId().equals(ID)).findFirst().get().getName());
        this.springTemplateEngine.process("studentsInCourse.html", context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long ID = (Long) req.getSession().getAttribute("courseId");
        context.setVariable("students", this.courseService.listStudentsByCourse(ID));
        context.setVariable("courseName", this.courseService.listAllCourses().stream()
                .filter(i->i.getCourseId().equals(ID)).findFirst().get().getName());
       this.springTemplateEngine.process("studentsInCourse.html", context,resp.getWriter());
    }
}
