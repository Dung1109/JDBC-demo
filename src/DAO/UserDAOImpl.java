package DAO;

import utility.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl {

    public boolean checkLogin(String username, String password) {
        try (Connection conn = new DBUtils().getConnection()) {
            // 1. Add lib (implementation)
//            String sql = "SELECT COUNT(*) FROM Users WHERE username='"  + username + "'" + " AND password=" + "'" + password + "'";
//            ResultSet rs = stm.executeQuery(sql);

            String sql = "SELECT COUNT(*) FROM Users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
//            ps.executeQuery();

//            System.out.println("sql = " + sql);
//            stm.executeQuery(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Long count = rs.getLong(1);
            if (count >= 1) {
                return true;
            }

            // find column name of result set
//            String columnCount = rs.getMetaData().getColumnName(1);
//            System.out.println("columnCount = " + columnCount);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
