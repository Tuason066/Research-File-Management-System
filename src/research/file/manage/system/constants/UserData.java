/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package research.file.manage.system.constants;

/**
 *
 * @author Tuason
 */
public class UserData {
    private static UserData instance = null;
    private UserData() {
    }
    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
    
    // DATA
    private static String username;
    private static String role;
    
    // GETTERS AND SETTERS

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserData.username = username;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserData.role = role;
    }
    
    
}
