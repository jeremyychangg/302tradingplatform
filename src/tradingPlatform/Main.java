package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.gui.leadScreen;

import java.sql.Connection;

public class Main {
    private static String currentUser;

    public static String getCurrentUser() {
        return Main.currentUser;
    }

    public static void resetCurrentUser() {
        setCurrentUser(null);
    }

    public static Connection connection;

    public static void setCurrentUser(String currentUser) {
        Main.currentUser = currentUser;
    }

    public static void main(String[] args) throws Exception {
        try {
            new JBDCConnection();
        } catch (Exception e) {
        }

//        editCredits("ADM01", 1200);
//        editAccountType("A003", "Admin");
//        unitExists("IT00001");

//        setCurrentUser("S0001");
//        new loginGUI();
        new leadScreen();
//        System.out.println(usernameExists("ADSFS"));
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

