package com.multilanguage.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aykut.karayel
 */
public class DBConnect {

    Connection conn;
//    Statement stmt;
//    PreparedStatement prmt;

    public DBConnect() {
    }

    public Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver");
            //this.conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/octodb","octosrv","CNTocto!14");
            this.conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.132:5432/octodb_dev", "octosrv", "CNTocto!14");
//            this.conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.139:5432/octodb", "octosrv", "CNTocto!14");

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
