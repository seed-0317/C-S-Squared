package servlets;

import dao.UsersDAO;
import dao.UsersDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by nof191 on 4/22/17.
 */
@WebServlet(value = "/ListUsers")
public class ListUsersServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            UsersDAO dao= new UsersDAOImpl();
            List<User> userData= dao.getAllUser();
            req.setAttribute("users", userData);
            req.getRequestDispatcher("/views/employees.html").forward(req, resp);
        }

  //  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  //  }


}
