/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import model.Service;
import model.ServiceMechanic;
import mylib.DBUntils;

/**
 *
 * @author Dell
 */
public class ServiceMechanicDAO {

    public List<ServiceMechanic> getList(String mechanicID) {
        Connection conn = null;
        List<ServiceMechanic> list = new ArrayList<>();
        String sql = "SELECT [serviceTicketID]\n"
                + "      ,[serviceID]\n"
                + "      ,[mechanicID]\n"
                + "      ,[hours]\n"
                + "      ,[comment]\n"
                + "      ,[rate]\n"
                + "  FROM [Car_Dealership].[dbo].[ServiceMehanic]"
                + "  WHERE mechanicID = ?";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, mechanicID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    list.add(new ServiceMechanic(
                            rs.getInt("serviceTicketID"),
                            rs.getInt("serviceID"),
                            rs.getString("mechanicID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getDouble("rate")));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void updateServiceMechanic(ServiceMechanic sm) {
        Connection conn = null;
        String sql = "UPDATE ServiceMehanic\n"
                + "     SET [hours] = ?, [comment] = ?, [rate] = ?\n"
                + "     WHERE [serviceTicketID] = ? AND [serviceID] = ?";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, sm.getHours());
                st.setString(2, sm.getComment());
                st.setDouble(3, sm.getRate());
                st.setInt(4, sm.getServiceTicketID());
                st.setInt(5, sm.getServiceID());
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ServiceMechanic getServiceMechanicByID(int serviceTicketID, int serviceID) {
        Connection conn = null;
        String sql = "SELECT [serviceTicketID]\n"
                + "                     ,[serviceID]\n"
                + "                     ,[mechanicID]\n"
                + "                     ,[hours]\n"
                + "                     ,[comment]\n"
                + "                     ,[rate]\n"
                + "                 FROM [Car_Dealership].[dbo].[ServiceMehanic]\n"
                + "		    WHERE [serviceTicketID] = ? AND [serviceID] = ?";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceTicketID);
                st.setInt(2, serviceID);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    return new ServiceMechanic(
                            rs.getInt("serviceTicketID"),
                            rs.getInt("serviceID"),
                            rs.getString("mechanicID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getDouble("rate")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
