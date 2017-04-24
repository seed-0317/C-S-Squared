package servlets;

/**
 * Created by tky247 on 4/23/17.
 */

import dao.Test;
import model.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class AddReimbursement extends HttpServlet {

    Reimbursement reimbursement = new Reimbursement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/reimbursment.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read form fields
        int r_id = 3;
        String amount = request.getParameter("amount");
        String u_id = request.getParameter("id_author");
        int rs_id = 1;
        String rt_id = request.getParameter("rt_id");

        reimbursement.setId(r_id);
        reimbursement.setAmount(Float.parseFloat(amount));
        reimbursement.setAuthor(Integer.parseInt(u_id));
        reimbursement.setStatusID(rs_id);
        reimbursement.setTypeId(Integer.parseInt(rt_id));

        System.out.println("r_id: " + reimbursement.getId() );
        System.out.println("amount: " + reimbursement.getAmount());
        System.out.println("u_id: " + reimbursement.getAuthor());
        System.out.println("rs_id: " + reimbursement.getStatusID());
        System.out.println("rt_id: " + reimbursement.getTypeId());

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Test.addReimburse(reimbursement.getId(), reimbursement.getAmount(), reimbursement.getAuthor(),
                          reimbursement.getStatusID(), reimbursement.getTypeId());

        /* Connection connection = ConnectionFactory.createConnection();
        try (PreparedStatement stmt1 = connection.prepareStatement("Insert INTO csssquared.ers_reimbursements " +
             "(r_id,r_amount,u_id_author,rs_id, rt_id) VALUES (?, ?, ?, ?, ?)")) {

         stmt1.setInt(1, reimbursement.getId());
         stmt1.setFloat(2, reimbursement.getAmount());
         stmt1.setInt(3, reimbursement.getAuthor());
         stmt1.setInt(4, reimbursement.getStatusID());
         stmt1.setInt(5, reimbursement.getTypeId());

         ResultSet rs = stmt1.executeQuery();
        } catch (SQLException e1) {
         e1.printStackTrace();
        } */
    }

}