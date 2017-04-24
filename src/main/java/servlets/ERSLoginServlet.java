package servlets;

import dao.*;
import model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by uzh051 on 4/19/17.
 */
@WebServlet(urlPatterns = "/login")
public class ERSLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Grab a list of Users from the Database


//        List<User>  users = dao.getAllUser();
//
//        // Populate the list into a variable that will be stored in the session
//        request.getSession().setAttribute("users", users);


        request.getRequestDispatcher("/WEB-INF/views/login.html").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        System.out.println("Hi " + username);


        DAOUtilities utilities = new DAOUtilities();
        Users user = utilities.getUserByName(username);


        if (user != null) {

//            request.setAttribute("dog", username);
//            HttpSession session = request.getSession();
//            session.setAttribute("dog", username);

            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("home");
        } else {

                // user does not exist in database
                response.sendRedirect("login");

        }

    }
}

