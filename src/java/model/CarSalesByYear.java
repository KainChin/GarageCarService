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
public class CarSalesByYear {

    private int year;
    private int totalSold;

    public CarSalesByYear(int year, int totalSold) {
        this.year = year;
        this.totalSold = totalSold;
    }

    public int getYear() {
        return year;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
