/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package research.file.manage.system.constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static research.file.manage.system.constants.DatabaseConnection.getDB_CONNECTION_URL;
import static research.file.manage.system.constants.DatabaseConnection.getDB_PASSWORD;
import static research.file.manage.system.constants.DatabaseConnection.getDB_USERNAME;
import static research.file.manage.system.constants.DatabaseConnection.getJDBC_DRIVER;
import research.file.manage.system.studentFrames.StudentHomepage;

/**
 *
 * @author Tuason
 */
public class Helper {
    public static void displayBookTableItems() {
        // DATABASE
        Connection connection = null;
        PreparedStatement bookStatement = null;
        ResultSet bookRs = null;
        
        try {
            Class.forName(getJDBC_DRIVER());
            connection = DriverManager.getConnection(getDB_CONNECTION_URL(), getDB_USERNAME(), getDB_PASSWORD());
            
            // CHECK IF IT IS A STUDENT
            String bookQuery = "SELECT * FROM book ORDER BY year_published DESC";
            bookStatement = connection.prepareStatement(bookQuery);
            bookRs = bookStatement.executeQuery();
            
            JTable table = Tables.getInstance().getBookTable();

            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (bookRs.next()) {
                Object[] row = {
                    bookRs.getString("accession_name"),
                    bookRs.getString("title"),
                    bookRs.getString("author"),
                    bookRs.getInt("year_published"),
                    bookRs.getString("location"),
                    bookRs.getString("call_number"),
                    bookRs.getString("status"),
                    bookRs.getString("noOfCopies"),
                    bookRs.getString("subjects"),
                    bookRs.getString("publisher"),
                    bookRs.getString("borrow_for")
                };

                defaultTable.addRow(row); 
            }
                
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
             try {
                if (bookStatement != null) {
                    bookStatement.close();
                }
                if (bookRs != null) {
                    bookRs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public static void searchItems(String searchText) {
         // DATABASE
        Connection connection = null;
        PreparedStatement bookStatement = null;
        ResultSet bookRs = null;
        
        try {
            Class.forName(getJDBC_DRIVER());
            connection = DriverManager.getConnection(getDB_CONNECTION_URL(), getDB_USERNAME(), getDB_PASSWORD());
            
            // CHECK IF IT IS A STUDENT
            String bookQuery = "SELECT * FROM book WHERE "
                    + "accession_name LIKE ? OR "
                    + "title LIKE ? OR "
                    + "author LIKE ? OR "
                    + "year_published LIKE ? OR "
                    + "location LIKE ? OR "
                    + "call_number LIKE ? OR "
                    + "subjects LIKE ? OR "
                    + "publisher LIKE ? OR "
                    + "borrow_for LIKE ? " // 9
                    + " ORDER BY year_published DESC";
            bookStatement = connection.prepareStatement(bookQuery);
            bookStatement.setString(1, "%" + searchText + "%");
            bookStatement.setString(2, "%" + searchText + "%");
            bookStatement.setString(3, "%" + searchText + "%");
            bookStatement.setString(4, "%" + searchText + "%");
            bookStatement.setString(5, "%" + searchText + "%");
            bookStatement.setString(6, "%" + searchText + "%");
            bookStatement.setString(7, "%" + searchText + "%");
            bookStatement.setString(8, "%" + searchText + "%");
            bookStatement.setString(9, "%" + searchText + "%");
            bookRs = bookStatement.executeQuery();
            
            JTable table = Tables.getInstance().getBookTable();

            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (bookRs.next()) {
                Object[] row = {
                    bookRs.getString("accession_name"),
                    bookRs.getString("title"),
                    bookRs.getString("author"),
                    bookRs.getInt("year_published"),
                    bookRs.getString("location"),
                    bookRs.getString("call_number"),
                    bookRs.getString("status"),
                    bookRs.getString("noOfCopies"),
                    bookRs.getString("subjects"),
                    bookRs.getString("publisher"),
                    bookRs.getString("borrow_for")
                };

                defaultTable.addRow(row); 
            }
                
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
             try {
                if (bookStatement != null) {
                    bookStatement.close();
                }
                if (bookRs != null) {
                    bookRs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}
