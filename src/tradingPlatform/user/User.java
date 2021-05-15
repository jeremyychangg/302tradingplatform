package user;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class SQLDatabaseConnection {
//    // Connect to your database.
//    // Replace server name, username, and password with your credentials
//    public static void main(String[] args) {
//        String connectionUrl =
//                "jdbc:sqlserver://yourserver.database.windows.net:1433;"
//                        + "database=AdventureWorks;"
//                        + "user=yourusername@yourserver;"
//                        + "password=yourpassword;"
//                        + "encrypt=true;"
//                        + "trustServerCertificate=false;"
//                        + "loginTimeout=30;";
//
//        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
//            // Code here.
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

public class User {
    enum UserType {
        Employee,
        Admin,
        Lead
    }

    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private int unitID;
    private String password;
    private UserType accountType = UserType.Employee;

    public User(String username, String firstName, String lastName, int unitID, String password, UserType accountType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;
    }

    public UserType getAccountType(){
        return accountType;
    }

    public int getCredits(){
        // search the Units database and return the credit of the given unit
        // return the credits in int
    }

    public String getName(){
        return firstName;
    }

    public boolean usernameExists(){

    }

    //GUI
    public boolean passwordCorrect(){

    }

    /**
     * Returns the assets associated with the userID
     */
    public void returnAssests(){
        //this.unitID
        //take the unitID and query the orders table to determine their orders
    }

    public void getNotifications(){
    }

    public void getWatchList(){
        //
    }
}