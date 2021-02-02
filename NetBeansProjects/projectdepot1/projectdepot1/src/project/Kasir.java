/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import com.sun.javafx.image.impl.IntArgb;
import com.sun.prism.PresentableState;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TreeMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.Date;
/**
 *
 * @author asus
 */
public class Kasir extends javax.swing.JFrame {
private DefaultTableModel model;
    Connection con = null;
    ResultSet rss = null;
    PreparedStatement pst = null;
    /**
     * Creates new form Kasir
     */
    public Kasir() {
        initComponents();
        auto_key();
        nota1.disable();
        
        TxtStock.hide();
        TxtDateTime.hide();
                
        model = new DefaultTableModel();
        tbdetail.setModel(model);
        model.addColumn("kode_menu");
        model.addColumn("nama_makanan");
        model.addColumn("harga_makanan");
        model.addColumn("jumlah");
        model.addColumn("subtotal");
        model.addColumn("timedate");
        
        tbdetail.getColumnModel().getColumn(5).setMinWidth(0);
        tbdetail.getColumnModel().getColumn(5).setMaxWidth(0);
        tbdetail.getColumnModel().getColumn(5).setWidth(0);
        
        tbdetail.getColumnModel().getColumn(0).setMinWidth(0);
        tbdetail.getColumnModel().getColumn(0).setMaxWidth(0);
        tbdetail.getColumnModel().getColumn(0).setWidth(0);
        
        loadData();
        Date date = new Date();
        JDatebeli.setDate(date);
    }
 public void Batal(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(jumlahbeli1.getText());
    z = x+y;
    
    String Barang_ID=this.kodebar.getText();
     try{
       Connection con= koneksi.getKoneksi();  
       String sql ="UPDATE tb_menu set stok_makanan=? WHERE kode_menu=?";  
       PreparedStatement pss=(PreparedStatement)con.prepareStatement(sql);  
           pss.setInt(1,z);
           pss.setString(2,Barang_ID);//yang kode atau id letakkan di nomor terakhir  
           pss.executeUpdate();  
           pss.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }  
     try {
        Connection con= koneksi.getKoneksi();
        String sql="DELETE From tb_detailkasir WHERE kode_transaksi='"+this.nota1.getText()+"' AND  timedate ='"+this.TxtDateTime.getText()+"'";
        PreparedStatement pss=(PreparedStatement)con.prepareStatement(sql);
        pss.executeUpdate();
        pss.close();
    }catch(SQLException e){
        System.out.println("Terjadi Kesalahan");
    }finally{
        loadData();
        JOptionPane.showMessageDialog(this,"Sukses Hapus Data...");
    }
    }
 public void Cari_Kode(){
   int i=tbdetail.getSelectedRow();  
   if(i==-1)  
   { return; }  
   String ID=(String)model.getValueAt(i, 0); 
   kodebar.setText(ID);
   }
 public void ShowData(){
   try {
        Connection con=koneksi.getKoneksi();
        String sql="Select * from tb_detailkasir, tb_menu WHERE tb_detailkasir.kode_menu = tb_menu.kode_menu AND tb_detailkasir.kode_menu='"+this.kodebar.getText()+"'"; 
        Statement stt = koneksi.getKoneksi().createStatement();
        ResultSet rss = stt.executeQuery(sql);
        while(rss.next()){
        this.jumlahbeli1.setText(rss.getString("jumlah"));
        this.namabar.setText(rss.getString("nama_makanan"));
        this.hargabar.setText(rss.getString("harga_makanan"));
        this.subtotal.setText(rss.getString("subtotal"));
        this.TxtDateTime.setText(rss.getString("timedate"));
        }
        rss.close(); stt.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
 }
 public void ShowSisa(){
   try {
        Connection con=koneksi.getKoneksi();
        String sql="Select * from  tb_menu WHERE kode_menu ='"+this.kodebar.getText()+"'"; 
        Statement stt = koneksi.getKoneksi().createStatement();
        ResultSet rss = stt.executeQuery(sql);
        while(rss.next()){
        this.TxtStock.setText(rss.getString("stok_makanan"));      
        }
        rss.close(); stt.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
 } 
 public void UpdateStock(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(jumlahbeli1.getText());
    z = x-y;
    
    String Barang_ID=this.kodebar.getText();
     try{
       Connection con= koneksi.getKoneksi();  
       String sql ="UPDATE tb_menu set stok_makanan=? WHERE kode_menu=?";  
       PreparedStatement pss=(PreparedStatement)con.prepareStatement(sql);  
           pss.setInt(1,z);
           pss.setString(2,Barang_ID);//yang kode atau id letakkan di nomor terakhir  
           pss.executeUpdate();  
           pss.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }
}
 public final void loadData(){
   model.getDataVector().removeAllElements();
   model.fireTableDataChanged();
   try{  
     Connection con= koneksi.getKoneksi();
     Statement stt= con.createStatement();
     String sql="Select * from tb_detailkasir, tb_menu WHERE tb_detailkasir.kode_menu = tb_menu.kode_menu AND tb_detailkasir.kode_transaksi='"+this.nota1.getText()+"'"; 
     ResultSet rss=stt.executeQuery(sql);
     while(rss.next()){
       Object[]o=new Object[6];
       o[0]=rss.getString("kode_menu");
       o[1]=rss.getString("nama_makanan");
       o[2]=rss.getString("harga_makanan");
       o[3]=rss.getString("jumlah");
       o[4]=rss.getString("subtotal");
       o[5]=rss.getString("stok_makanan");
       model.addRow(o);
     }  
     rss.close();  
     stt.close();  
     //ShowData();  
   }catch(SQLException e){  
     System.out.println("Terjadi Kesalahan");  
   }



   //menjumlahkan isi colom ke 4 dalam tabel
   int total = 0;
   for (int i =0; i< tbdetail.getRowCount(); i++){
       int amount = Integer.parseInt((String)tbdetail.getValueAt(i, 4));
       total += amount;
   }
   totaltxt.setText(""+total);
 }  
 public void AutoSum() {     
        int a, b, c;
        a = Integer.parseInt(hargabar.getText());
        b = Integer.parseInt(jumlahbeli1.getText());
        c = a*b;
        subtotal.setText(""+c);
    }
    
    
        public void HitungKembali() {     
        int d, e, f;
        d = Integer.parseInt(totaltxt.getText());
        e = Integer.parseInt(bayartxt.getText());
        f = e-d;
        kembalitxt.setText(""+f);
    }
public void auto_key(){
    try {  
   java.util.Date tgl = new java.util.Date();  
   java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyMMdd");  
   java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMMdd");  
     Connection con=koneksi.getKoneksi();  
     String sql = "select max(kode_transaksi) from tb_kasir WHERE tgltransaksi ="+tanggal.format(tgl);   
     Statement stt = koneksi.getKoneksi().createStatement();  
     ResultSet rss = stt.executeQuery(sql);  
     while(rss.next()){  
     Long a =rss.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.nota1.setText(kal.format(tgl)+"0000"+(a+1));  
       }else{  
         this.nota1.setText(""+(a+1));  
       }  
   }  
   rss.close(); stt.close();}  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
   }  
}
public void Selesai(){   
   String kode_transaksi =this.nota1.getText();
   String nama_pelanggan = this.namapembeli.getText();
   String harga=this.hargabar.getText();    
   String totalbayar=this.totaltxt.getText();
   String bayar =this.bayartxt.getText();
   String kembali =this.kembalitxt.getText();
   
   //Date date = new Date();
   //JdateJual.setDate(date);
        
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
   Date tanggal = new Date(); 
   tanggal = JDatebeli.getDate(); 
   String tgltransaksi = dateFormat.format(tanggal);

        
        
   
   try{  
     Connection con=koneksi.getKoneksi();  
     String sql="Insert into tb_kasir (kode_transaksi,nama_pelanggan,tgltransaksi,bayar,totalbayar,kembali) values (?,?,?,?,?,?)";  
     PreparedStatement pss=(PreparedStatement)con.prepareStatement(sql);  
     pss.setString(1,kode_transaksi);
     pss.setString(2,nama_pelanggan);
     pss.setString(3,tgltransaksi);
     pss.setString(4,bayar);
     pss.setString(5,totalbayar);
     pss.setString(6,kembali);
     pss.executeUpdate();
     pss.close();
   }catch(SQLException e){ 
   System.out.println(e);  
   }finally{  
   //loadData();
       JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
   
  auto_key();
  loadData();
 }  
public void TambahDetail(){
   Date HariSekarang = new Date( );
   SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
      
   String kode_transaksi =this.nota1.getText();  
   String kode_menu =this.kodebar.getText();  
   String harga_makanan=this.hargabar.getText();  
   String jumlah=this.jumlahbeli1.getText();
   String subtotal =this.subtotal.getText();
   String timedate = ft.format(HariSekarang);
   
   
   try{  
     Connection con=koneksi.getKoneksi();  
     String sql="Insert into tb_detailkasir (kode_transaksi,kode_menu,harga_makanan,jumlah,subtotal,timedate) values (?,?,?,?,?,?)";  
     PreparedStatement pss=(PreparedStatement)con.prepareStatement(sql);  
     pss.setString(1,kode_transaksi);
     pss.setString(2,kode_menu);
     pss.setString(3,harga_makanan);
     pss.setString(4,jumlah);
     pss.setString(5,subtotal);
     pss.setString(6,timedate);
     pss.executeUpdate();
     pss.close();
   }catch(SQLException e){ 
   System.out.println(e);  
   }finally{  
   //loadData();
       //JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
 }
public void cari_id(){
        try {
        Connection con=koneksi.getKoneksi();
        String sql = "select * from tb_menu WHERE kode_menu='"+this.kodebar.getText()+"'"; 
        Statement stt = koneksi.getKoneksi().createStatement();
        ResultSet rss = stt.executeQuery(sql);
        
        while(rss.next()){
        this.namabar.setText(rss.getString("nama_makanan"));
        this.hargabar.setText(rss.getString("harga_makanan"));
        this.TxtStock.setText(rss.getString("stok_makanan"));
        }
        rss.close(); stt.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
    public  void bersihkan(){
        kodebar.setText("");
        namabar.setText("");
        hargabar.setText("");
        jumlahbeli1.setText("");
        bayartxt.setText("");
        subtotal.setText("");
        kembalitxt.setText("");
       
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sbttl = new javax.swing.JLabel();
        notatx = new javax.swing.JLabel();
        nmtx = new javax.swing.JLabel();
        kdtx = new javax.swing.JLabel();
        nmbartx = new javax.swing.JLabel();
        hrgbrgtx = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        bayartxt = new javax.swing.JTextField();
        nota1 = new javax.swing.JTextField();
        namapembeli = new javax.swing.JTextField();
        kodebar = new javax.swing.JTextField();
        namabar = new javax.swing.JTextField();
        hargabar = new javax.swing.JTextField();
        jumlahbeli1 = new javax.swing.JTextField();
        kembalitxt = new javax.swing.JTextField();
        totaltxt = new javax.swing.JTextField();
        TxtStock = new javax.swing.JTextField();
        TxtDateTime = new javax.swing.JTextField();
        subtotal = new javax.swing.JTextField();
        jmltx1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totaltx = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdetail = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        nmbg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sbttl.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        sbttl.setText("SubTotal");
        getContentPane().add(sbttl, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 160, 40));

        notatx.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        notatx.setText("No Nota");
        getContentPane().add(notatx, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 160, 40));

        nmtx.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        nmtx.setText("Nama Pembeli");
        getContentPane().add(nmtx, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 160, 40));

        kdtx.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        kdtx.setText("Kode Barang");
        getContentPane().add(kdtx, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 160, 40));

        nmbartx.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        nmbartx.setText("Nama Barang");
        getContentPane().add(nmbartx, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 160, 40));

        hrgbrgtx.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        hrgbrgtx.setText("Harga Barang");
        getContentPane().add(hrgbrgtx, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 160, 40));

        tanggal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tanggal.setText("Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 90, 40));

        bayartxt.setBackground(new java.awt.Color(255, 255, 153));
        bayartxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bayartxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bayartxtKeyPressed(evt);
            }
        });
        getContentPane().add(bayartxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 210, 40));

        nota1.setBackground(new java.awt.Color(255, 255, 153));
        nota1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(nota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 210, 40));

        namapembeli.setBackground(new java.awt.Color(255, 255, 153));
        namapembeli.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(namapembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 210, 40));

        kodebar.setBackground(new java.awt.Color(255, 255, 153));
        kodebar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        kodebar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodebarKeyPressed(evt);
            }
        });
        getContentPane().add(kodebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 210, 40));

        namabar.setBackground(new java.awt.Color(255, 255, 153));
        namabar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(namabar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 210, 40));

        hargabar.setBackground(new java.awt.Color(255, 255, 153));
        hargabar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(hargabar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 210, 40));

        jumlahbeli1.setBackground(new java.awt.Color(255, 255, 153));
        jumlahbeli1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jumlahbeli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahbeli1KeyPressed(evt);
            }
        });
        getContentPane().add(jumlahbeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 210, 40));

        kembalitxt.setBackground(new java.awt.Color(255, 255, 153));
        kembalitxt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(kembalitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 600, 210, 40));

        totaltxt.setBackground(new java.awt.Color(255, 204, 102));
        totaltxt.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        totaltxt.setText("0,0");
        getContentPane().add(totaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 650, 320, 80));
        getContentPane().add(TxtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 140, -1));
        getContentPane().add(TxtDateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 140, -1));

        subtotal.setBackground(new java.awt.Color(255, 255, 153));
        subtotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 210, 40));

        jmltx1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jmltx1.setText("Jumlah Beli");
        getContentPane().add(jmltx1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 160, 40));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("BAYAR     :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("KEMBALI :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, -1));

        totaltx.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        totaltx.setText("TOTAL :");
        getContentPane().add(totaltx, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 660, -1, -1));

        cancel.setText("Cancel");
        cancel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 153)));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        getContentPane().add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 650, 90, 40));

        simpan.setText("Simpan");
        simpan.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 153)));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 90, 40));

        jButton3.setText("Cetak Nota");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 620, 90, 40));

        reset.setText("Reset");
        reset.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 153)));
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        getContentPane().add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 650, 80, 40));

        add.setText("Add");
        add.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 153)));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 650, 90, 40));

        tbdetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tbdetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbdetail);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 520, 370));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 51), null));

        nmbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logotulisan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(nmbg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nmbg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 140));

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));

        judul.setFont(new java.awt.Font("SimSun-ExtB", 1, 24)); // NOI18N
        judul.setText("KASIR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(463, 463, 463))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1060, 40));

        bg.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/makanan.jpg"))); // NOI18N
        bg.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 153)));
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
       Selesai();
       bersihkan();
    }//GEN-LAST:event_simpanActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        Batal();
        bersihkan();
    }//GEN-LAST:event_cancelActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
       TambahDetail();
       UpdateStock();
       loadData();
       bersihkan();
    }//GEN-LAST:event_addActionPerformed

    private void kodebarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodebarKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
            cari_id();}
    }//GEN-LAST:event_kodebarKeyPressed

    private void jumlahbeli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahbeli1KeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        AutoSum();}
    }//GEN-LAST:event_jumlahbeli1KeyPressed

    private void tbdetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdetailMouseClicked
        this.Cari_Kode();
        this.ShowData();
        this.ShowSisa();
    }//GEN-LAST:event_tbdetailMouseClicked

    private void bayartxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayartxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        HitungKembali();}
    }//GEN-LAST:event_bayartxtKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtDateTime;
    private javax.swing.JTextField TxtStock;
    private javax.swing.JButton add;
    private javax.swing.JTextField bayartxt;
    private javax.swing.JLabel bg;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField hargabar;
    private javax.swing.JLabel hrgbrgtx;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jmltx1;
    private javax.swing.JLabel judul;
    private javax.swing.JTextField jumlahbeli1;
    private javax.swing.JLabel kdtx;
    private javax.swing.JTextField kembalitxt;
    private javax.swing.JTextField kodebar;
    private javax.swing.JTextField namabar;
    private javax.swing.JTextField namapembeli;
    private javax.swing.JLabel nmbartx;
    private javax.swing.JLabel nmbg;
    private javax.swing.JLabel nmtx;
    private javax.swing.JTextField nota1;
    private javax.swing.JLabel notatx;
    private javax.swing.JButton reset;
    private javax.swing.JLabel sbttl;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField subtotal;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTable tbdetail;
    private javax.swing.JLabel totaltx;
    private javax.swing.JTextField totaltxt;
    // End of variables declaration//GEN-END:variables
}
