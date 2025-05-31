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
public class BestUsedPart {

    private String partName;
    private int totalUsed;

    public BestUsedPart(String partName, int totalUsed) {
        this.partName = partName;
        this.totalUsed = totalUsed;
    }

    public String getPartName() {
        return partName;
    }

    public int getTotalUsed() {
        return totalUsed;
    }
}
