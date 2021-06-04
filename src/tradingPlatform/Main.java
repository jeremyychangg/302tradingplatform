package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.gui.employeeScreen;

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
//        User test = new User("Angela", "Shibu", "IT00001", "password.CAB302", UserType.Employee);
//        String salt = generateSalt(100).get();

//        System.out.println(verifyPassword("password.CAB302", "q/i13M0jPpAtG3hrGyh3ZTUx2GoLf6FqLtzxSh21vAFlkjaseju1KOrUC+Jie41fl/4leimzXZkRnPApH8E5ZA==", salt));
//        System.out.println(retrieveOrders());
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


        new employeeScreen();
//        new Screen();
    }

}

