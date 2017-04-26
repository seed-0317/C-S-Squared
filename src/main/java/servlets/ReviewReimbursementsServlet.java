package servlets;

import dao.ReimbursmentsDAO;
import dao.ReimbursmentsDAOImpl;
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
 * Created by nof191 on 4/26/17.
 */
@WebServlet(urlPatterns = "/reviewReimbursement")
public class ReviewReimbursementsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReimbursmentsDAO dao = new ReimbursmentsDAOImpl();
        List<Reimbursement> reimbursements = null;
        reimbursements = dao.getAllReimbursments();
        request.setAttribute("reimbersment", reimbursements);
        request.getRequestDispatcher("/views/reviewreimbursments.html").forward(request, response);

    }
}
