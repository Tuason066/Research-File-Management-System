/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package research.file.manage.system.constants;

import javax.swing.JTable;

/**
 *
 * @author Tuason
 */
public class Tables {
    private static Tables instance = null;
    private Tables() {}
    
    public static Tables getInstance() {
        if (instance == null) {
            instance = new Tables();
        }
        return instance;
    }
    
    // TABLES
    private JTable bookTable;
    
    
    // GETTERS AND SETTERS

    public JTable getBookTable() {
        return bookTable;
    }

    public void setBookTable(JTable bookTable) {
        this.bookTable = bookTable;
    }

    
    
}
