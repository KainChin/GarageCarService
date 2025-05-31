/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;
import mylib.DBUntils;

/**
 *
 * @author this pc
 */
public class CustomerDAO {

    public Customer checkLogin(String custName, String phone) { //COLLATE SQL_Latin1_General_CP1_CS_AS
        Customer customer = null;
        String sql = "SELECT custID, custName, phone, sex, cusAddress FROM [dbo].[Customer] WHERE custName = ? AND phone = ?";
        try (Connection cn = DBUntils.getConnection()) {
            if (cn == null) {
                return null;
            }
            try (PreparedStatement st = cn.prepareStatement(sql)) {
                custName = custName.trim();
                phone = phone.trim();
                st.setString(1, custName);
                st.setString(2, phone);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        int custID = rs.getInt("custID");
                        String custNameDb = rs.getString("custName");
                        String phoneDb = rs.getString("phone");
                        String sex = rs.getString("sex");
                        String custAddress = rs.getString("cusAddress");
                        customer = new Customer(custID, custNameDb, phoneDb, sex, custAddress);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer getCustomerById(String custID) {
        String sql = "SELECT * FROM Customers WHERE custID = ?";
        try (Connection conn = DBUntils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, custID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("custID"),
                        rs.getString("custName"),
                        rs.getString("phone"),
                        rs.getString("sex"),
                        rs.getString("custAddress")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
