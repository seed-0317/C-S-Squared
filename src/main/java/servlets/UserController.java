package servlets;


import model.Users;
import model.Reimbursement;

import service.BusinessDelegate;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by uzh051 on 4/19/17.
 */
@WebServlet(urlPatterns = "/createUser")
public class UserController{
    /**
     * This is performed when the user logs in with their credentials.
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String username = request.getParameter("username");


        try {
			/*returns the information of a user with the credentials above
			* used to see if they have the correct credentials to log in and to gather information
			* from the user for later use*/
            Users session = new BusinessDelegate().login(username);
            request.getSession().setAttribute("user", session);

			/*if the user is an employee, uses the username they entered in order to
			 * returns the list of reimbursements of the employee to the employee*/
            List<Reimbursement> session2 = new BusinessDelegate().reimbursements(username);
            request.getSession().setAttribute("usersData", session2);

			/*if the user is a manager, returns the list of pending reimbursements
			 * from all the employees to the manager*/
            List<Reimbursement> session3 = new BusinessDelegate().reimbursementsByStatus("Pending");
            request.getSession().setAttribute("pending", session3);

            List<Reimbursement> session4 = new BusinessDelegate().completedReimbursements(username);
            request.getSession().setAttribute("completed", session4);

			/* The user is forwarded to index.jsp*/
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (AuthenticationException e) { //if invalid credentials are entered
            request.setAttribute("authFailed", "Try to login again");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    /**
     * Updates a reimbursement by a manager.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resolver = request.getParameter("resolver");
        String newStatus = request.getParameter("newStatus");
        String rowId = request.getParameter("rowId");

        List<Reimbursement> reimbAfterUpdate = new BusinessDelegate().updateReimbursement(rowId, resolver, newStatus);
        request.getSession().setAttribute("pending", reimbAfterUpdate);

        List<Reimbursement> session4 = new BusinessDelegate().completedReimbursements(resolver);
        request.getSession().setAttribute("completed", session4);

        request.setAttribute("updated", newStatus);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Adds a reimbursement for a employee.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amount = request.getParameter("amount");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String type = request.getParameter("type");

        List<Reimbursement> reimbAfterAdd = new BusinessDelegate().addReimbursement(amount, description, author, type);
        request.getSession().setAttribute("usersData", reimbAfterAdd);
        request.setAttribute("added", "Successful");

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}