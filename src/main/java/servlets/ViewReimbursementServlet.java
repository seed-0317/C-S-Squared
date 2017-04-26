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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");
        TestReimbursementStatus status = new TestReimbursementStatus();
        List<Reimbursement> reimbursements = null;
        try {
            reimbursements = status.selectAllByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (reimbursements != null) {
                session.setAttribute("reimbersment", reimbursements);
                request.getRequestDispatcher("/views/allreimbursments.html").forward(request,response);

            } else {
                response.sendRedirect("login");
            }

    }

}