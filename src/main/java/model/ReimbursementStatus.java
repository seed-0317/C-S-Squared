package model;

/**
 * Created by uzh051 on 4/26/17.
 */
public class ReimbursementStatus {

    private int statusId;
    private String status;

    public ReimbursementStatus(){
        super();
    }

    public ReimbursementStatus(int statusId, String status) {
        super();
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "statusId=" + statusId +
                ", status='" + status + '\'' +
                '}';
    }

}
