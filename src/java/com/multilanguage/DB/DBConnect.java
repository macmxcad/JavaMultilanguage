package com.multilanguage.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    Connection conn;
    
    public DBConnect() {
    }

    public Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver");            
            this.conn = DriverManager.getConnection("jdbc:postgresql://xxx.xxx.xxx.xxx:5432/MyDB", "User", "Password");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PreparedStatement preparestmt(String St) {
        try {
            return conn.prepareStatement(St);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
