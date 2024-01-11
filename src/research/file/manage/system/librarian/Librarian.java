/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package research.file.manage.system.librarian;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import research.file.manage.system.constants.DatabaseConnection;
import static research.file.manage.system.constants.DatabaseConnection.getDB_CONNECTION_URL;
import static research.file.manage.system.constants.DatabaseConnection.getDB_PASSWORD;
import static research.file.manage.system.constants.DatabaseConnection.getDB_USERNAME;

/**
 *
 * @author Tuason
 */
public class Librarian extends javax.swing.JFrame {

    /**
     * Creates new form Users
     */
    public Librarian() {
        initComponents();
        
        // TABLE
        this.displayLibrarianAccounts();
        
        librarianAccountTable.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 14));
        librarianAccountTable.getTableHeader().setOpaque(false);
        librarianAccountTable.getTableHeader().setBackground(new Color(102,153,255));
        
        // KEYPRESS
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Librarian.this.keyPressed(evt);
            }
        });
    }
    
    // KEYPRESS
    public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Simulate a button click when Enter is pressed
            searchBtn.doClick();
        }
    }
    
    public void displayLibrarianAccounts() {
        // DATABASE
        Connection connection = null;
        PreparedStatement librarianStatement = null;
        ResultSet librarianRs = null;
        
        try {
            Class.forName(DatabaseConnection.getJDBC_DRIVER());
            connection = DriverManager.getConnection(getDB_CONNECTION_URL(), getDB_USERNAME(), getDB_PASSWORD());
            
            String studentQuery = "SELECT * FROM librarian";
            librarianStatement = connection.prepareStatement(studentQuery);
            librarianRs = librarianStatement.executeQuery();
            
            DefaultTableModel defaultTable = (DefaultTableModel) librarianAccountTable.getModel();
            defaultTable.setRowCount(0); 

            while (librarianRs.next()) {
                Object[] row = {
                    librarianRs.getString("librarian_id"),
                    librarianRs.getString("username"),
                    librarianRs.getString("password")
                };

                defaultTable.addRow(row); 
            }
                
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            librarianAccountTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
            try {
                if(librarianStatement != null) {
                    librarianStatement.close();
                }
                if(librarianRs != null) {
                    librarianRs.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }
    }
    
    public void searchLibrarianAccounts(String searchText) {
        // DATABASE
        Connection connection = null;
        PreparedStatement librarianStatement = null;
        ResultSet librarianRs = null;
        
        try {
            Class.forName(DatabaseConnection.getJDBC_DRIVER());
            connection = DriverManager.getConnection(getDB_CONNECTION_URL(), getDB_USERNAME(), getDB_PASSWORD());
            
            String studentQuery = "SELECT * FROM librarian WHERE librarian_id LIKE ?";
            librarianStatement = connection.prepareStatement(studentQuery);
            librarianStatement.setString(1, "%" + searchText + "%");
            librarianRs = librarianStatement.executeQuery();
            
            DefaultTableModel defaultTable = (DefaultTableModel) librarianAccountTable.getModel();
            defaultTable.setRowCount(0); 

            while (librarianRs.next()) {
                Object[] row = {
                    librarianRs.getString("librarian_id"),
                    librarianRs.getString("username"),
                    librarianRs.getString("password")
                };

                defaultTable.addRow(row); 
            }
                
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            librarianAccountTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
            try {
                if(librarianStatement != null) {
                    librarianStatement.close();
                }
                if(librarianRs != null) {
                    librarianRs.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
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

        container = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        title = new javax.swing.JPanel();
        library = new javax.swing.JLabel();
        underline = new javax.swing.JLabel();
        tcc = new javax.swing.JLabel();
        img = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        librarianAccountTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/research/file/manage/system/images/back.png"))); // NOI18N
        backBtn.setToolTipText("Back to Homepage");
        backBtn.setBorder(null);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        header.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        library.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        library.setText("LIBRARY");
        title.add(library, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        underline.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        underline.setText("____________________");
        title.add(underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 220, -1));

        tcc.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        tcc.setText("TALISAY CITY COLLEGE");
        title.add(tcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/research/file/manage/system/images/tcc logo.png"))); // NOI18N
        title.add(img, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        header.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 100));

        container.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 110));

        body.setBackground(new java.awt.Color(204, 204, 204));
        body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        librarianAccountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Librarian ID", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        librarianAccountTable.setRowHeight(25);
        librarianAccountTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        jScrollPane1.setViewportView(librarianAccountTable);

        body.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 760, 360));

        searchField.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        searchField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        body.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 200, 30));

        searchBtn.setBackground(new java.awt.Color(102, 153, 255));
        searchBtn.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(51, 51, 51));
        searchBtn.setText("Search");
        searchBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        body.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 90, 30));

        container.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 800, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new LibrarianHomepage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String searchText = searchField.getText();
        this.searchLibrarianAccounts(searchText);
    }//GEN-LAST:event_searchBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Librarian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel body;
    private javax.swing.JPanel container;
    private javax.swing.JPanel header;
    private javax.swing.JLabel img;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable librarianAccountTable;
    private javax.swing.JLabel library;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel tcc;
    private javax.swing.JPanel title;
    private javax.swing.JLabel underline;
    // End of variables declaration//GEN-END:variables
}
