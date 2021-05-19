package user;


import tradingPlatform.user.UserType;

public class User {

    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private int unitID;
    private String password;
    private UserType accountType = UserType.Employee;


    // Public variables
    public String _username = this.username;
    public String _firstName = this.firstName;
    public String _lastName = this.lastName;


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