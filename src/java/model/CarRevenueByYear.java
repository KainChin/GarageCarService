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
public class CarRevenueByYear {

    private int year;
    private double revenue;

    public CarRevenueByYear(int year, double revenue) {
        this.year = year;
        this.revenue = revenue;
    }

    public int getYear() {
        return year;
    }

    public double getRevenue() {
        return revenue;
    }
}
