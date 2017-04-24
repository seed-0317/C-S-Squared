package model;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Created by nof191 on 4/17/17.
 */
public class Reimbursement {

    private int id;
    private float amount;
    private String description;
    private Blob receipt;
    private Timestamp submitted;
    private Timestamp resolved;
    private int author;
    private int resolver;
    private int typeId;
    private String type;
    private int statusID;
    private String status;

    public Reimbursement(int id, float amount, String description, Blob receipt,
                         Timestamp submitted, Timestamp resolved, int author, int resolver,
                         int typeId, String type, int statusID, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.typeId = typeId;
        this.type = type;
        this.statusID = statusID;
        this.status = status;
    }

    public Reimbursement() {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;

        Reimbursement that = (Reimbursement) o;

        if (getId() != that.getId()) return false;
        if (Float.compare(that.getAmount(), getAmount()) != 0) return false;
        if (getAuthor() != that.getAuthor()) return false;
        if (getResolver() != that.getResolver()) return false;
        if (getTypeId() != that.getTypeId()) return false;
        if (getStatusID() != that.getStatusID()) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getReceipt() != null ? !getReceipt().equals(that.getReceipt()) : that.getReceipt() != null) return false;
        if (!getSubmitted().equals(that.getSubmitted())) return false;
        if (getResolved() != null ? !getResolved().equals(that.getResolved()) : that.getResolved() != null)
            return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getAmount() != +0.0f ? Float.floatToIntBits(getAmount()) : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getReceipt() != null ? getReceipt().hashCode() : 0);
        result = 31 * result + getSubmitted().hashCode();
        result = 31 * result + (getResolved() != null ? getResolved().hashCode() : 0);
        result = 31 * result + getAuthor();
        result = 31 * result + getResolver();
        result = 31 * result + getTypeId();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + getStatusID();
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
