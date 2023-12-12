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
    public static boolean isStudentLoggedIn(String username, String password) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM user WHERE username=? AND password=?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, username);
            searchStatement.setString(2, password);
            searchRs = searchStatement.executeQuery();
            
            return searchRs.next();
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static boolean isAdminLoggedIn(String username, String password) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM admin WHERE username=? AND password=?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, username);
            searchStatement.setString(2, password);
            searchRs = searchStatement.executeQuery();
            
            return searchRs.next();
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // UPDATES
    public static boolean updateStudentInfo(String username, String password) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String updateQuery = "UPDATE user SET username=?, password=? WHERE username=? AND password=?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, username);
            updateStatement.setString(2, password);
            updateStatement.setString(3, UserData.getInstance().getUsername());
            updateStatement.setString(4, UserData.getInstance().getPassword());
            return updateStatement.executeUpdate() > 0;
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (updateStatement != null) {
                    updateStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static boolean updateAdminInfo(String username, String password) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String updateQuery = "UPDATE admin SET username=?, password=? WHERE username=? AND password=?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, username);
            updateStatement.setString(2, password);
            updateStatement.setString(3, UserData.getInstance().getUsername());
            updateStatement.setString(4, UserData.getInstance().getPassword());
            return updateStatement.executeUpdate() > 0;
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (updateStatement != null) {
                    updateStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // BOOKS
    public static boolean addNewBook(String bookId, String title, int yearPublished, String category, String author, String url, JTable table) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String insertQuery = "INSERT INTO book(book_id, title, year_published, category, author, url) VALUES (?,?,?,?,?,?)";
            insertStatement = connection.prepareStatement(insertQuery);
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
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static void displayBooks(JTable table) {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM book";
            selectStatement = connection.prepareStatement(selectQuery);
            selectRs = selectStatement.executeQuery();
            
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
        } finally {
            try {
                if (selectRs != null) {
                    selectRs.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // SEARCH
    public static void searchBooks(String searchText, String category, JTable table) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
            String searchQuery;
            
            if(category.equals("All")) {
                searchQuery = "SELECT * FROM book "
                    + "WHERE book_id LIKE ? OR "
                    + "title LIKE ? OR "
                    + "author LIKE ? OR "
                    + "year_published LIKE ?";
                
                searchStatement = connection.prepareStatement(searchQuery);
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
                
                searchStatement = connection.prepareStatement(searchQuery);
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
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static void comboSearchBooks(String searchText, JTable table) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
            String searchQuery;
            
            if(searchText.equals("All")) {
                searchQuery = "SELECT * FROM book";
                searchStatement = connection.prepareStatement(searchQuery);
                searchRs = searchStatement.executeQuery();
            } else {
                searchQuery = "SELECT * FROM book WHERE category=?";
                searchStatement = connection.prepareStatement(searchQuery);
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
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // ADD
    public static boolean addNewStudent(String userId, String role, String username, char[] password, JTable table) {
        // Already an account
        if(isStudentExisted(userId)) return false;
        
        // Not enrolled
        if(!isStudentEnrolled(userId)) return false;
        
        Connection connection = null;
        PreparedStatement insertStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String insertQuery = "INSERT INTO user(student_id, role, username, password) VALUES (?,?,?,?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, userId);
            insertStatement.setString(2, role);
            insertStatement.setString(3, username);
            insertStatement.setString(4, new String(password));

            if(insertStatement.executeUpdate() > 0) {
                displayStudents(table);
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // Already an account
    public static boolean isStudentExisted(String id) {
        Connection connection = null;
        PreparedStatement searchIdStatement = null;
        ResultSet searchIdRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchIdQuery = "SELECT * FROM user WHERE student_id=?";
            searchIdStatement = connection.prepareStatement(searchIdQuery);
            searchIdStatement.setString(1, id);
            searchIdRs = searchIdStatement.executeQuery();
            
            if(searchIdRs.next()) {
                JOptionPane.showMessageDialog(null, "This ID number has already an account.");
                return true;
            } else {
                return false;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (searchIdRs != null) {
                    searchIdRs.close();
                }
                if (searchIdStatement != null) {
                    searchIdStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // If it is enrolled
    public static boolean isStudentEnrolled(String id) {
        Connection connection = null;
        PreparedStatement searchIdStatement = null;
        ResultSet searchIdRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchIdQuery = "SELECT * FROM student_info WHERE student_id=?";
            searchIdStatement = connection.prepareStatement(searchIdQuery);
            searchIdStatement.setString(1, id);
            searchIdRs = searchIdStatement.executeQuery();
            
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
        } finally {
            try {
                if (searchIdRs != null) {
                    searchIdRs.close();
                }
                if (searchIdStatement != null) {
                    searchIdStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // USERS
    public static void displayStudents(JTable table) {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM user";
            selectStatement = connection.prepareStatement(selectQuery);
            selectRs = selectStatement.executeQuery();
            
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
        } finally {
            try {
                if (selectRs != null) {
                    selectRs.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static void searchStudents(String searchText, JTable table) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM user WHERE student_id LIKE ?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, "%" + searchText + "%");
            searchRs = searchStatement.executeQuery();
            
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
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    // DISPLAY DATA
    public static void showBookById(String id) throws IOException {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM book WHERE book_id=?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, id);
            searchRs = searchStatement.executeQuery();
            
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
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static void showStudentInformation(String studentId) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM student_info WHERE student_id=?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, studentId);
            searchRs = searchStatement.executeQuery();
            
            if(searchRs.next()) {
                new StudentInformation(searchRs).setVisible(true);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
}
