package model;

public class Mechanic {

    private String mechanicName;
    private String mechanicID;

    // Constructor, getters, setters
    public Mechanic(String mechanicName, String mechanicID) {
        this.mechanicName = mechanicName;
        this.mechanicID = mechanicID;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }

}
