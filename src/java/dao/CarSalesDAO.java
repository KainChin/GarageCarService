package dao;

import mylib.DBUntils;
import java.sql.*;
import java.util.*;
import model.*;

public class CarSalesDAO {

    public static List<CarSalesByYear> getCarsSoldByYear() {
        List<CarSalesByYear> carSales = new ArrayList<>();
        String query = "SELECT YEAR(invoiceDate) AS year, COUNT(carID) AS total "
                + "FROM SalesInvoice GROUP BY YEAR(invoiceDate) ORDER BY year";

        try (Connection conn = DBUntils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                carSales.add(new CarSalesByYear(rs.getInt("year"), rs.getInt("total")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carSales;
    }

    public static List<CarRevenueByYear> getCarSalesRevenueByYear() {
        List<CarRevenueByYear> revenueByYear = new ArrayList<>();
        String query = "SELECT YEAR(invoiceDate) AS year, SUM(price) AS revenue "
                + "FROM SalesInvoice GROUP BY YEAR(invoiceDate) ORDER BY year";

        try (Connection conn = DBUntils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                revenueByYear.add(new CarRevenueByYear(rs.getInt("year"), rs.getDouble("revenue")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return revenueByYear;
    }

    public static List<BestSellingCarModel> getBestSellingCarModels() {
        List<BestSellingCarModel> bestSellingCars = new ArrayList<>();
        String query = "SELECT c.model, COUNT(si.invoiceID) AS total_sold "
                + "FROM SalesInvoice si "
                + "JOIN Cars c ON si.carID = c.carID "
                + "GROUP BY c.model "
                + "ORDER BY total_sold DESC";

        try (Connection conn = DBUntils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                bestSellingCars.add(new BestSellingCarModel(rs.getString("model"), rs.getInt("total_sold")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bestSellingCars;
    }

    public static List<BestUsedPart> getBestUsedParts() {
        List<BestUsedPart> bestUsedParts = new ArrayList<>();
        String query = "SELECT p.partName, SUM(pu.numberUsed) AS total_used "
                + "FROM PartsUsed pu "
                + "JOIN Parts p ON pu.partID = p.partID "
                + "GROUP BY p.partName "
                + "ORDER BY total_used DESC";

        try (Connection conn = DBUntils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                bestUsedParts.add(new BestUsedPart(
                        rs.getString("partName"),
                        rs.getInt("total_used")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bestUsedParts;
    }

    public static List<TopMechanic> getTop3Mechanics() { //with ties
        List<TopMechanic> topMechanics = new ArrayList<>();
        String query = "SELECT TOP 3 m.mechanicID, m.mechanicName, COUNT(sm.serviceTicketID) AS repairCount "
                + "FROM ServiceMehanic sm "
                + "JOIN Mechanic m ON sm.mechanicID = m.mechanicID "
                + "GROUP BY m.mechanicID, m.mechanicName "
                + "ORDER BY repairCount DESC"; //mechanicID  asc

        try (Connection conn = DBUntils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                topMechanics.add(new TopMechanic(
                        rs.getString("mechanicID"),
                        rs.getString("mechanicName"),
                        rs.getInt("repairCount")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return topMechanics;
    }
    //ORDER BY repairCount DESC, mechanicID ASC
}
