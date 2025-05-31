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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import model.Service;
import mylib.DBUntils;

/**
 *
 * @author Dell
 */
public class ServiceDAO {

    public List<Service> getList() {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT serviceID, serviceName, hourlyRate FROM Car_Dealership.dbo.Service WHERE Status = 'Active'";

        try (Connection conn = DBUntils.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(new Service(
                        rs.getInt("serviceID"),
                        rs.getString("serviceName"),
                        rs.getString("hourlyRate")
                ));
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
        return list;
    }

    public void deleteService(int serviceID) {
        Connection conn = null;
        String sql = "UPDATE Service\n"
                + "SET Status = 'Deactive'\n"
                + "WHERE serviceID = ?";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceID);
                st.executeUpdate();
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
    }

    public void updateService(Service s) {
        Connection conn = null;
        String sql = "UPDATE Service SET serviceName = ?, hourlyRate = ? WHERE serviceID = ?";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, s.getServiceName());
                st.setString(2, s.getHourlyRate()); // Đổi từ setString -> setDouble
                st.setInt(3, s.getServiceID());
                st.executeUpdate();
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
    }

    public Service getServiceByID(int serviceID) {
        Connection conn = null;
        String sql = "SELECT serviceID, serviceName, hourlyRate FROM Service WHERE serviceID = ?";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, serviceID);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return new Service(rs.getInt("serviceID"),
                            rs.getString("serviceName"),
                            rs.getString("hourlyRate")); 
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
        return null;
    }

    public void addService(Service s) {
        Connection conn = null;
        String sql = "INSERT INTO Service (serviceID, serviceName, hourlyRate) VALUES (?, ?, ?)";
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, s.getServiceID());
                st.setString(2, s.getServiceName());
                st.setString(3, s.getHourlyRate());
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

}
