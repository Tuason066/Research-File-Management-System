/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package research.file.manage.system.frames;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static research.file.manage.system.constants.DatabaseConnection.getDB_CONNECTION_URL;
import static research.file.manage.system.constants.DatabaseConnection.getDB_PASSWORD;
import static research.file.manage.system.constants.DatabaseConnection.getDB_USERNAME;
import static research.file.manage.system.constants.DatabaseConnection.getJDBC_DRIVER;
import research.file.manage.system.constants.UserData;

/**
 *
 * @author Tuason
 */
public final class Settings extends javax.swing.JFrame {

    private String username;
    private String role;
    
    public Settings() {
        initComponents();
        
        this.username = UserData.getUsername();
        this.role = UserData.getRole();
        
        displayAccountInformation();
        
        // KEYPRESS
        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Settings.this.keyPressed(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Settings.this.keyPressed(evt);
            }
        });
    }
    
    // KEYPRESS
    public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Simulate a button click when Enter is pressed
            updateBtn.doClick();
        }
    }

    public void displayAccountInformation() {
        // DATABASE
        Connection connection = null;
        PreparedStatement accountStudentStatement = null;
        ResultSet accounStudenttRs = null;
        PreparedStatement accountLibrarianStatement = null;
        ResultSet accountLibrariantRs = null;
        
        try {
            Class.forName(getJDBC_DRIVER());
            connection = DriverManager.getConnection(getDB_CONNECTION_URL(), getDB_USERNAME(), getDB_PASSWORD());
            
            if(this.role.equals("student")) {
                // RETRIEVE STUDENT ACCOUNT INFORMATION
                String accountStudentQuery = "SELECT username, password FROM student WHERE username=?";
                accountStudentStatement = connection.prepareStatement(accountStudentQuery);
                accountStudentStatement.setString(1, this.username);
                accounStudenttRs = accountStudentStatement.executeQuery();
                if(accounStudenttRs.next()) {
                    usernameField.setText(accounStudenttRs.getString("username"));
                    passwordField.setText(accounStudenttRs.getString("password"));
                }
            } else {
                // RETRIEVE LIBRARIAN ACCOUNT INFORMATION
                String accountLibrarianQuery = "SELECT username, password FROM librarian WHERE username=?";
                accountLibrarianStatement = connection.prepareStatement(accountLibrarianQuery);
                accountLibrarianStatement.setString(1, this.username);
                accountLibrariantRs = accountLibrarianStatement.executeQuery();
                if(accountLibrariantRs.next()) {
                    usernameField.setText(accountLibrariantRs.getString("username"));
                    passwordField.setText(accountLibrariantRs.getString("password"));
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
            try {
                if(accountStudentStatement != null) {
                    accountStudentStatement.close();
                }
                if(accountLibrarianStatement != null) {
                    accountLibrarianStatement.close();
                }
                if(accounStudenttRs != null) {
                    accounStudenttRs.close();
                }
                if(accountLibrariantRs != null) {
                    accountLibrariantRs.close();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        updateBtn = new javax.swing.JButton();
        showPasswordCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setText("Account Information");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel11.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel11.setText("Username");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));
        jPanel1.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 240, 30));

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));
        jPanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 240, 30));

        updateBtn.setBackground(new java.awt.Color(154, 49, 20));
        updateBtn.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 140, 40));

        showPasswordCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        showPasswordCheckBox.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        showPasswordCheckBox.setText("Show password");
        showPasswordCheckBox.setToolTipText("Show/Hide Password");
        showPasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCheckBoxActionPerformed(evt);
            }
        });
        jPanel1.add(showPasswordCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        
        String newUsername = usernameField.getText();
        String newPassword = new String(passwordField.getPassword());
        
        // if current username && new username
            // update - password
        // else 
            // check if username exist
                // promt - username already exist
            // else
                // update - username && password
        
        
        // DATABASE
        Connection connection = null;
        PreparedStatement accountStatement = null;
        PreparedStatement updatePasswordStatement = null;
        PreparedStatement updateAccountStatement = null;
        ResultSet accountRs = null;
        
        try {
            Class.forName(getJDBC_DRIVER());
            connection = DriverManager.getConnection(getDB_CONNECTION_URL(), getDB_USERNAME(), getDB_PASSWORD());
            
            if(this.role.equals("student")) {
                // if current username && new username
                if(this.username.equals(newUsername)) {

                    // update - password
                    String updatePasswordQuery = "UPDATE student SET password=? WHERE username=?";
                    updatePasswordStatement = connection.prepareStatement(updatePasswordQuery);
                    updatePasswordStatement.setString(1, newPassword);
                    updatePasswordStatement.setString(2, this.username);

                    if(updatePasswordStatement.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Update successfully");
                    }
                } else {
                    // check if username exist
                    // CHECK USERNAME IF ALREADY EXIST
                    String accountQuery = "SELECT username FROM student WHERE username=?";
                    accountStatement = connection.prepareStatement(accountQuery);
                    accountStatement.setString(1, newUsername);
                    accountRs = accountStatement.executeQuery();
                    if(accountRs.next()) {

                        JOptionPane.showMessageDialog(rootPane, "Username is already exist.");

                    } else {
                        // UPDATE ACCOUNT INFORMATION
                            String updateAccountQuery = "UPDATE student SET username=?, password=? WHERE username=?";
                            updateAccountStatement = connection.prepareStatement(updateAccountQuery);
                            updateAccountStatement.setString(1, newUsername);
                            updateAccountStatement.setString(2, newPassword);
                            updateAccountStatement.setString(3, this.username);

                            if(updateAccountStatement.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Update successfully");
                                this.username = newUsername;
                                UserData.setUsername(newUsername);
                            }
                    }

                }
            } else { // LIBRARIAN
                // if current username && new username
                if(this.username.equals(newUsername)) {

                    // update - password
                    String updatePasswordQuery = "UPDATE librarian SET password=? WHERE username=?";
                    updatePasswordStatement = connection.prepareStatement(updatePasswordQuery);
                    updatePasswordStatement.setString(1, newPassword);
                    updatePasswordStatement.setString(2, this.username);

                    if(updatePasswordStatement.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Update successfully");
                    }
                } else {
                    // check if username exist
                    // CHECK USERNAME IF ALREADY EXIST
                    String accountQuery = "SELECT username FROM librarian WHERE username=?";
                    accountStatement = connection.prepareStatement(accountQuery);
                    accountStatement.setString(1, newUsername);
                    accountRs = accountStatement.executeQuery();
                    if(accountRs.next()) {

                        JOptionPane.showMessageDialog(rootPane, "Username is already exist.");

                    } else {
                        // UPDATE ACCOUNT INFORMATION
                            String updateAccountQuery = "UPDATE librarian SET username=?, password=? WHERE username=?";
                            updateAccountStatement = connection.prepareStatement(updateAccountQuery);
                            updateAccountStatement.setString(1, newUsername);
                            updateAccountStatement.setString(2, newPassword);
                            updateAccountStatement.setString(3, this.username);

                            if(updateAccountStatement.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Update successfully");
                                this.username = newUsername;
                                UserData.setUsername(newUsername);
                            }
                    }

                }
            }
            
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
             try {
                if (updateAccountStatement != null) {
                    updateAccountStatement.close();
                }
                if (updatePasswordStatement != null) {
                    updatePasswordStatement.close();
                }
                if (accountStatement != null) {
                    accountStatement.close();
                }
                if (accountRs != null) {
                    accountRs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void showPasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxActionPerformed
        if (showPasswordCheckBox.isSelected()){
            passwordField.setEchoChar((char)0);
        }
        else{
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_showPasswordCheckBoxActionPerformed

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
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBox showPasswordCheckBox;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}