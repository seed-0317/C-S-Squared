package servlets;

import dao.TestReimbursementStatus;
import model.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by uzh051 on 4/24/17.
 */
@WebServlet(urlPatterns = "/ListRis")
public class ViewReimbursementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/login.html").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("name");
        username ="bjones";
        System.out.println("Hi " + username);
        TestReimbursementStatus status = new TestReimbursementStatus();
        List<Reimbursement> reimbursements = null;
        try {
            reimbursements = status.selectAllByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (reimbursements != null) {
            System.out.println("Inside Session");
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                response.sendRedirect("/views/allreimbursments.html");

            } else {
                response.sendRedirect("login");
            }

    }

}