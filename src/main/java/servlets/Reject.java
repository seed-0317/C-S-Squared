package servlets;

import model.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static dao.AddReimbursementsDAO.StatusReimburse;
import static dao.AddReimbursementsDAO.addTime;


/**
 * Created by tky247 on 4/26/17.
 */
@WebServlet("/reject")
public class Reject extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("user");
        //String id = (String) session.getAttribute("dog.id");

        String r_id = request.getParameter("r_id");
        String rs_id = request.getParameter("rs_id");

        System.out.println(r_id);
        System.out.println(rs_id);
        System.out.println();

        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setId(Integer.parseInt(r_id));
        reimbursement.setStatusID(Integer.parseInt(rs_id));
        reimbursement.setResolver(Integer.parseInt(id));
        reimbursement.setResolved(addTime());




        try {
            StatusReimburse(reimbursement.getStatusID(), reimbursement.getResolver(), reimbursement.getResolved(),
                    reimbursement.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/home");

    }
}
