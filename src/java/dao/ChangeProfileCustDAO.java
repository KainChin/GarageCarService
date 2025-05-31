/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Customer;
import mylib.DBUntils;

/**
 *
 * @author this pc
 */
public class ChangeProfileCustDAO {

    public boolean updateCustomer(Customer customer) {
        Connection cn = null;
        boolean checkUpdate = false;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Customer SET custName = ?, phone = ?, sex = ?, cusAddress = ? WHERE custID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, customer.getCustName());
                st.setString(2, customer.getPhone());
                st.setString(3, customer.getSex());
                st.setString(4, customer.getCustAddress());
                st.setInt(5, customer.getCustID());

                int row = st.executeUpdate();
                if (row > 0) {
                    checkUpdate = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUpdate;
    }
}
