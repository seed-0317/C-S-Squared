package servlets;

/**
 * Created by tky247 on 4/23/17.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/userupdate.html").forward(request, response);

        //HttpSession session = request.getSession();
        //System.out.println(session.getAttribute("user"));
        //System.out.println(session.getAttribute("firstname"));
        //System.out.println(session.getAttribute("lastname"));
        //System.out.println(session.getAttribute("email"));
        //System.out.println(session.getAttribute("role"));


        //session.getAttribute("user");
        //session.getAttribute("firstname");
        //session.getAttribute("lastname");
        //session.getAttribute("email");
        //session.getAttribute("role");
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.getAttribute("user");
        session.getAttribute("firstname");
        session.getAttribute("lastname");
        session.getAttribute("email");
        session.getAttribute("role");
    }

}