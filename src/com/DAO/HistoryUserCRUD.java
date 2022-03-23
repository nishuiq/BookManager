package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryUserCRUD {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement preSql;
    private Query findRecord;

    public HistoryUserCRUD() {
        con = GetDBCConnection.connectDB("Library", "root", "");
        findRecord = new Query();
        findRecord.setCon(con);
    }


    public boolean insert(String username, String book) {
        if (con == null) return false;
        if (match(username, book) == true) return false;

        int ok = 0;
        try {
            String sqlStr = "INSERT INTO HistoryUser (`Date_s`, `user_name`, `book`) VALUES (?,?,?)";
            preSql = con.prepareStatement(sqlStr);
            //preSql.setString(1, String.valueOf(LocalDateTime.now()));
            preSql.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            preSql.setString(2, username);
            preSql.setString(3, book);
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return ok != 0;
    }

    public String[][] searchAll() {
        findRecord.setSQL("select * from HistoryUser order by Date_s desc");
        return findRecord.getRecord();
    }

    public String[][] searchAll(String username) {
        findRecord.setSQL("select * from HistoryUser where `user_name` = ( '" + username + "') order by Date_s desc");
        return findRecord.getRecord();
    }

    public String[] searchColumnAll() {
        return findRecord.getColumnName();
    }

    public boolean review(String username , String book) {
        if (con == null) return false;
        if (match(username, book) == false) return false;
        int ok = 0;

        try {
            String sqlStr = "UPDATE HistoryUser SET `state` = ?, `Date_e` = ? WHERE (`user_name`= ? and `book`= ? and `state` = 0)";
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, "1");
            preSql.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            preSql.setString(3, username);
            preSql.setString(4, book);
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return ok != 0;
    }

    // 匹配历史记录是否已经有
    public boolean match(String username, String book) {
        if (con == null || book.length() == 0) return false;
        String sqlStr = "select book from HistoryUser WHERE (`user_name` = ? and `book` = ? and `state` = \"0\")";
        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, username);
            preSql.setString(2, book);
            rs = preSql.executeQuery();
            if (rs.next() == true) return true;
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return false;
    }
}
