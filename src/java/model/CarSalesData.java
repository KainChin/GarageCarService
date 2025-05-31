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
public class CarSalesData {

    private int year;
    private String model;
    private int totalSold;

    public CarSalesData(int year, String model, int totalSold) {
        this.year = year;
        this.model = model;
        this.totalSold = totalSold;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
