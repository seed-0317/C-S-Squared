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
    private int type;
    private int status;


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        if (getType() != that.getType()) return false;
        if (getStatus() != that.getStatus()) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getReceipt() != null ? !getReceipt().equals(that.getReceipt()) : that.getReceipt() != null) return false;
        if (!getSubmitted().equals(that.getSubmitted())) return false;
        return getResolved() != null ? getResolved().equals(that.getResolved()) : that.getResolved() == null;
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
        result = 31 * result + getType();
        result = 31 * result + getStatus();
        return result;
    }
}
