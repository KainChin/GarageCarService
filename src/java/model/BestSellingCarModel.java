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
public class BestSellingCarModel {

    private String model;
    private int totalSold;

    public BestSellingCarModel(String model, int totalSold) {
        this.model = model;
        this.totalSold = totalSold;
    }

    public String getModel() {
        return model;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
