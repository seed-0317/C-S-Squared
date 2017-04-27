package servlets;

import dao.ReimbursmentsDAO;
import dao.ReimbursmentsDAOImpl;
import model.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by nof191 on 4/26/17.
 */
@WebServlet(urlPatterns = "/reviewReimbursement")
public class ReviewReimbursementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReimbursmentsDAO dao = new ReimbursmentsDAOImpl();
        List<Reimbursement> reimbursements = null;
        reimbursements = dao.getAllUnapprovedReimbursments();
        System.out.println("we are in all un approved servlet" + reimbursements);
        request.setAttribute("reimbersment", reimbursements);
        request.getRequestDispatcher("/views/reviewreimbursments.html").forward(request, response);

    }
}
