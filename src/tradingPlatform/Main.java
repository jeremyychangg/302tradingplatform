package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.gui.client.employeeScreen;
import tradingPlatform.gui.client.orderGUI;
import tradingPlatform.gui.client.watchlistGUI;
import tradingPlatform.gui.common.loginGUI;
import tradingPlatform.user.Admin;
import tradingPlatform.user.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

import static tradingPlatform.user.User.getUnitID;

public class Main {
    private static String currentUser;
    private static String currentUnit;

    public static String getCurrentUser() {
        return Main.currentUser;
    }


    public static String getCurrentUnit() throws SQLException {
        return Main.currentUnit;
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
        } catch (Exception thr){
            String msg = "SQL Connection Error : Program not connected to database. ";
            JOptionPane.showMessageDialog(null, msg);
            throw new SQLException(msg, thr);
        }
        setCurrentUser("S0001");
        Main.currentUnit = "IT00001";

//        new adminScreen();
//        new watchlistGUI();
            new employeeScreen();
//        User one = new User("Stephen", "King", "ADM00001",  " ", UserType.Employee);

//
//
//        User test = new User("Angela", "Shibu", "IT00001", "hello", UserType.Employee);
//
//
//        Admin.addUserToDatabase(test);
//        User one = new User("John", "Smith", "ADM00001",  "admin1", UserType.Admin);
//        Admin.addUserToDatabase(one);
//        User two = new User("Peter", "La", "IT00000034",   "password.1", UserType.Admin);
//        Admin.addUserToDatabase(two);
//        User three = new User("Angela", "Da Cost", "IT00001", "password", UserType.Admin);
//        Admin.addUserToDatabase(three);
//        User four = new User("Pat", "Kam", "IT00001", "password", UserType.Lead);
//         Admin.addUserToDatabase(four);
//        User five = new User("Ian", "Musk", "IT00001", "pass", UserType.Employee);
//        Admin.addUserToDatabase(five);
//
//        User six = new User("Stephen", "King", "AD0001", "pass", UserType.Employee);


//        new employeeScreen();

//        Inventory values = new Inventory("IT00001");
//        ArrayList<InventoryItem> inventory = values.unitInventory;
//        for (InventoryItem c: inventory){
//            System.out.println(c.asset.assetName);
//            System.out.println(c.purchasePrice);
//            System.out.println(c.quantity);
//        }

//        editCredits("ADM01", 1200);
//        editAccountType("A003", "Admin");
//        unitExists("IT00001");

//        setCurrentUser("S0001");
//        new loginGUI();
//        new leadScreen();
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

