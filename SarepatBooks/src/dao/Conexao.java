/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
/**
 *
 * @author gabri
 */
public class Conexao {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "barbosa";
    private static final String PASS = "123";

    public static Connection open() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn,
                             PreparedStatement pstm, ResultSet rs)
    {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
               rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
