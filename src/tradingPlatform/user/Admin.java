package tradingPlatform.user;

public class Admin extends User{
    private int unitID;
    private UserType accountType;

    public Admin(String username, String firstName, String lastName, int unitID, String password, UserType accountType) {
        super(username, firstName, lastName, unitID, password, accountType);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public void addUser(User user){
        boolean found_username = false;
        //Determine if you can find the ID in the database
        // if ... found
        // found_username = true
        if (found_username = false){
//            Statement statement = conn.createStatement();
//            statement.executeUpdate("INSERT INTO Users " + "VALUES (" + user.username, user.firstName, user.lastName, user.unitID, user.password, user.accountType")");
        }
    }

    public void addAsset(){
        //new asset
    }

    public void addUnit(){
        //new unit
    }

    public void editCredits(){

    }

    public void editAssets(){

    }

    public void viewRequests(){

    }

}