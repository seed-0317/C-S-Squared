package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by uzh051 on 4/19/17.
 */
@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
//        request.setAttribute("name", request.getParameter("username"));
        request.getRequestDispatcher("/WEB-INF/views/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("name", request.getParameter("name"));
        request.getRequestDispatcher("/WEB-INF/views/employees.html").forward(request, response);
    }

}