package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetDBCConnection {
    public static Connection connectDB(String DBName, String id, String p) {
        Connection con = null;
        String uri = "jdbc:mysql://localhost/"+ DBName + "?"+
                "useSSL=false&serverTimezone=GMT&characterEncoding=utf-8";
        try {
            con = DriverManager.getConnection(uri, id, p);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}