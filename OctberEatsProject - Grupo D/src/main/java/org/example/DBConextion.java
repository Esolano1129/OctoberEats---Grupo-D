package org.example;

import java.sql.*;

public class DBConextion {

    Connection conect;
    PreparedStatement prep;

    String url = "jdbc:mysql://localhost:3306/octobereatsdb";
    String username = "root";
    String password = "admin";

    public Connection StablishConection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection(url, username, password);


        } catch (SQLException | ClassNotFoundException error) {
            error.printStackTrace();
        }
        return conect;
    }

    public ResultSet getResult(String sql) {
        ResultSet result = null;
        try {
            if (conect == null || conect.isClosed()) {

                conect = StablishConection();
            }
            prep = conect.prepareStatement(sql);
            result = prep.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close() {
        try {
            if (prep != null && !prep.isClosed()) {
                prep.close();
            }
            if (conect != null && !conect.isClosed()) {
                conect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
