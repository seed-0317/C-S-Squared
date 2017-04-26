package servlets;

/**
 * Created by tky247 on 4/23/17.
 */

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static dao.UsersDAOImpl.updateUser;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // querry the db with user
        // user dog = new user
        request.getRequestDispatcher("/views/userupdate.html").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        HttpSession session = request.getSession();
        session.getAttribute("user");
        session.getAttribute("firstname");
        session.getAttribute("lastname");
        session.getAttribute("email");
        session.getAttribute("role");
        String userName = (String) (String) session.getAttribute("user");

        // read form fields
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.seteMail(email);


        try {
            updateUser(userName, user.getFirstName(), user.getLastName(), user.geteMail());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/home");


    }
}