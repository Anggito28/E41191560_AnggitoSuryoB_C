/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

 
public class inputmenubar {
    Connection con = null;
    ResultSet rss = null;
    PreparedStatement pst = null;
    
    private String sql;
    public String kode_menu;
    public String nama_makanan;
    public String stok_makanan;
    public String harga_makanan;
    public String kode_katagori;
    
    public void simpan()throws SQLException{
        con = koneksi.getKoneksi();
        sql = "INSERT INTO tb_menu(kode_menu,nama_makanan,stok_makanan,harga_makanan,kode_katagori)VALUES(?,?,?,?,?)";
        pst = con.prepareStatement(sql);
        pst.setString(1,kode_menu);
        pst.setString(2,nama_makanan);
        pst.setString(3,stok_makanan);
        pst.setString(4,harga_makanan);
        pst.setString(5,kode_katagori);
        pst.execute();
        pst.close();
        
    }
   
    public ResultSet UpdateJTable()throws SQLException{
        con = koneksi.getKoneksi();
        sql = "select kode_menu,nama_makanan,stok_makanan,harga_makanan,kode_katagori from tb_menu";
        pst = con.prepareStatement(sql);
        rss= pst.executeQuery();
        return rss;
    }
}
