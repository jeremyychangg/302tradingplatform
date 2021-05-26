package tradingPlatform.user;


public class Lead extends User {
    private int unitID;
    private String password;
    private UserType accountType;

    public Lead(String username, String firstName, String lastName, int unitID, String password, UserType accountType) {
        super(username, firstName, lastName, unitID, password, accountType);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public void requestUser(String username, String firstName, String lastName){

    }

}
