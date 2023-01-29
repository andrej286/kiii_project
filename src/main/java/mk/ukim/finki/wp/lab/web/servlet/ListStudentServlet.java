package mk.ukim.finki.wp.lab.web.servlet;


import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="labs2-servlet",urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {


    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     //   WebContext context = new WebContext(req,resp,req.getServletContext());
     //   Long ID = (long) req.getSession().getAttribute("courseId");
     //   context.setVariable("students", studentService.listAll());
       // context.setVariable("courseID", ID);
      //  this.springTemplateEngine.process("listStudents.html", context,resp.getWriter());
        Long ID = Long.parseLong(req.getParameter("courseId"));
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courseId", ID);
        context.setVariable("students", this.studentService.listAll());
        req.getSession().setAttribute("courseId", ID);
        this.springTemplateEngine.process("listStudents.html", context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Long ID = Long.parseLong(req.getParameter("courseId"));
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courseId", ID);
        context.setVariable("students", this.studentService.listAll());
        req.getSession().setAttribute("courseId", ID);
        this.springTemplateEngine.process("listStudents.html", context,resp.getWriter());
       // resp.sendRedirect("/addStudent");
    }
}
