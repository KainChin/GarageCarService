/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class TopMechanic {

    private String mechanicID;
    private String mechanicName;
    private int repairCount;

    public TopMechanic(String mechanicID, String mechanicName, int repairCount) {
        this.mechanicID = mechanicID;
        this.mechanicName = mechanicName;
        this.repairCount = repairCount;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public int getRepairCount() {
        return repairCount;
    }
}
