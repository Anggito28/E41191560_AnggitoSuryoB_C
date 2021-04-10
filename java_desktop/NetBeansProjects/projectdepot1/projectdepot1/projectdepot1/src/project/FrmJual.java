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

/**
 *
 * @author FB
 */
public class FrmJual extends javax.swing.JFrame {
private DefaultTableModel model;



    /**
     * Creates new form FrmJual
     */
    public FrmJual() {
        initComponents();
        combo_pelanggan();
        auto_key();
        txtNofa.disable();
        
        //variabel pembantu di tutup
        TxtIdPelanggan.hide();
        TxtStock.hide();
        TxtDateTime.hide();
        
        
        
        model =new DefaultTableModel();
        TblDetail.setModel(model);
        model.addColumn("ID Barang");
        model.addColumn("Nama Barnag");
        model.addColumn("Harga");
        model.addColumn("Qry");
        model.addColumn("Sub Total");
        model.addColumn("Jual Time");
        
        //START fungsi tidak menampilkan column ID barang(0) dan jual time (5)
        TblDetail.getColumnModel().getColumn(5).setMinWidth(0);
        TblDetail.getColumnModel().getColumn(5).setMaxWidth(0);
        TblDetail.getColumnModel().getColumn(5).setWidth(0);
        
        TblDetail.getColumnModel().getColumn(0).setMinWidth(0);
        TblDetail.getColumnModel().getColumn(0).setMaxWidth(0);
        TblDetail.getColumnModel().getColumn(0).setWidth(0);
        
        loadData();
        Date date = new Date();
        JdateJual.setDate(date);
    }
    
    
    

   public void Batal(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(TxtQty.getText());
    z = x+y;
    
    String Barang_ID=this.TxtKode.getText();
     try{
       Connection c= koneksi.getKoneksi();  
       String sql ="UPDATE tb_menu set stok_makanan=? WHERE kode_menu=?";  
       PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
           p.setInt(1,z);
           p.setString(2,Barang_ID);//yang kode atau id letakkan di nomor terakhir  
           p.executeUpdate();  
           p.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }
     
     
     
     //Proses mengahapus data dari tabel detail
     try {
        Connection c= koneksi.getKoneksi();
        String sql="DELETE From t_jualdetail WHERE jual_nofa='"+this.txtNofa.getText()+"' AND  jual_time ='"+this.TxtDateTime.getText()+"'";
        PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);
        p.executeUpdate();
        p.close();
    }catch(SQLException e){
        System.out.println("Terjadi Kesalahan");
    }finally{
        loadData();
        JOptionPane.showMessageDialog(this,"Sukses Hapus Data...");
    }  
   }
   
    
   public void Cari_Kode(){
   int i=TblDetail.getSelectedRow();  
   if(i==-1)  
   { return; }  
   String ID=(String)model.getValueAt(i, 0); 
   TxtKode.setText(ID);
   }
    
    
   public void ShowData(){
   try {
        Connection c=koneksi.getKoneksi();
        String sql="Select * from t_jualdetail, tb_menu WHERE t_jualdetail.jual_barangid = tb_menu.kode_menu AND t_jualdetail.jual_barangid='"+this.TxtKode.getText()+"'"; 
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.TxtQty.setText(rs.getString("jual_qty"));
        this.TxtNama.setText(rs.getString("nama_makanan"));
        this.TxtHJual.setText(rs.getString("jual_harga"));
        this.TxtSubTotal.setText(rs.getString("jual_subtotal"));
        this.TxtDateTime.setText(rs.getString("jual_time"));
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
 }

   
   public void ShowSisa(){
   try {
        Connection c=koneksi.getKoneksi();
        String sql="Select * from  tb_menu WHERE kode_menu ='"+this.TxtKode.getText()+"'"; 
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.TxtStock.setText(rs.getString("stok_makanan"));      
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
 } 

  
    
public void UpdateStock(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(TxtQty.getText());
    z = x-y;
    
    String Barang_ID=this.TxtKode.getText();
     try{
       Connection c= koneksi.getKoneksi();  
       String sql ="UPDATE tb_menu set stok_makanan=? WHERE kode_menu=?";  
       PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
           p.setInt(1,z);
           p.setString(2,Barang_ID);//yang kode atau id letakkan di nomor terakhir  
           p.executeUpdate();  
           p.close();  
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
     Connection c= koneksi.getKoneksi();
       Statement s= c.createStatement();
     String sql="Select * from t_jualdetail, tb_menu WHERE t_jualdetail.jual_barangid = tb_menu.kode_menu AND t_jualdetail.jual_nofa='"+this.txtNofa.getText()+"'"; 
       ResultSet r=s.executeQuery(sql);
     while(r.next()){
       Object[]o=new Object[6];
       o[0]=r.getString("jual_barangid");
       o[1]=r.getString("nama_makanan");
       o[2]=r.getString("jual_harga");
       o[3]=r.getString("jual_qty");
       o[4]=r.getString("jual_subtotal");
       o[5]=r.getString("stok_makanan");
       model.addRow(o);
     }  
     r.close();  
     s.close();  
     //ShowData();  
   }catch(SQLException e){  
     System.out.println("Terjadi Kesalahan");  
   }



   //menjumlahkan isi colom ke 4 dalam tabel
   int total = 0;
   for (int i =0; i< TblDetail.getRowCount(); i++){
       int amount = Integer.parseInt((String)TblDetail.getValueAt(i, 4));
       total += amount;
   }
   TxtTotal.setText(""+total);
 }  
    
    public void AutoSum() {     
        int a, b, c;
        a = Integer.parseInt(TxtHJual.getText());
        b = Integer.parseInt(TxtQty.getText());
        c = a*b;
        TxtSubTotal.setText(""+c);
    }
    
    
        public void HitungKembali() {     
        int d, e, f;
        d = Integer.parseInt(TxtTotal.getText());
        e = Integer.parseInt(TxtCash.getText());
        f = e-d;
        TxtKembali.setText(""+f);
    }
        
        
        
        
    public void auto_key(){  
   try {  
   java.util.Date tgl = new java.util.Date();  
   java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyMMdd");  
   java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMMdd");  
     Connection c=koneksi.getKoneksi();  
     String sql = "select max(jual_nofa) from t_jual WHERE jual_tgl ="+tanggal.format(tgl);   
     Statement st = koneksi.getKoneksi().createStatement();  
     ResultSet rs = st.executeQuery(sql);  
     while(rs.next()){  
     Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.txtNofa.setText(kal.format(tgl)+"0000"+(a+1));  
       }else{  
         this.txtNofa.setText(""+(a+1));  
       }  
   }  
   rs.close(); st.close();}  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
   }  
 }  
   
    


   
    public void Selesai(){   
   String jual_nofa =this.txtNofa.getText();  
   String jual_pelanggan=this.TxtIdPelanggan.getText();    
   String jual_total=this.TxtTotal.getText();
   String jual_cash =this.TxtCash.getText();
   String jual_kembali =this.TxtKembali.getText();
   
   //Date date = new Date();
   //JdateJual.setDate(date);
        
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
   Date tanggal = new Date(); 
   tanggal = JdateJual.getDate(); 
   String jual_tgl = dateFormat.format(tanggal);

        
        
   
   try{  
     Connection c=koneksi.getKoneksi();  
     String sql="Insert into t_jual (jual_nofa,jual_tgl,jual_pelanggan,jual_total,jual_cash,jual_kembali) values (?,?,?,?,?,?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,jual_nofa);
     p.setString(2,jual_tgl);
     p.setString(3,jual_pelanggan);
     p.setString(4,jual_total);
     p.setString(5,jual_cash);
     p.setString(6,jual_kembali);
     p.executeUpdate();
     p.close();
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
      
   String jual_nofa =this.txtNofa.getText();  
   String jual_barangid =this.TxtKode.getText();  
   String jual_harga=this.TxtHJual.getText();  
   String jual_qty=this.TxtQty.getText();
   String jual_subtotal =this.TxtSubTotal.getText();
   String DateTime = ft.format(HariSekarang);
   
   
   try{  
     Connection c=koneksi.getKoneksi();  
     String sql="Insert into t_jualdetail (jual_nofa,jual_barangid,jual_harga,jual_qty,jual_subtotal,jual_time) values (?,?,?,?,?,?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,jual_nofa);
     p.setString(2,jual_barangid);
     p.setString(3,jual_harga);
     p.setString(4,jual_qty);
     p.setString(5,jual_subtotal);
     p.setString(6,DateTime);
     p.executeUpdate();
     p.close();
   }catch(SQLException e){ 
   System.out.println(e);  
   }finally{  
   //loadData();
       //JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
 }
    
    


    public void cari_id(){
        try {
        Connection c=koneksi.getKoneksi();
        String sql = "select * from tb_menu, tb_katagori where tb_menu.kode_katagori = tb_katagori.kode_katagori AND tb_menu.kode_menu='"+this.TxtKode.getText()+"'"; 
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
        this.TxtNama.setText(rs.getString("nama_makanan"));
        this.TxtHJual.setText(rs.getString("harga_makanan"));
        this.TxtStock.setText(rs.getString("stok_makanan"));
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
    
    
    
    
    
    
    
    
    
    
    
    
    public void cari_nama()
    {
        try {
        Connection c=koneksi.getKoneksi();
        String sql_t = "select pelanggan_id from t_pelanggan where pelanggan_nama='"+cmbPelanggan.getSelectedItem()+"'"; 
        Statement st = koneksi.getKoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql_t);                              // yang anda ingin kan
        
        while(rs.next()){
        this.TxtIdPelanggan.setText(rs.getString("pelanggan_id")); 
        }
        rs.close(); st.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }     
    }
    
    
    public  void bersihkan(){
        TxtKode.setText("");
        TxtNama.setText("");
        TxtHJual.setText("");
        TxtQty.setText("");
        TxtCash.setText("");
        TxtSubTotal.setText("");
        TxtKembali.setText("");
       
    }
    
    
    
    
    
    public void combo_pelanggan()
    {
        try {
        Connection c=koneksi.getKoneksi();
        Statement st = c.createStatement();
        String sql_tc = "select pelanggan_id, pelanggan_nama from t_pelanggan order by pelanggan_id asc";
        ResultSet rs = st.executeQuery(sql_tc);

        while(rs.next()){
            String nama = rs.getString("pelanggan_nama");
            cmbPelanggan.addItem(nama);
        }
        rs.close(); st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnSimpan = new javax.swing.JButton();
        cmbPelanggan = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        TxtKode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtNama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TxtHJual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxtQty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtSubTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDetail = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        TxtCash = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TxtKembali = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TxtIdPelanggan = new javax.swing.JTextField();
        TxtStock = new javax.swing.JTextField();
        TxtDateTime = new javax.swing.JTextField();
        btnkeluar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtTotal = new javax.swing.JTextField();
        JdateJual = new com.toedter.calendar.JDateChooser();
        txtNofa = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JButton();
        BtnAdd1 = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Depot Modern V.1");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnSimpan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_save_30px.png"))); // NOI18N
        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(BtnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, 110, 40));

        cmbPelanggan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPelangganMouseClicked(evt);
            }
        });
        cmbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelangganActionPerformed(evt);
            }
        });
        getContentPane().add(cmbPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 160, 30));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel5.setText("Kode Barang");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        TxtKode.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtKode.setName(""); // NOI18N
        TxtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeActionPerformed(evt);
            }
        });
        TxtKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtKodeKeyPressed(evt);
            }
        });
        getContentPane().add(TxtKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 160, 30));

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel7.setText("Nama");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, -1, -1));

        TxtNama.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtNama.setName(""); // NOI18N
        TxtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNamaActionPerformed(evt);
            }
        });
        TxtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNamaKeyPressed(evt);
            }
        });
        getContentPane().add(TxtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 160, 30));

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel8.setText("Harga Jual");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, -1));

        TxtHJual.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtHJual.setName(""); // NOI18N
        TxtHJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHJualActionPerformed(evt);
            }
        });
        TxtHJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtHJualKeyPressed(evt);
            }
        });
        getContentPane().add(TxtHJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 160, 30));

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel9.setText("Qty");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, -1, -1));

        TxtQty.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtQty.setName(""); // NOI18N
        TxtQty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtQtyMouseClicked(evt);
            }
        });
        TxtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtQtyActionPerformed(evt);
            }
        });
        TxtQty.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TxtQtyPropertyChange(evt);
            }
        });
        TxtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtQtyKeyPressed(evt);
            }
        });
        getContentPane().add(TxtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 150, 30));

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel10.setText("Sub Total");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 250, -1, -1));

        TxtSubTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtSubTotal.setName(""); // NOI18N
        TxtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSubTotalActionPerformed(evt);
            }
        });
        TxtSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtSubTotalKeyPressed(evt);
            }
        });
        getContentPane().add(TxtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 270, 160, 30));

        TblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama  Barang", "Harga", "Qty", "Sub Total", "jual_time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDetail.getTableHeader().setReorderingAllowed(false);
        TblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblDetail);
        if (TblDetail.getColumnModel().getColumnCount() > 0) {
            TblDetail.getColumnModel().getColumn(0).setMinWidth(0);
            TblDetail.getColumnModel().getColumn(0).setPreferredWidth(0);
            TblDetail.getColumnModel().getColumn(0).setMaxWidth(0);
            TblDetail.getColumnModel().getColumn(1).setResizable(false);
            TblDetail.getColumnModel().getColumn(2).setResizable(false);
            TblDetail.getColumnModel().getColumn(3).setResizable(false);
            TblDetail.getColumnModel().getColumn(4).setResizable(false);
            TblDetail.getColumnModel().getColumn(5).setMinWidth(0);
            TblDetail.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 996, 164));

        jLabel11.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel11.setText("Cash");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 60, 31));

        TxtCash.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtCash.setName(""); // NOI18N
        TxtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCashKeyPressed(evt);
            }
        });
        getContentPane().add(TxtCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 186, 31));

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setText("........................");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, 270, -1));

        TxtKembali.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TxtKembali.setName(""); // NOI18N
        getContentPane().add(TxtKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 186, 30));

        jLabel13.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel13.setText("Kembali");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 60, 34));

        TxtIdPelanggan.setName(""); // NOI18N
        TxtIdPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIdPelangganActionPerformed(evt);
            }
        });
        TxtIdPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtIdPelangganKeyPressed(evt);
            }
        });
        getContentPane().add(TxtIdPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 570, 206, -1));
        getContentPane().add(TxtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 550, 206, -1));
        getContentPane().add(TxtDateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 600, 206, -1));

        btnkeluar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_cancel_30px.png"))); // NOI18N
        btnkeluar.setText("Keluar");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, 100, 40));

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));

        jPanel2.setBackground(new java.awt.Color(204, 153, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logotulisan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel2.setText("Nomor Faktur");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel4.setText("Pelanggan");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel6.setText("Total Pembelian (Rp)");

        TxtTotal.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        TxtTotal.setName(""); // NOI18N
        TxtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTotalActionPerformed(evt);
            }
        });
        TxtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtTotalKeyPressed(evt);
            }
        });

        JdateJual.setDateFormatString("yyyy-MM-dd");

        txtNofa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNofa.setName(""); // NOI18N
        txtNofa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNofaActionPerformed(evt);
            }
        });
        txtNofa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNofaKeyPressed(evt);
            }
        });

        BtnAdd.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        BtnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_30px.png"))); // NOI18N
        BtnAdd.setText("ADD");
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });

        BtnAdd1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        BtnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_report_card_16px.png"))); // NOI18N
        BtnAdd1.setText("LIST");
        BtnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdd1ActionPerformed(evt);
            }
        });

        BtnBatal.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_waste_24px.png"))); // NOI18N
        BtnBatal.setText("Batal");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JdateJual, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNofa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(387, 387, 387)))
                .addGap(186, 186, 186))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtnAdd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNofa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JdateJual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(BtnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNofaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNofaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNofaActionPerformed

    private void txtNofaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNofaKeyPressed

    }//GEN-LAST:event_txtNofaKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here:
        Selesai();
        bersihkan();
        
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Batal();
        bersihkan();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
       TambahDetail();
       UpdateStock();
       loadData();
       bersihkan();
       
    }//GEN-LAST:event_BtnAddActionPerformed

    private void cmbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelangganActionPerformed
        // TODO add your handling code here:
        cari_nama();
    }//GEN-LAST:event_cmbPelangganActionPerformed

    private void TxtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtKodeActionPerformed

    private void TxtKodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtKodeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
            cari_id();
        }
    }//GEN-LAST:event_TxtKodeKeyPressed

    private void TxtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamaActionPerformed

    private void TxtNamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamaKeyPressed

    private void TxtHJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHJualActionPerformed

    private void TxtHJualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtHJualKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHJualKeyPressed

    private void TxtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtQtyActionPerformed

    private void TxtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtQtyKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        AutoSum();
        }
    }//GEN-LAST:event_TxtQtyKeyPressed

    private void TxtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSubTotalActionPerformed

    private void TxtSubTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSubTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSubTotalKeyPressed

    private void TblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDetailMouseClicked
        this.Cari_Kode();
        this.ShowData();
        this.ShowSisa();
    }//GEN-LAST:event_TblDetailMouseClicked

    private void TxtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalActionPerformed

    private void TxtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalKeyPressed

    private void TxtQtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtQtyMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_TxtQtyMouseClicked

    private void TxtQtyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TxtQtyPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtQtyPropertyChange

    private void TxtCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCashKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        HitungKembali();
        }
    }//GEN-LAST:event_TxtCashKeyPressed

    private void TxtIdPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIdPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIdPelangganActionPerformed

    private void TxtIdPelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtIdPelangganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIdPelangganKeyPressed

    private void cmbPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPelangganMouseClicked
        // TODO add your handling code here:
        
        cari_nama();
        
    }//GEN-LAST:event_cmbPelangganMouseClicked

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
    this.dispose();
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void BtnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdd1ActionPerformed
      listmenu l = new listmenu();
      l.setVisible(true);
      l.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnAdd1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnAdd1;
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnSimpan;
    private com.toedter.calendar.JDateChooser JdateJual;
    private javax.swing.JTable TblDetail;
    private javax.swing.JTextField TxtCash;
    private javax.swing.JTextField TxtDateTime;
    private javax.swing.JTextField TxtHJual;
    private javax.swing.JTextField TxtIdPelanggan;
    private javax.swing.JTextField TxtKembali;
    private javax.swing.JTextField TxtKode;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtQty;
    private javax.swing.JTextField TxtStock;
    private javax.swing.JTextField TxtSubTotal;
    private javax.swing.JTextField TxtTotal;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JComboBox<String> cmbPelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNofa;
    // End of variables declaration//GEN-END:variables
}
