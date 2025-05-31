package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.math.BigDecimal;
import model.InvoiceDetail;
import model.InvoiceDetail2;
import mylib.DBUntils;

public class InvoiceDetailDAO {

    // Lấy danh sách dịch vụ từ invoiceID
    public ArrayList<InvoiceDetail> getServiceDetailsByInvoiceID(int invoiceID) {
        ArrayList<InvoiceDetail> serviceList = new ArrayList<>();
        String sql = "SELECT s.serviceName, sm.hours, s.hourlyRate, "
                + "(s.hourlyRate * sm.hours) AS serviceCost "
                + "FROM ServiceMehanic sm "
                + "JOIN Service s ON sm.serviceID = s.serviceID "
                + "JOIN ServiceTicket st ON sm.serviceTicketID = st.serviceTicketID "
                + "JOIN SalesInvoice si ON st.custID = si.custID AND st.carID = si.carID "
                + "WHERE si.invoiceID = ? "
                + "ORDER BY s.serviceName";

        try (Connection cn = DBUntils.getConnection();
                PreparedStatement st = cn.prepareStatement(sql)) {

            st.setInt(1, invoiceID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    InvoiceDetail service = new InvoiceDetail(
                            rs.getString("serviceName"),
                            rs.getInt("hours"),
                            rs.getBigDecimal("hourlyRate"),
                            rs.getBigDecimal("serviceCost")
                    );
                    serviceList.add(service);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceList;
    }

    // Lấy danh sách phụ tùng từ invoiceID
    public ArrayList<InvoiceDetail2> getPartDetailsByInvoiceID(int invoiceID) {
        ArrayList<InvoiceDetail2> partList = new ArrayList<>();
        String sql = "SELECT p.partName, pu.numberUsed, pu.price, "
                + "(pu.numberUsed * pu.price) AS partTotalPrice "
                + "FROM PartsUsed pu "
                + "JOIN Parts p ON pu.partID = p.partID "
                + "JOIN ServiceTicket st ON pu.serviceTicketID = st.serviceTicketID "
                + "JOIN SalesInvoice si ON st.custID = si.custID AND st.carID = si.carID "
                + "WHERE si.invoiceID = ? "
                + "ORDER BY p.partName";

        try (Connection cn = DBUntils.getConnection();
                PreparedStatement st = cn.prepareStatement(sql)) {

            st.setInt(1, invoiceID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    InvoiceDetail2 part = new InvoiceDetail2(
                            rs.getString("partName"),
                            rs.getInt("numberUsed"),
                            rs.getBigDecimal("price"),
                            rs.getBigDecimal("partTotalPrice")
                    );
                    partList.add(part);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partList;
    }
}
