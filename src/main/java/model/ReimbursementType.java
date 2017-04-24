package model;

/**
 * Created by uzh051 on 4/23/17.
 */
public class ReimbursementType {
    private int typeId;
    private String type;

    public ReimbursementType(){
        super();
    }

    public ReimbursementType(int typeId, String type) {
        this.typeId = typeId;
        this.type = type;
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

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }
}
