package com.DAO;

import java.sql.*;

// User 用户表
public class UserCRUD {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement preSql;

    public UserCRUD() {
        con = GetDBCConnection.connectDB("Library", "root", "");
    }

    public boolean insert(String id, String password, Boolean isManager) {
        if (con == null) return false;
        if (search(id) == true) return false;

        int ok = 0;
        try {
            String sqlStr = "INSERT INTO User (`name`, `password`, `manager`) VALUES (?, ?, ?)";
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, id);
            preSql.setString(2, password);
            preSql.setString(3, isManager == false ? "0" : "1");
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return ok != 0;
    }

    // 修改密码
    public boolean review(String id, String password, String newPassword, Boolean isManager) {
        if (con == null) return false;
        if (id.length() == 0 || password.length() == 0 || newPassword.length() == 0) return false;
        if (match(id, password, isManager) == false) return false;  // 不存在该用户名
        int ok = 0;

        try {
            String sqlStr = "UPDATE User SET `password` = ? WHERE (`name` = ? and `password` = ?)";
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, newPassword);
            preSql.setString(2, id);
            preSql.setString(3, password);
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return ok != 0;
    }
    // 匹配用户名和密码
    public boolean match(String id, String password, Boolean isManager) {
        if (con == null) return false;
        String sqlStr = "select name from User where name=? and password=? and manager=?";
        int ok = 0;

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, id);
            preSql.setString(2, password);
            preSql.setString(3, isManager == false ? "0" : "1");

            rs = preSql.executeQuery();
            if (rs.next() == true) return true;
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return false;
    }

    // 仅查询用户名是否存在
    public boolean search(String id) {
        if (con == null) return false;
        String sqlStr = "select name from User where name=?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, id);

            rs = preSql.executeQuery();
            if (rs.next() == true) return true;  // 已有用户名
        }
        catch (SQLException e) { e.printStackTrace(); }

        return false;
    }
}