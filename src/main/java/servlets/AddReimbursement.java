package servlets;

/**
 * Created by tky247 on 4/23/17.
 */

import model.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static dao.Test.addReimburse;
import static dao.Test.addTime;

@WebServlet("/createReimbursement")
public class AddReimbursement extends HttpServlet {

    Reimbursement reimbursement = new Reimbursement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/reimbursment.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read form fields
        String amount = request.getParameter("amount");
        String u_id = request.getParameter("id_author");
        String rs_id = request.getParameter("rs_id");
        String rt_id = request.getParameter("rt_id");
        String descr = request.getParameter("description");
        String id_resolver = request.getParameter("id_resolver");

        /*reimbursement.setId(Integer.parseInt(r_id));*/
        reimbursement.setAmount(Float.parseFloat(amount));
        reimbursement.setAuthor(Integer.parseInt(u_id));
        reimbursement.setStatusID(Integer.parseInt(rs_id));
        reimbursement.setTypeId(Integer.parseInt(rt_id));
        reimbursement.setDescription(descr);
        reimbursement.setResolver(Integer.parseInt(id_resolver));

        addReimburse(reimbursement.getAmount(), reimbursement.getAuthor(),
                reimbursement.getStatusID(), reimbursement.getTypeId(), reimbursement.getDescription(),
                addTime(), reimbursement.getResolver());

        response.sendRedirect("/login");

    }
}