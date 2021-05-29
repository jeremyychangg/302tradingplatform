package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.exceptions.UserException;
import tradingPlatform.gui.loginGUI;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static String currentUser;
    public static String getCurrentUser(){
        return Main.currentUser;
    }

    public static void resetCurrentUser(){
        setCurrentUser(null);
    }

    public static Connection connection;

    public static void setCurrentUser(String currentUser) {
        Main.currentUser = currentUser;
    }

    public static void main(String[] args) throws SQLException, UserException {
        try {
            new JBDCConnection();
        } catch (Exception e){
        }
//        setCurrentUser("S0001");
        new loginGUI();
//        Admin current = new Admin(getFirstName(), getLastName(), getUnitID(), getAccountType());
//
//        User current = new User("S0001");
//        System.out.println(current.returnfirstName());

//        Admin current = new Admin(getFirstName(), getLastName(), getUnitID());
//        User lisa = new User("Anna", "Mia", "IT00001", "password", UserType.Lead);
//        System.out.println(lisa.returnUserID());
//        System.out.println(getUnitID());

//        currentUser.newUser(new Employee("Lisa", "Vanderpump", "IT00001", "password"));
//        Admin newAdmin = new Admin("Angela", "Da Cost", "IT00001", "password");


//        new employeeScreen();
//        new Screen();
    }
}

