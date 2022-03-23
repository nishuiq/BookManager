package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCRUD {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement preSql;
    private Query findRecord;

    public BookCRUD() {
        con = GetDBCConnection.connectDB("Library", "root", "");
        findRecord = new Query();
        findRecord.setCon(con);
    }

    //TODO:CRUD
    public boolean insert(String title, String cnt) {
        if (con == null || title.length()  == 0 || !isNumber(cnt)) return false;

        if (match(title) == true) return false;

        int ok = 0;
        try {
            String sqlStr = "INSERT INTO Book (`title`, `store_cnt`) VALUES (?, ?)";
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, title);
            preSql.setString(2, cnt);
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return ok != 0;
    }

    // 严格书名匹配
    public boolean match(String title) {
        if (con == null || title.length() == 0) return false;
        String sqlStr = "select title from Book where title=?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, title);

            rs = preSql.executeQuery();
            if (rs.next() == true) return true;
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return false;
    }

    // TODO:查询一本书的数量
    public int getBookCnt(String title) {
        if (con == null || title.length() == 0) return -1;
        String sqlStr = "select store_cnt from Book where title=?";
        int cnt = -1;
        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, title);

            rs = preSql.executeQuery();
            if (rs.next()) cnt = rs.getInt(1);
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return cnt;
    }

    // TODO:借出一本书
    public boolean borrow(String title) {
        if (con == null || title.length() == 0) return false;
        if (match(title) == false) return false;  // 找不到书
        int cnt = -1;
        try {
            String sqlStr = "select store_cnt from Book where title=?";
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, title);

            rs = preSql.executeQuery();
            if (rs.next()) cnt = rs.getInt(1) - 1;
            if (cnt < 0) return false;  // 0本书别借了
            if (review(title, cnt + "", "") == false) return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // TODO:修改书名，数量 管理员行为
    public boolean review(String title, String cnt, String changeTitle) {
        if (con == null || title.length() == 0 || !isNumber(cnt) || changeTitle == null) return false;
        if (match(title) == false) return false;  // 不存在该书
        int ok = 0;
        try {
            if (changeTitle.length() == 0) {  // 书名不变
                String sqlStr = "UPDATE Book SET `store_cnt` = ? WHERE (`title` = ?)";
                preSql = con.prepareStatement(sqlStr);
                preSql.setString(1, cnt);
                preSql.setString(2, title);
            }
            else {  // 书名修改
                if (match(changeTitle) == true) return false;  // 该书名已存在啦
                String sqlStr = "UPDATE Book SET `title` = ?, `store_cnt` = ? WHERE (`title` = ?)";
                preSql = con.prepareStatement(sqlStr);
                preSql.setString(1, changeTitle);
                preSql.setString(2, cnt);
                preSql.setString(3, title);
            }
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) {  e.printStackTrace(); }
        return ok != 0;
    }

    public String[][] searchAll() {
        findRecord.setSQL("select * from Book");
        return findRecord.getRecord();
    }

    public String[] searchColumnAll() {
        return findRecord.getColumnName();
    }

    public String[][] fuzzySearch(String title) {
        if (title.length() == 0) findRecord.setSQL("select * from Book");
        else findRecord.setSQL("select * from Book where title like '%' \"" + title + "\" '%'");
        return findRecord.getRecord();
    }

    // 删除书名
    public boolean remove(String title) {
        if (con == null || title.length() == 0) return false;
        if (match(title) == false) return false;

        int ok = 0;
        String sqlStr = "DELETE FROM `Book` WHERE (`title` = ?)";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setString(1, title);
            ok = preSql.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return ok != 0;
    }

    // 判断cnt是否是数字，且不为空，且>=0
    private boolean isNumber(String s) {
        if (s.length() == 0) return false;
        for (int i = 0; i < s.length(); i ++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        if (Integer.parseInt(s) < 0) return false;
        return true;
    }
}