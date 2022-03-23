package com.DAO;

import java.sql.*;

public class Query {
    Connection con;
    String SQL;
    String [] columnName;
    String [][] record;

    public void setCon(Connection con) {
        this.con = con;
    }
    public void setSQL(String SQL) {
        this.SQL = SQL.trim();
    }

    public String[] getColumnName() {
        return columnName == null ? null : columnName;
    }

    public String[][] getRecord() {
        startQuery();
        return record;
    }

    private void startQuery() {
        Statement sql;
        ResultSet rs;
        try  {
            sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sql.executeQuery(SQL);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            columnName = new String[columnCount];
            for (int i = 1; i <= columnCount; i ++) {
                columnName[i - 1] = metaData.getColumnName(i);
            }
            rs.last();
            int recordAmount = rs.getRow();
            record = new String[recordAmount][columnCount];
            int i = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int j = 1; j <= columnCount; j ++) {
                    record[i][j - 1] = rs.getString(j);
                }
                i ++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
