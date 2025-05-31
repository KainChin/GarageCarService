package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Car;
import model.Customer;
import model.Parts;
import model.Saler;
import mylib.DBUntils;

public class SalerDAO {

    public Saler checkLogin(String salesName) {
        Saler saler = null;
        String sql = "SELECT salesName, salesID, birthday, sex, salesAddress "
                + "FROM [dbo].[SalesPerson] WHERE salesName = ?";

        try (Connection cn = DBUntils.getConnection()) {
            if (cn == null) {
                return null;
            }

            try (PreparedStatement st = cn.prepareStatement(sql)) {
                salesName = salesName.trim();

                st.setString(1, salesName);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String salesNamedb = rs.getString("salesName");
                        String salesID = rs.getString("salesID");
                        Date birthday = rs.getDate("birthday");
                        String sex = rs.getString("sex");
                        String salesAddress = rs.getString("salesAddress");

                        saler = new Saler(salesName, salesID, birthday, sex, salesAddress);
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

        return saler;
    }

    public ArrayList<Customer> getCustName(String salesid, String custName) {
        ArrayList<Customer> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "SELECT DISTINCT a.[custID], a.[custName], a.[phone], a.[sex], a.[cusAddress]\n"
                        + "FROM Customer a  \n"
                        + "JOIN SalesInvoice b ON a.[custID] = b.[custID]\n"
                        + "WHERE [salesID] = ? AND a.[custName] like ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, salesid);
                st.setString(2, "%" + custName + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("custID");
                        String name = table.getString("custName");
                        String phone = table.getString("phone");
                        String sex = table.getString("sex");
                        String address = table.getString("cusAddress");
                        Customer in = new Customer(id, name, phone, sex, address);
                        rs.add(in);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return rs;
    }

    public ArrayList<Car> getCar(String serialNumber, String model, int year) {
        ArrayList<Car> rs = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                // Thêm điều kiện Status = 'Active'
                String sql = "SELECT DISTINCT carID, serialNumber, model, colour, year FROM Cars WHERE Status = 'Active'";

                if (serialNumber != null && !serialNumber.isEmpty()) {
                    sql += " AND serialNumber LIKE ?";
                }
                if (model != null && !model.isEmpty()) {
                    sql += " AND model LIKE ?";
                }
                if (year > 0) {
                    sql += " AND year = ?";
                }

                PreparedStatement pst = cn.prepareStatement(sql);
                int paramIndex = 1;

                if (serialNumber != null && !serialNumber.isEmpty()) {
                    pst.setString(paramIndex++, "%" + serialNumber + "%");
                }
                if (model != null && !model.isEmpty()) {
                    pst.setString(paramIndex++, "%" + model + "%");
                }
                if (year > 0) {
                    pst.setInt(paramIndex++, year);
                }

                ResultSet table = pst.executeQuery();

                while (table.next()) {
                    rs.add(new Car(
                            table.getLong("carID"),
                            table.getString("serialNumber"),
                            table.getString("model"),
                            table.getString("colour"),
                            table.getInt("year")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    public ArrayList<Parts> getPartByName(String partName) {
        ArrayList<Parts> resultList = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "SELECT partID, partName, purchasePrice, retailPrice FROM Parts WHERE partName LIKE ? AND Status = 'Active'";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + partName + "%");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    resultList.add(new Parts(
                            rs.getString("partID"),
                            rs.getString("partName"),
                            rs.getString("purchasePrice"),
                            rs.getString("retailPrice")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return resultList;
    }

}