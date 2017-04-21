package servlets;

import dao.DAOUtilities;
import dao.UsersDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by uzh051 on 4/19/17.
 */
@WebServlet(urlPatterns = "/createUser")
public class AddUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("name", request.getParameter("username"));
        request.getRequestDispatcher("/WEB-INF/views/employees.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //Get Parameters
//       int id = Integer.parseInt(request.getParameter("id"));
//
//        String name = request.getParameter("userName");
//        String firstname = request.getParameter("firstName");
//        String lastname = request.getParameter("lastName");
//        String mail = request.getParameter("eMail");
//        String role = request.getParameter("role");
//
//
//
//
//        User userToSave = new User(
//
//                id,
//                name,
//                firstname,
//                lastname,
//                mail,
//                role
//
//        );

        //Call DAO method
//        UsersDAO dao = DAOUtilities.getUsersDAO();
//        try {
//            dao.saveUser(userToSave);
//            request.getSession().setAttribute("message", "User Succesfully Crteated");
//            request.getSession().setAttribute("messageClass","alert-success");
//            response.sendRedirect("addUser");
//
//        }
//        catch (SQLIntegrityConstraintViolationException e){
//            e.printStackTrace();
//
//            //change the message
//            request.getSession().setAttribute("message","Id of " + userToSave.getId() + "is already in use");
//            request.getSession().setAttribute("messageClass", "alert-danger");
//            request.getRequestDispatcher("/WEB-INF/views/employees.html").forward(request,response);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

    }

}