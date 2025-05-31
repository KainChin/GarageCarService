package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Mechanic;
import model.Service;
import mylib.DBUntils;

/**
 *
 * @author this pc
 */
public class MechanicDAO {

    public Mechanic checkLogin(String mechaName) {
        Mechanic mechanic = null;
        String sql = "select mechanicID, mechanicName from [dbo].[Mechanic] WHERE mechanicName = ?";

        try (Connection cn = DBUntils.getConnection()) {
            if (cn == null) {
                return null;
            }

            try (PreparedStatement st = cn.prepareStatement(sql)) {
                mechaName = mechaName.trim();

                st.setString(1, mechaName);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        String mechanicName = rs.getString("mechanicName");
                        String mechanicID = rs.getString("mechanicID");

                        mechanic = new Mechanic(mechanicName, mechanicID);
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

        return mechanic;
    }

}
