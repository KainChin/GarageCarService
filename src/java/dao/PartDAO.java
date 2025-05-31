package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Parts;
import mylib.DBUntils;

public class PartDAO {

    public boolean addPart(String partID, String partName, String purchasePrice, String retailPrice) {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                // Kiểm tra xem partID đã tồn tại chưa
                String checkSql = "SELECT COUNT(*) FROM Parts WHERE partID = ?";
                st = cn.prepareStatement(checkSql);
                st.setString(1, partID);
                ResultSet rs = st.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // Trả về false nếu partID đã tồn tại
                }
                rs.close();
                st.close();

                // Nếu không tồn tại, thêm part mới
                String sql = "INSERT INTO Parts (partID, partName, purchasePrice, retailPrice) VALUES (?, ?, ?, ?)";
                st = cn.prepareStatement(sql);
                st.setString(1, partID);
                st.setString(2, partName);
                st.setString(3, purchasePrice);
                st.setString(4, retailPrice);
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

    public void updatePart(String partID, String partName, String purchasePrice, String retailPrice) {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Parts SET partName = ?, purchasePrice = ?, retailPrice = ? WHERE partID = ?";
                st = cn.prepareStatement(sql);
                st.setString(1, partName);
                st.setString(2, purchasePrice);
                st.setString(3, retailPrice);
                st.setString(4, partID);
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

    public void deletePart(String partID) {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Parts SET Status = 'Deactive' WHERE partID = ?";
                st = cn.prepareStatement(sql);
                st.setString(1, partID);
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

    public Parts getPartById(String partID) {
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Parts part = null;

        try {
            cn = DBUntils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Parts WHERE partID = ?";
                st = cn.prepareStatement(sql);
                st.setString(1, partID);
                rs = st.executeQuery();

                if (rs.next()) {
                    part = new Parts(
                            rs.getString("partID"),
                            rs.getString("partName"),
                            rs.getString("purchasePrice"),
                            rs.getString("retailPrice")
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
        return part;
    }
}
