/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Invoice;
import mylib.DBUntils;

/**
 *
 * @author this pc
 */
public class InvoiceDAO {

    public ArrayList<Invoice> getInvoice(String custid) {
        ArrayList<Invoice> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "SELECT si.invoiceID, si.invoiceDate, "
                        + "c.serialNumber, c.model, c.colour, c.year, "
                        + "si.price as TotalPrice "
                        + "FROM SalesInvoice si "
                        + "JOIN Cars c ON si.carID = c.carID "
                        + "WHERE si.custID = ?";

                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, custid);
                ResultSet table = st.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        String id = table.getString("invoiceID");
                        String date = table.getString("invoiceDate");
                        String serialNumber = table.getString("serialNumber");
                        String model = table.getString("model");
                        String colour = table.getString("colour");
                        int year = table.getInt("year");
                        double totalPrice = table.getDouble("TotalPrice");

                        Invoice invoice = new Invoice(id, date, serialNumber, model, colour, year, totalPrice);
                        result.add(invoice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
