package mk.ukim.finki.wp.lab.web.servlet;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="labs-servlet",urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {

    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;


    public CoursesListServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("courses", courseService.listAllCourses());
        this.springTemplateEngine.process("listCourses.html", context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Long ID = Long.parseLong(req.getParameter("courseId"));
//
//        try {
//            user = authService.login(username,password);
//        } catch (InvalidUserCredentialsException ex) {
//            WebContext context = new WebContext(req,resp,req.getServletContext());
//            context.setVariable("hasError",true);
//            context.setVariable("error",ex.getMessage());
//            springTemplateEngine.process("login.html",context,resp.getWriter());
//        }
//        req.getSession().setAttribute("user",user);
//        resp.sendRedirect("/servlet/thymeleaf/category");
//    }
//


        resp.sendRedirect("/addStudent");

    }


}
/*


import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter
public class IdFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
     //   Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

      //  User user = (User)request.getSession().getAttribute("user");
        Long ID = (Long) request.getSession().getAttribute("courseId");
        String path = request.getServletPath();

        if (!"/listCourses".equals(path) && ID==null) {
            response.sendRedirect("/listCourses");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
      //  Filter.super.destroy();
    }
}
 */