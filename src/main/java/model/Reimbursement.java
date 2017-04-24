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
    private String submitted;
    private String resolved;
    private int author;
    private int resolver;
    private int typeId;
    private int statusID;


    public Reimbursement(int id, float amount, String description, String submitted, String resolved, int author, int resolver, int typeId, int statusID) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.typeId = typeId;
        this.statusID = statusID;
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

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
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

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", typeId=" + typeId +
                ", statusID=" + statusID +
                '}';
    }
}
