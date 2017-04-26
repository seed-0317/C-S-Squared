package servlets;

import dao.UsersDAO;
import dao.UsersDAOImpl;
import model.User;

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

//        UsersDAO dao = DAOUtilities.getUsersDAO();
//        List<User>  users = dao.getAllUser();
//
//        // Populate the list into a variable that will be stored in the session
//        request.getSession().setAttribute("users", users);


        request.getRequestDispatcher("/views/login.html").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
 //       System.out.println("Hi " + username);

        UsersDAO dao = new UsersDAOImpl();
        User temp = dao.getUser(username);

        /*user = new User();
          user.setId(resultSet.getInt("u_id"));
          user.setUserName(resultSet.getString("u_username"));
          user.setFirstName(resultSet.getString("u_firstname"));
          user.setLastName(resultSet.getString("u_lastname"));
          user.seteMail(resultSet.getString("u_email"));
          setUrRole*/




        if (temp != null) {

//            request.setAttribute("dog", username);
//            HttpSession session = request.getSession();
//            session.setAttribute("dog", username);

            HttpSession session = request.getSession();
            request.setAttribute("user", username);
            session.setAttribute("user", username);

            request.setAttribute("firstname", temp.getFirstName());
            session.setAttribute("firstname", temp.getFirstName());

            request.setAttribute("lastname", temp.getLastName());
            session.setAttribute("lastname", temp.getLastName());

            request.setAttribute("email",temp.geteMail());
            session.setAttribute("email",temp.geteMail());

            request.setAttribute("role",temp.getRole());
            session.setAttribute("role",temp.getRole());
            String role = (String)session.getAttribute("role");
            if (role.equals("Employee")){
                response.sendRedirect("emplHome");
            }
            else {
                response.sendRedirect("home");
            }
        } else {

                // user does not exist in database
                response.sendRedirect("login");

        }

    }
}

