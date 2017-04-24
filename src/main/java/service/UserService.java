package service;

import dao.DAOUtilities;
import model.Reimbursement;
import model.Users;

import javax.naming.AuthenticationException;
import java.util.List;

/**
 * Created by uzh051 on 4/24/17.
 */
public class UserService {

    /**
     * Returns the Users object of the user with the given username and password. If user does not
     * exist that throw an authentication exception. Uses BCrypt to check password.
     * @param username
     * @return
     * @throws AuthenticationException
     */
    public Users authenticate(String username) throws AuthenticationException{
        DAOUtilities utilities = new DAOUtilities();
        Users user = utilities.getUserByName(username);
        if(user == null) throw new AuthenticationException();
        else
            return user;

    }

    /**
     * Returns the list of reimbursements of the employee with the given username.
     * @param username
     * @return
     */
    public List<Reimbursement> returnUserReimb(String username){
        DAOUtilities utilities = new DAOUtilities();
        List<Reimbursement> reimb = utilities.showAllReimbByUsername(username);
        return reimb;
    }

    /**
     * Returns the list of reimbursements with a given status. Used for managers.
     * @param status
     * @return
     */
    public List<Reimbursement> returnReimbByStatus(String status) {
        DAOUtilities utilities = new DAOUtilities();
        List<Reimbursement> reimb = utilities.showAllByStatus(status);
        System.out.println(reimb);
        return reimb;
    }

    /**
     * Returns the list of reimbursements after a reimbursement has been updated given the new status,
     * manager's username, and reimbursement ID of the updated reimbursement.
     * @param newStatus
     * @param resolverUsername
     * @param reimbId
     * @return
     */
    public List<Reimbursement> updateReimb(String newStatus, String resolverUsername, int reimbId) {
        DAOUtilities utilities = new DAOUtilities();
        utilities.updateReimbursement(newStatus, resolverUsername, reimbId);
        return returnReimbByStatus("Pending");
    }

    /**
     * Returns the list of reimbursements after a new reimbursement has been added given the amount,
     * description, manager's ID, and type of the new reimbursement.
     * @param amount
     * @param description
     * @param author
     * @param type
     * @return
     */
    public List<Reimbursement> addReimb(double amount, String description, int author, int type) {
        DAOUtilities utilities = new DAOUtilities();
        Reimbursement newReimb = utilities.addNewReimbursement(amount, description, author, type);
        return utilities.showAllReimbByUsername(newReimb.getAuthor().getUsername());
    }

    /**
     * Returns the list of completed (resolved) reimbursement that have been approved or denied
     * by the given username. Used for managers.
     * @param username
     * @return
     */
    public List<Reimbursement> showCompleted(String username) {
        DAOUtilities utilities = new DAOUtilities();
        return utilities.showCompleted(username);
    }
}
