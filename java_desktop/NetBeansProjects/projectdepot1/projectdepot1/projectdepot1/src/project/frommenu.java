/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author asus
 */
public class frommenu extends javax.swing.JFrame {

    
    Statement stt;
    ResultSet rss;
    koneksi koneksi;
        
    String user_id = UserSession.getacces();
    String user_menu = String.valueOf(user_id);
    
    String name_id =UserSession.getname();
    String name_menu = String.valueOf(name_id);
    
    
 
    public frommenu() {
        
        koneksi = new koneksi ();
        initComponents();
        switch (user_menu)
        {
        case"admin":
            admin();
            break ;
        case"pegawai":
            pegawai();
            break;            
        }
        keterangan.setText(user_menu);
        namakaryawan.setText(name_menu);
    }
    
    public void admin(){
        transaksi.setEnabled(false);
        pengaturan.setEnabled(true);
        menumakanan.setEnabled(true);
        laporan.setEnabled(true);
        katalogbarang.setEnabled(true);
        aplikasi.setEnabled(true);
        pengaturan.setEnabled(true);
    }
    public void pegawai(){
        transaksi.setEnabled(true);
        pengaturan.setEnabled(false);
        menumakanan.setEnabled(true);
        laporan.setEnabled(false);
        katalogbarang.setEnabled(true);
        aplikasi.setEnabled(true);
        pengaturan.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        avatar = new javax.swing.JLabel();
        namakaryawan = new javax.swing.JLabel();
        keterangan = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        transaksi = new javax.swing.JButton();
        menumakanan = new javax.swing.JButton();
        laporan = new javax.swing.JButton();
        katalogbarang = new javax.swing.JButton();
        aplikasi = new javax.swing.JButton();
        pengaturan = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        menuutamaname = new javax.swing.JLabel();
        bg1 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Depot Modern V.1");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(91, 51, 3));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_male_user_50px_1.png"))); // NOI18N

        namakaryawan.setFont(new java.awt.Font("Kozuka Mincho Pr6N L", 0, 18)); // NOI18N
        namakaryawan.setForeground(new java.awt.Color(255, 255, 102));
        namakaryawan.setText("43");
        namakaryawan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                namakaryawanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        keterangan.setFont(new java.awt.Font("Kozuka Gothic Pr6N EL", 1, 14)); // NOI18N
        keterangan.setForeground(new java.awt.Color(255, 255, 102));
        keterangan.setText("434");

        jPanel3.setPreferredSize(new java.awt.Dimension(169, 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        transaksi.setBackground(new java.awt.Color(255, 255, 153));
        transaksi.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        transaksi.setText("KASIR ");
        transaksi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiActionPerformed(evt);
            }
        });

        menumakanan.setBackground(new java.awt.Color(255, 255, 153));
        menumakanan.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        menumakanan.setText("UPDATE MENU");
        menumakanan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menumakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menumakananActionPerformed(evt);
            }
        });

        laporan.setBackground(new java.awt.Color(255, 255, 153));
        laporan.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        laporan.setText("LAPORAN TRANSAKSI");
        laporan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        laporan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                laporanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanActionPerformed(evt);
            }
        });

        katalogbarang.setBackground(new java.awt.Color(255, 255, 153));
        katalogbarang.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        katalogbarang.setText("LIST MAKANAN");
        katalogbarang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        katalogbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                katalogbarangActionPerformed(evt);
            }
        });

        aplikasi.setBackground(new java.awt.Color(255, 255, 153));
        aplikasi.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        aplikasi.setText("APLIKASI");
        aplikasi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        aplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplikasiActionPerformed(evt);
            }
        });

        pengaturan.setBackground(new java.awt.Color(255, 255, 153));
        pengaturan.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        pengaturan.setText("PENGATURAN");
        pengaturan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pengaturan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengaturanActionPerformed(evt);
            }
        });

        logout.setBackground(new java.awt.Color(255, 255, 153));
        logout.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        logout.setText("LOG OUT");
        logout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(namakaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addComponent(menumakanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(katalogbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(aplikasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pengaturan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namakaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)
                        .addComponent(keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(menumakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(katalogbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(aplikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(pengaturan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 780));

        jPanel2.setBackground(new java.awt.Color(204, 153, 0));

        menuutamaname.setFont(new java.awt.Font("Kozuka Mincho Pro R", 1, 18)); // NOI18N
        menuutamaname.setText("MENU UTAMA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(761, Short.MAX_VALUE)
                .addComponent(menuutamaname)
                .addGap(477, 477, 477))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuutamaname, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1360, 40));

        bg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/desainlogo1.png"))); // NOI18N
        bg1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 153, 0)));
        getContentPane().add(bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, -1, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/makanan.jpg"))); // NOI18N
        logo.setText("jLabel2");
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 1080, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       setExtendedState(frommenu.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int pesan= JOptionPane.showConfirmDialog(null, "Anda Yakin Untuk Keluar?", "Konfirmasi",JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(pesan== JOptionPane.YES_OPTION){
            formlogin fl = new formlogin();
            fl.setLocationRelativeTo(null);
            fl.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void pengaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengaturanActionPerformed
        forminputkaryawan fi = new forminputkaryawan();
        fi.setVisible(true);
        fi.setLocationRelativeTo(null);
    }//GEN-LAST:event_pengaturanActionPerformed

    private void aplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplikasiActionPerformed
       tentangaplikasi ta = new tentangaplikasi();
       ta.setVisible(true);
       ta.setLocationRelativeTo(null);
    }//GEN-LAST:event_aplikasiActionPerformed

    private void katalogbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_katalogbarangActionPerformed
        listmenu l = new listmenu();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_katalogbarangActionPerformed

    private void laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanActionPerformed
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("transaksi.jasper"), null, koneksi.getKoneksi());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_laporanActionPerformed

    private void menumakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menumakananActionPerformed
        tambahmenu t = new tambahmenu();
        t.setVisible(true);
        t.setLocationRelativeTo(null);
    }//GEN-LAST:event_menumakananActionPerformed

    private void namakaryawanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_namakaryawanAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_namakaryawanAncestorAdded

    private void transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiActionPerformed
        FrmJual f = new FrmJual();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }//GEN-LAST:event_transaksiActionPerformed

    private void laporanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_laporanAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_laporanAncestorAdded

    
    
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
            java.util.logging.Logger.getLogger(frommenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frommenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frommenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frommenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frommenu f = new frommenu();
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aplikasi;
    private javax.swing.JLabel avatar;
    private javax.swing.JLabel bg1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton katalogbarang;
    private javax.swing.JLabel keterangan;
    private javax.swing.JButton laporan;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logout;
    private javax.swing.JButton menumakanan;
    private javax.swing.JLabel menuutamaname;
    private javax.swing.JLabel namakaryawan;
    private javax.swing.JButton pengaturan;
    private javax.swing.JButton transaksi;
    // End of variables declaration//GEN-END:variables
}
