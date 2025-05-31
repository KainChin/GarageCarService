/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import model.ServiceTicket;
import mylib.DBUntils;

/**
 *
 * @author this pc
 */
public class ServiceTicketDAO extends DBUntils {

    public ArrayList<ServiceTicket> getServiceTicket(String custid) { //danh sách vé cho cus
        ArrayList<ServiceTicket> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "select serviceTicketID, dateReceived,dateReturned,carID \n"
                        + "from [dbo].[ServiceTicket]\n"
                        + "where custID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, custid);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String id = table.getString("serviceTicketID");
                        Date dateReceived = table.getDate("dateReceived");
                        Date dateReturned = table.getDate("dateReturned");
                        String carid = table.getString("carID");
                        ServiceTicket in = new ServiceTicket(id, dateReceived, dateReturned, custid, carid);
                        result.add(in);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return result;
    }

//    public ArrayList<ServiceTicket> getAllServiceTicket() {
//        ArrayList<ServiceTicket> result = new ArrayList<>();
//        Connection cn = null;
//        try {
//            cn = DBUntils.getConnection();
//            if (cn != null) {
//                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, custID, carID FROM [dbo].[ServiceTicket]";
//                PreparedStatement st = cn.prepareStatement(sql);
//                ResultSet table = st.executeQuery();
//                while (table.next()) {
//                    String id = table.getString("serviceTicketID");
//                    Date dateReceived = table.getDate("dateReceived");
//                    Date dateReturned = table.getDate("dateReturned");
//                    String custid = table.getString("custID");
//                    String carid = table.getString("carID");
//                    ServiceTicket ticket = new ServiceTicket(id, dateReceived, dateReturned, custid, carid);
//                    result.add(ticket);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

//    public boolean updateServiceTicket(int serviceTicketID, int hours, String comment, BigDecimal rate) {
//        String query = "UPDATE ServiceMehanic SET hours = ?, comment = ?, rate = ? WHERE serviceTicketID = ?";
//        try (Connection cn = DBUntils.getConnection();
//                PreparedStatement ps = cn.prepareStatement(query)) {
//            ps.setInt(1, hours);
//            ps.setString(2, comment);
//            ps.setBigDecimal(3, rate);
//            ps.setInt(4, serviceTicketID);
//
//            int rowsUpdated = ps.executeUpdate();
//            return rowsUpdated > 0;
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public List<ServiceTicket> searchTickets(String custID, String carID, String dateReceived) {
        List<ServiceTicket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ServiceTicket WHERE 1=1";

        if (custID != null && !custID.isEmpty()) {
            sql += " AND CustID LIKE ?";
        }
        if (carID != null && !carID.isEmpty()) {
            sql += " AND CarID LIKE ?";
        }
        if (dateReceived != null && !dateReceived.isEmpty()) {
            sql += " AND DateReceived = ?";
        }
        try (Connection conn = DBUntils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            if (custID != null && !custID.isEmpty()) {
                stmt.setString(paramIndex++, "%" + custID + "%");
            }
            if (carID != null && !carID.isEmpty()) {
                stmt.setString(paramIndex++, "%" + carID + "%");
            }
            if (dateReceived != null && !dateReceived.isEmpty()) {
                stmt.setDate(paramIndex++, Date.valueOf(dateReceived));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tickets.add(new ServiceTicket(
                        rs.getString("ServiceTicketID"),
                        rs.getDate("DateReceived"),
                        rs.getDate("DateReturned"),
                        rs.getString("CustID"),
                        rs.getString("CarID")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }
    
    

}
