/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package research.file.management.system.pages;

import java.time.Year;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static research.file.management.system.images.database.Helper.addNewBook;

/**
 *
 * @author Tuason
 */
public class AddBook extends javax.swing.JFrame {

    private JTable table;
    
    /**
     * Creates new form AddUser
     */
    public AddBook(JTable table) {
        initComponents();
        
        // book table
        this.table = table;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        bookIdField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        authorField = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        addBtn1 = new javax.swing.JButton();
        yearPublishedField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("Add New Book");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        header.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 580, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/research/inventory/management/system/images/library.jpg"))); // NOI18N
        header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 60));

        getContentPane().add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel3.setText("Category");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        bookIdField.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jPanel2.add(bookIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 180, 30));

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel4.setText("Book ID");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        categoryComboBox.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSIT", "BSHM", "BEED", "BSED", "Others" }));
        jPanel2.add(categoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 180, 30));

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel5.setText("Title");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        titleField.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jPanel2.add(titleField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 180, 30));

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel6.setText("Author");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        authorField.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jPanel2.add(authorField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 180, 30));

        cancelBtn.setBackground(new java.awt.Color(204, 204, 204));
        cancelBtn.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel2.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 130, 30));

        addBtn1.setBackground(new java.awt.Color(204, 204, 204));
        addBtn1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        addBtn1.setText("Add");
        addBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtn1ActionPerformed(evt);
            }
        });
        jPanel2.add(addBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 130, 30));

        yearPublishedField.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jPanel2.add(yearPublishedField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 180, 30));

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel7.setText("Year Published");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel8.setText("URL (Optional)");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        urlField.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jPanel2.add(urlField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 180, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 600, 340));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void addBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtn1ActionPerformed
        String bookId = bookIdField.getText();
        String title = titleField.getText();
        String yearPublishedStr = yearPublishedField.getText();
        int yearPublished = 0;
        String category = (String) categoryComboBox.getSelectedItem();
        String author = authorField.getText();
        String url = urlField.getText();
        
        // CHECKING
        if(bookId.equals("") || bookId.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill in the book ID field.");
        }
        if(title.equals("") || title.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill in the book title field.");
        }
        if(yearPublishedStr.equals("") || yearPublishedStr.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill in the year published field.");
        }
        try {
            yearPublished = Integer.parseInt(yearPublishedStr);;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Please fill in the year published field. e.g. (" + Year.now().getValue() + ").");
        }
        
        // DATABASE
        if(addNewBook(bookId, title, yearPublished, category, author, url, this.table)) {
            JOptionPane.showMessageDialog(rootPane, "Successfully add new book.");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Failed to add new book.");
        }
    }//GEN-LAST:event_addBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new AddBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn1;
    private javax.swing.JTextField authorField;
    private javax.swing.JTextField bookIdField;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField titleField;
    private javax.swing.JTextField urlField;
    private javax.swing.JTextField yearPublishedField;
    // End of variables declaration//GEN-END:variables
}
