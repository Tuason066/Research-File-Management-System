/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package research.file.management.system.images.database;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
import research.file.management.system.pages.StudentInformation;

/**
 *
 * @author Tuason
 */
public class Helper {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/research_file_management_system";
    private static final String DB_USERNAME = "phpMyAdmin";
    private static final String DB_PASSWORD = "admin";
    
    private boolean value;
    // LOGIN
    
    public static boolean isStudent(String username, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, username);
            searchStatement.setString(2, password);
            ResultSet searchRs = searchStatement.executeQuery();
            
            return searchRs.next();
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean isAdmin(String username, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, username);
            searchStatement.setString(2, password);
            ResultSet searchRs = searchStatement.executeQuery();
            
            return searchRs.next();
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateStudent(String username, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String updateQuery = "UPDATE user SET username=?, password=? WHERE username=? AND password=?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, username);
            updateStatement.setString(2, password);
            updateStatement.setString(3, UserData.getInstance().getUsername());
            updateStatement.setString(4, UserData.getInstance().getPassword());
            return updateStatement.executeUpdate() > 0;
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateAdmin(String username, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String updateQuery = "UPDATE admin SET username=?, password=? WHERE username=? AND password=?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, username);
            updateStatement.setString(2, password);
            updateStatement.setString(3, UserData.getInstance().getUsername());
            updateStatement.setString(4, UserData.getInstance().getPassword());
            return updateStatement.executeUpdate() > 0;
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean addBook(String bookId, String title, int yearPublished, String category, String author, String url, JTable table) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String insertQuery = "INSERT INTO book(book_id, title, year_published, category, author, url) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, bookId);
            insertStatement.setString(2, title);
            insertStatement.setInt(3, yearPublished);
            insertStatement.setString(4, category);
            insertStatement.setString(5, author);
            insertStatement.setString(6, url);
            if(insertStatement.executeUpdate() > 0) {
                displayBooks(table);
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void displayBooks(JTable table) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM book";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet selectRs = selectStatement.executeQuery();
            
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (selectRs.next()) {
                Object[] row = {
                    selectRs.getString("book_id"),
                    selectRs.getString("title"),
                    selectRs.getString("author"),
                    selectRs.getString("year_published")
                };

                defaultTable.addRow(row); 
            }
            
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void searchBooks(String searchText, String category, JTable table) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
            String searchQuery;
            ResultSet searchRs;
            
            if(category.equals("All")) {
                searchQuery = "SELECT * FROM book "
                    + "WHERE book_id LIKE ? OR "
                    + "title LIKE ? OR "
                    + "author LIKE ? OR "
                    + "year_published LIKE ?";
                
                PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
                searchStatement.setString(1, "%" + searchText + "%");
                searchStatement.setString(2, "%" + searchText + "%");
                searchStatement.setString(3, "%" + searchText + "%");
                searchStatement.setString(4, "%" + searchText + "%");
                searchRs = searchStatement.executeQuery();
            } else {
                searchQuery = "SELECT * FROM book "
                    + "WHERE category=? AND (book_id LIKE ? OR "
                    + "title LIKE ? OR "
                    + "author LIKE ? OR "
                    + "year_published LIKE ?)";
                
                PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
                searchStatement.setString(1, category);
                searchStatement.setString(2, "%" + searchText + "%");
                searchStatement.setString(3, "%" + searchText + "%");
                searchStatement.setString(4, "%" + searchText + "%");
                searchStatement.setString(5, "%" + searchText + "%");
                searchRs = searchStatement.executeQuery();
            }

            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 


            while (searchRs.next()) {
                Object[] row = {
                    searchRs.getString("book_id"),
                    searchRs.getString("title"),
                    searchRs.getString("author"),
                    searchRs.getString("year_published")
                };

                defaultTable.addRow(row); 
            }

            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void comboSearchBooks(String searchText, JTable table) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
            String searchQuery;
            ResultSet searchRs;
            
            if(searchText.equals("All")) {
                searchQuery = "SELECT * FROM book";
                PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
                searchRs = searchStatement.executeQuery();
            } else {
                searchQuery = "SELECT * FROM book WHERE category=?";
                PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
                searchStatement.setString(1, searchText);
                searchRs = searchStatement.executeQuery();
            }
            
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 


            while (searchRs.next()) {
                Object[] row = {
                    searchRs.getString("book_id"),
                    searchRs.getString("title"),
                    searchRs.getString("author"),
                    searchRs.getString("year_published")
                };

                defaultTable.addRow(row); 
            }

            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean addNewUser(String userId, String role, String username, char[] password, JTable table) {
        // Already an account
        if(isExistedUser(userId)) return false;
        
        // Not enrolled
        if(!isEnrolled(userId)) return false;
        
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String insertQuery = "INSERT INTO user(student_id, role, username, password) VALUES (?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, userId);
            insertStatement.setString(2, role);
            insertStatement.setString(3, username);
            insertStatement.setString(4, new String(password));

            if(insertStatement.executeUpdate() > 0) {
                displayUsers(table);
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Already an account
    public static boolean isExistedUser(String id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchIdQuery = "SELECT * FROM user WHERE student_id=?";
            PreparedStatement searchIdStatement = connection.prepareStatement(searchIdQuery);
            searchIdStatement.setString(1, id);
            ResultSet searchIdRs = searchIdStatement.executeQuery();
            
            if(searchIdRs.next()) {
                JOptionPane.showMessageDialog(null, "This ID number has already an account.");
                return true;
            } else {
                return false;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // If it is enrolled
    public static boolean isEnrolled(String id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchIdQuery = "SELECT * FROM student_info WHERE student_id=?";
            PreparedStatement searchIdStatement = connection.prepareStatement(searchIdQuery);
            searchIdStatement.setString(1, id);
            ResultSet searchIdRs = searchIdStatement.executeQuery();
            
            if(searchIdRs.next()) {
                return true;
            } else {
                // meaning wala siya sa database sa Registrar
                JOptionPane.showMessageDialog(null, "This ID number is not enrolled.\n Proceed to registrar to update your information.");
                return false;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void displayUsers(JTable table) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM user";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet selectRs = selectStatement.executeQuery();
            
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (selectRs.next()) {
                Object[] row = {
                    selectRs.getString("student_id"),
                    selectRs.getString("role"),
                    selectRs.getString("username"),
                    selectRs.getString("password")
                };

                defaultTable.addRow(row); 
            }
            
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void searchUser(String searchText, JTable table) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM user WHERE student_id LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, "%" + searchText + "%");
            ResultSet searchRs = searchStatement.executeQuery();
            
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (searchRs.next()) {
                Object[] row = {
                    searchRs.getString("student_id"),
                    searchRs.getString("role"),
                    searchRs.getString("username"),
                    searchRs.getString("password")
                };

                defaultTable.addRow(row); 
            }
            
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void showBookById(String id) throws IOException {
        
        try {
            
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM book WHERE book_id=?";
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, id);
            ResultSet searchRs = searchStatement.executeQuery();
            
            if(searchRs.next()) {
                File file = new File(searchRs.getString("url"));
                if(file.exists()) {
                    if(Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(file);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not supported.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Book softcopy doesn't exist.\nPlease go to the library to check the book.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Book softcopy doesn't exist.\nPlease go to the library to check the book.");
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void showUserInformation(String studentId) {
        try {
            
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM student_info WHERE student_id=?";
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, studentId);
            ResultSet searchRs = searchStatement.executeQuery();
            
            if(searchRs.next()) {
                new StudentInformation(searchRs).setVisible(true);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
}
