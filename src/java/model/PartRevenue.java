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
public class PartRevenue {

    private String partName;
    private double totalRevenue;

    public PartRevenue(String partName, double totalRevenue) {
        this.partName = partName;
        this.totalRevenue = totalRevenue;
    }

    public String getPartName() {
        return partName;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
