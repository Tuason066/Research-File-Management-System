/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package research.file.management.system.images.database;

/**
 *
 * @author Tuason
 */
public class UserData {
    private static UserData instance = null;
    
    private String username;
    private String password;
    
    // Private constructor to prevent creating instances outside this class
    private UserData() {}
    
    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
    
    public void setUserData(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
}
