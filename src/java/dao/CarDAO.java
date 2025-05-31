/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Car;
import mylib.DBUntils;

public class CarDAO {

    public boolean addCar(long carID, String serialNumber, String model, String colour, int year) {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                // Kiểm tra xem CarID đã tồn tại chưa
                String checkSql = "SELECT COUNT(*) FROM Cars WHERE carID = ?";
                st = cn.prepareStatement(checkSql);
                st.setLong(1, carID);
                ResultSet rs = st.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // Trả về false nếu CarID đã tồn tại
                }
                rs.close();
                st.close();

                // Nếu không tồn tại, thêm xe mới
                String sql = "INSERT INTO Cars (carID, serialNumber, model, colour, year) VALUES (?, ?, ?, ?, ?)";
                st = cn.prepareStatement(sql);
                st.setLong(1, carID);
                st.setString(2, serialNumber);
                st.setString(3, model);
                st.setString(4, colour);
                st.setInt(5, year);
                st.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void updateCar(long carID, String serialNumber, String model, String colour, int year) {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Cars SET serialNumber = ?, model = ?, colour = ?, year = ? WHERE carID = ?";
                st = cn.prepareStatement(sql);
                st.setString(1, serialNumber);
                st.setString(2, model);
                st.setString(3, colour);
                st.setInt(4, year);
                st.setLong(5, carID);
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
}
        }
    }

    public Car getCarById(long carID) {
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Car car = null;

        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Cars WHERE carID = ?";
                st = cn.prepareStatement(sql);
                st.setLong(1, carID);
                rs = st.executeQuery();

                if (rs.next()) {
                    car = new Car(
                            rs.getLong("carID"),
                            rs.getString("serialNumber"),
                            rs.getString("model"),
                            rs.getString("colour"),
                            rs.getInt("year")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return car;
    }

    public void deleteCar(String carID) {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Cars SET Status = 'Deactive' WHERE carID = ?";
                st = cn.prepareStatement(sql);
                st.setString(1, carID);
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}