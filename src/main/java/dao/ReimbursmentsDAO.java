package dao;

import model.Reimbursement;
import java.util.List;

/**
 * Created by nof191 on 4/17/17.
 */
public interface ReimbursmentsDAO {

    // used to retrive a list of reimbursments

    List<Reimbursement> getAllReimbursments();

    // used to retrive a list of Unapproved reimbursments

    List<Reimbursement> getAllUnapprovedReimbursments();

    // user to create a reimbursment

    void createReimbursment(Reimbursement newReimbursment) throws Exception;

    // used to update a reimbursment

    void saveReimbursment(Reimbursement saveReimbursment) throws Exception;



}
