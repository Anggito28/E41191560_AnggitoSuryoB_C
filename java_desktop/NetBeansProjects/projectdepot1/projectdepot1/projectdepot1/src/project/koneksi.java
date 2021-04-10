/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class koneksi {
    Connection con;
    Statement stt;
    ResultSet rss;
    
    public void config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //konek ke database
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_depot", "root", "");
            stt = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
    private static Connection koneksi;
    public static Connection getKoneksi() {
        Connection koneksi = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_depot"; 
        String user = "root";
        String password = "";
        if (koneksi == null) {
            try {
                Class.forName(driver);
                koneksi = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException error) {
               System.exit(0);
            }

        }
        return koneksi;
    }
    static Object getConnection(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
